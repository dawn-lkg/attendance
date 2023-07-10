package com.example.attendance_admin.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.UploadService;
import com.example.attendance_framework.service.impl.FaceCheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliming
 * @date 2023/4/19 22:11
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private FaceCheckServiceImpl faceCheckService;
    @PostMapping("image")
    public Result uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return Result.ok(uploadService.uploadImage(file));
    }
    @PostMapping("faceToken")
    public Result getFaceToken(@RequestParam("file") MultipartFile file){
        String filePath = (String) uploadService.uploadImage(file);
        JSONObject jsonObject = (JSONObject) faceCheckService.getFaceToken(filePath);
        Integer face_num = jsonObject.get("face_num",Integer.class);
        System.out.println(jsonObject);
        System.out.println(face_num);
        if(face_num==0){
            return Result.error(false,"没有检测到人脸",500);
        }
        JSONArray faces = jsonObject.get("faces", JSONArray.class);
        String faceToken =(String) faces.get(0, JSONObject.class).get("face_token");
        return Result.ok(faceToken);
    }
}
