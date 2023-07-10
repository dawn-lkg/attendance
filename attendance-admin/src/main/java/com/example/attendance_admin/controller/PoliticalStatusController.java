package com.example.attendance_admin.controller;



import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.PoliticalStatusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (PoliticalStatus)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-05 21:56:04
 */
@RestController
@RequestMapping("politicalStatus")
public class PoliticalStatusController{
    /**
     * 服务对象
     */
    @Resource
    private PoliticalStatusService politicalStatusService;
    @GetMapping("all")
    public Result getAll(){
        return Result.ok(politicalStatusService.list());
    }
}

