package kr.co.catdog.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import kr.co.catdog.domain.PageResponseVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import kr.co.catdog.domain.CommunityVO;
import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.dto.CommunityDTO;
import kr.co.catdog.mapper.CommunityMapper;
import kr.co.catdog.service.CommunityService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Value("${kr.co.catdog.upload.path}")
    private String upPath;

    private final ResourceLoader resourceLoader;
    private final String uploadDir = "static/assets/img/community";

    private ModelMapper modelMapper = new ModelMapper();

    public CommunityServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

//    @Override
//    public List<CommunityVO> selectAll(CommunityVO communityVO) {
//        List<CommunityVO> comList = communityMapper.selectAll(communityVO);
//        return comList;
//    }

    @Override
    public int register(CommunityDTO communityDTO)  {
        if(communityDTO.getMedia_type()== 1) {
            try {
                createPath(communityDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        int result = communityMapper.register(communityDTO);
        log.info("db다녀온 communityDTO : " + communityDTO);
        if (!(result > 0)) {
            return 0;
        }

        return communityMapper.registerMedia(communityDTO);
    }

    private void createPath(CommunityDTO communityDTO) throws IOException {
        String fileName = UUID.randomUUID().toString()+communityDTO.getFile().getOriginalFilename();
        Path path = Paths.get( "src/main/resources/static/assets/img/community/" + fileName);
        Files.createDirectories(path.getParent());
        log.info("path 생성 됨"+path);
        Files.write(path, communityDTO.getFile().getBytes());
        communityDTO.setMedia_path(fileName);
    }

    public Resource getMedia(String filename) {
        return resourceLoader.getResource( "classpath:" + uploadDir + "/" + filename);
    }

    @Override
    public CommunityDTO findByCommunity(CommunityDTO communityDTO) {

        CommunityVO communityVO = CommunityVO.builder()
                .community_no(communityDTO.getCommunity_no())
                .build();

        return modelMapper.map(communityMapper.findByCommunity(communityVO), CommunityDTO.class);
    }

    @Override
    public int update(CommunityDTO communityDTO) throws IOException {
        CommunityVO communityVO = CommunityVO.builder()
                .community_no(communityDTO.getCommunity_no())
                .category_type(communityDTO.getCategory_type())
                .community_content(communityDTO.getCommunity_content())
                .build();
        int result = communityMapper.update(communityVO);
        log.info("update 다녀온 result : " + result);
        if (!(result > 0)) {
            return 0;
        }
        if (communityDTO.getMedia_type()==1 && !communityDTO.getFile().isEmpty()) {
            createPath(communityDTO);
            MediaVO media = MediaVO.builder()
                    .media_no(communityDTO.getMedia_no())
                    .media_type(communityDTO.getMedia_type())
                    .media_path(communityDTO.getMedia_path())
                    .build();
            int mResult = communityMapper.updateMedia(media);
            log.info("이미지updateMedia 다녀온 mResult : " + mResult);

        } else if (communityDTO.getMedia_type()==2 && communityDTO.getMedia_path() != null) {
            MediaVO media = MediaVO.builder()
                    .media_no(communityDTO.getMedia_no())
                    .media_type(communityDTO.getMedia_type())
                    .media_path(communityDTO.getMedia_path())
                    .build();
            int mResult = communityMapper.updateMedia(media);
            log.info("영상updateMedia 다녀온 mResult : " + mResult);
        }
        return 1;


    }

    @Override
    public int delete(CommunityDTO communityDTO) {

        CommunityVO communityVO = CommunityVO.builder()
                .community_no(communityDTO.getCommunity_no())
                .build();
        int result = communityMapper.delete(communityVO);

        if (!(result > 0)) {
            return 0;
        }

        return 1;
    }

//    @Override
//    public List<CommunityVO> myCommunity(CommunityDTO communityDTO) {
//
//        CommunityVO communityVO = CommunityVO.builder()
//                .user_id(communityDTO.getUser_id())
//                .build();
//
//
//        return communityMapper.myCommunity(communityVO);
//    }

//    @Override
//    public List<CommunityVO> popularPosts(CommunityDTO communityDTO) {
//
//        CommunityVO communityVO = CommunityVO.builder()
//                .user_id(communityDTO.getUser_id())
//                .build();
//
//
//        return communityMapper.popularPosts(communityVO);
//    }

    @Override
    public List<CommunityDTO> selectSize(CommunityDTO communityDTO) {
        PageResponseVO pageResponseVO = PageResponseVO.index()
                .communityDTO(communityDTO)
                .build();
        List<CommunityVO> listVO = communityMapper.selectSize(pageResponseVO);
        List<CommunityDTO> listDTO = listVO.stream()
                .map(communityVO -> modelMapper.map(communityVO, CommunityDTO.class))
                .collect(Collectors.toList());



        return listDTO;
    }


}
