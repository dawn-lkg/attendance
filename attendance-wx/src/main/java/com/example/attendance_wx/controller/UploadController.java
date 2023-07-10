package com.example.attendance_wx.controller;

import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author chenliming
 * @date 2023/4/19 22:11
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping("image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return Result.ok(uploadService.uploadImage(file));
    }
    @PostMapping("test")
    public Result test(@RequestParam("file") MultipartFile file) throws IOException {
        return Result.ok(file.getBytes());
    }
}
