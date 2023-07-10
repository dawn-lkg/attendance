package com.example.attendance_admin.controller;




import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.service.ClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Class)表控制层
 *
 * @author 陈黎明
 * @since 2023-05-05 22:25:48
 */
@RestController
@RequestMapping("class")
public class ClassController{
    /**
     * 服务对象
     */
    @Resource
    private ClassService classService;
    @GetMapping("all")
    public Result getAll(){
        return Result.ok(classService.list());
    }
}

