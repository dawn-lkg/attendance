package com.example.attendance_framework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author chenliming
 * @date 2023/1/16 20:12
 */
@Service
public interface faceCheckService {

    Boolean comparePathFace(String imageFile1,String imageFile2);
    Boolean compareTokenFace(String token1,String token2);
    Object  getFaceToken(String filePath);
}
