package com.example.attendance_framework.service.impl;

import com.example.attendance_framework.entity.AppHttpCodeEnum;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author chenliming
 * @date 2023/4/19 21:50
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {
    @Value("${upload.image.path}")
    private String ImageBase;
    @Override
    public Object uploadImage(MultipartFile file) {
        if(file.isEmpty()) return AppHttpCodeEnum.FILE_NOT_NULL;
        File dir=new File(ImageBase);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String fileName = UUID.randomUUID() + suffix;
        System.out.println(fileName);
        try {
            file.transferTo(new File(ImageBase+fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileName;
    }
}
