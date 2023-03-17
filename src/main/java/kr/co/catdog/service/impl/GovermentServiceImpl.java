package kr.co.catdog.service.impl;

import kr.co.catdog.dto.GovermentDTO;
import kr.co.catdog.mapper.GovermentMapper;
import kr.co.catdog.service.GoverMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GovermentServiceImpl implements GoverMentService {


    @Autowired
    GovermentMapper govermentMapper;

    @Override
    public List<GovermentDTO> getAll() {
        return govermentMapper.getAll();
    }
}
