package com.zte.shopping.service.impl;


import com.zte.shopping.entity.Attache;
import com.zte.shopping.mapper.FileUploadMapper;
import com.zte.shopping.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadMapper fileUploadMapper;


    @Override
    public int deleteById(Integer id) {
        return fileUploadMapper.deleteById(id);
    }

    @Override
    public int addPath(Integer id, String fangWenPath) {
        return fileUploadMapper.addPath(id,fangWenPath);
    }

    @Override
    public Attache selectById(Integer userId) {
        return fileUploadMapper.selectById(userId);
    }
}

