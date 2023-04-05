package kr.co.catdog.apicontroller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import kr.co.catdog.dto.CommunityDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kr.co.catdog.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommunityApiController {

	@Autowired
	private CommunityService communityService;

	@GetMapping("media/{fileName}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) throws IOException {
		Resource resource = communityService.getMedia(fileName);
		byte[] imageBytes = IOUtils.toByteArray(resource.getInputStream());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	@GetMapping("api/user/community/list")
	public List<CommunityDTO> getList(CommunityDTO communityDTO){
		log.info("list에서 넘어온 user_id : "+communityDTO.getUser_id());
		log.info("list에서 넘어온 loadCount : "+communityDTO.getLoadCount());
		log.info("list에서 넘어온 communityMsg : "+communityDTO.getCommunity_msg());
		return communityService.selectSize(communityDTO);
	}




}
