package com.example.attendance_wx.controller;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.UploadService;
import com.example.attendance_framework.service.impl.FaceCheckServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenliming
 * @date 2023/5/1 14:57
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MyTask myTask;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private FaceCheckServiceImpl faceCheckService;
    @Autowired
    private UploadService uploadService;
    @GetMapping
    public Result getTest(){
        taskScheduler.scheduleAtFixedRate(myTask::run,25000);
        return Result.ok("开始");
    }
    @PostMapping("1")
    public Result getTest1(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = (String) uploadService.uploadImage(file);
        System.out.println(filePath);
        JSONObject jsonObject = (JSONObject) faceCheckService.getFaceToken(filePath);
        Integer face_num = jsonObject.get("face_num",Integer.class);
        System.out.println(jsonObject);
        System.out.println(face_num);
        if(face_num==0){
            return Result.error(false,"没有检测到人脸",500);
        }
        JSONArray faces = jsonObject.get("faces", JSONArray.class);
        Object face_token = faces.get(0, JSONObject.class).get("face_token");

        return Result.ok(jsonObject);
    }
    @GetMapping("time")
    public Result getTest2(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month =calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        System.out.println(year);
        CronUtil.setMatchSecond(true);
        String format = String.format("%d %d %d %d %d ? %d", seconds, minutes + 1, hours, day, month+1, year);
        System.out.println(format);
        CronUtil.schedule(format, new Task() {
            @Override
            public void execute() {
                System.out.println("成功");
                System.out.println(new Date());
                CronUtil.stop();
            }
        });
        CronUtil.start();
        return Result.ok();
    }
}
