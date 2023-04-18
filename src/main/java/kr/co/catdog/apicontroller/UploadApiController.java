package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.UploadFileDTO;
import kr.co.catdog.dto.UploadResultDTO;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UploadApiController {

    private final String upPath = "src/main/resources/static/assets/img/shop/";
    private final ShopService shopService;

    @PostMapping(value = "/user/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        if (uploadFileDTO.getFiles() != null) {
            final List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile -> {
                String originalName = multipartFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(upPath, uuid + "_" + originalName);

                try {
                    multipartFile.transferTo(savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(UploadResultDTO.builder().uuid(uuid).fileName(originalName).build());
            }); // end forEach
            return list;
        } // end if
        return null;
    }

    @GetMapping("/user/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(upPath + File.separator + fileName);
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/user/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(upPath + File.separator + fileName);
        String resourceName = resource.getFilename();
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;
        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();
            if (contentType.startsWith("image")) {
                File thumbnailFile = new File(upPath + File.separator + fileName);
                thumbnailFile.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resultMap.put("result", removed);

        return resultMap;
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) throws IOException {
        Resource resource = shopService.getMedia(fileName);
        byte[] imageBytes = IOUtils.toByteArray(resource.getInputStream());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
