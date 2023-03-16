package kr.co.catdog.apicontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommunityApiController {
	
	@Value("${kr.co.catdog.upload.path}")
	private String upPath;
	
	@GetMapping("/testimg/{fileName}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
		Resource resource = new FileSystemResource(upPath + File.separator+ fileName);
		log.info("파일 이름 : "+fileName);
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		
		
		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		} catch (IOException e) {
			
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().headers(headers).body(resource);
		
	}
	

}
