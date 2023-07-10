package com.example.attendance_framework.service;

import com.example.attendance_framework.entity.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenliming
 * @date 2023/4/19 21:47
 */
@Service
public interface UploadService {
    Object uploadImage(MultipartFile file);
}
