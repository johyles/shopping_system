package com.zte.shopping.service;


import com.zte.shopping.entity.Attache;

public interface FileUploadService {


    public int deleteById(Integer id);

    public int addPath(Integer id, String fangWenPath);

    public Attache selectById(Integer userId);
}
