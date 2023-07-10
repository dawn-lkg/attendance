package com.example.attendance_wx.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.constants.SystemConstants;
import com.example.attendance_framework.entity.*;
import com.example.attendance_framework.entity.vo.CheckinStuVo;
import com.example.attendance_framework.entity.vo.CheckinVo;
import com.example.attendance_framework.service.CheckinService;
import com.example.attendance_framework.service.PublishCheckinService;
import com.example.attendance_framework.service.StudentService;
import com.example.attendance_framework.service.UploadService;
import com.example.attendance_framework.service.impl.FaceCheckServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (Checkin)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-02 16:32:06
 */
@RestController
@RequestMapping("checkin")
public class CheckinController {
    /**
     * 服务对象
     */
    @Resource
    private CheckinService checkinService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private PublishCheckinService publishCheckinService;
    @Autowired
    private FaceCheckServiceImpl faceCheckService;
    @Autowired
    private StudentService studentService;
    @PostMapping("checkFace")
    public Result checkFace(@RequestParam("file") MultipartFile file){
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
        Student student = studentService.getById(Integer.valueOf(BaseContext.getCurrentId()));
        Boolean aBoolean = faceCheckService.compareTokenFace(faceToken, student.getFaceToken());
        Map<String,Object> map=new HashMap<>();
        map.put("facePath",filePath);
        map.put("faceToken",faceToken);
        map.put("status",aBoolean);
        return Result.ok(map);
    }
    @PostMapping("attendance")
    @Transactional
    public Result attendance(@RequestBody Checkin checkin){
        System.out.println(checkin);
        Integer publishId = checkin.getPublishId();
        checkin.setStatus(SystemConstants.CHECKIN);
        checkin.setCheckinTime(new Date());
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,publishId);
        lambdaQueryWrapper.eq(Checkin::getStudentId,BaseContext.getCurrentId());
        System.out.println(checkinService.getOne(lambdaQueryWrapper));
        boolean update = checkinService.update(checkin, lambdaQueryWrapper);
        if(!update){
            return Result.error(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        boolean b = publishCheckinService.addCount(publishId);
        return Result.ok(b);
    }
    @GetMapping("/student/card")
    public Result getCard(){
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getStudentId,Integer.valueOf(BaseContext.getCurrentId()));
        lambdaQueryWrapper.ne(Checkin::getStatus,SystemConstants.NOT_CHECKED);
        List<CheckinVo> collect = checkinService.list(lambdaQueryWrapper).stream().map(item -> {
            return checkinService.getCheckVo(item);
        }).collect(Collectors.toList());
        return Result.ok(collect);
    }
    @GetMapping("publishCheckin")
    public Result getCheckin(Integer id){
        LambdaQueryWrapper<Checkin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Checkin::getPublishId,id);
        List<CheckinStuVo> collect = checkinService.list(lambdaQueryWrapper).stream().map(item -> {
            System.out.println(item);
            Student byId = studentService.getById(item.getStudentId());
            CheckinStuVo checkinStuVo = new CheckinStuVo();
            BeanUtils.copyProperties(item, checkinStuVo);
            checkinStuVo.setStudentName(byId.getName());
            System.out.println(checkinStuVo);
            return checkinStuVo;
        }).collect(Collectors.toList());
        return Result.ok(collect);
    }
}

