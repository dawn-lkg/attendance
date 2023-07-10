package com.example.attendance_framework.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.attendance_framework.service.faceCheckService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliming
 * @date 2023/1/16 20:12
 */
@Service
public class FaceCheckServiceImpl implements faceCheckService {
    @Value("${face.api_key}")
    String api_key;
    @Value("${face.api_secret}")
    String api_secret;
    @Value("${upload.path}")
    String path;
    @Override
    public Boolean comparePathFace(String imageFile1, String imageFile2) {
        Map<String, Object> map = getMap();
        map.put("image_file1",FileUtil.file(path+imageFile1));
        map.put("image_file2",FileUtil.file(path+imageFile2));
        String post = HttpUtil.post("https://api-cn.faceplusplus.com/facepp/v3/compare", map);
        JSONObject jsonObject = JSONUtil.parseObj(post);
        Double confidence =jsonObject.get("confidence",Double.class);
        if(confidence>80){
            return true;
        }
        return false;
    }

    @Override
    public Boolean compareTokenFace(String token1, String token2) {
        Map<String, Object> map = getMap();
        map.put("face_token1",token1);
        map.put("face_token2",token2);
        String post = HttpUtil.post("https://api-cn.faceplusplus.com/facepp/v3/compare", map);
        JSONObject jsonObject = JSONUtil.parseObj(post);
        System.out.println(jsonObject);
        Double confidence =jsonObject.get("confidence",Double.class);
        if(confidence>80){
            return true;
        }
        return false;
    }
    @Override
    public Object getFaceToken(String filePath){
        Map<String, Object> map = getMap();
        map.put("image_file",FileUtil.file(path+filePath));
        String post = HttpUtil.post("https://api-cn.faceplusplus.com/facepp/v3/detect", map);
        JSONObject jsonObject = JSONUtil.parseObj(post);
//        System.out.println(jsonObject);
//        JSONArray faces = jsonObject.get("faces", JSONArray.class);
//        JSONObject json = faces.get(0, JSONObject.class);
//        System.out.println(json.get("face_token"));
        return jsonObject;
    }
    @NotNull
    public Map<String,Object> getMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("api_key",api_key);
        map.put("api_secret",api_secret);
        return map;
    }
}
