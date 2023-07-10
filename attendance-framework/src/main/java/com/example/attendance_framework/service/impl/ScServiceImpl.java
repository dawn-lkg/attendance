package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.ScDao;
import com.example.attendance_framework.entity.Sc;
import com.example.attendance_framework.service.ScService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Sc)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-26 21:10:56
 */
@Service("scService")
public class ScServiceImpl extends ServiceImpl<ScDao, Sc> implements ScService {

    @Override
    public boolean isJoinCourse(Integer stuId,Integer courseId) {
        LambdaQueryWrapper<Sc> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sc::getSutId,stuId);
        lambdaQueryWrapper.eq(Sc::getCourseId,courseId);
        Sc one = getOne(lambdaQueryWrapper);
        if(one==null){
            return false;
        }
        return true;
    }

    @Override
    public List<Sc> getCourseStu(Integer courseId) {
        LambdaQueryWrapper<Sc> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sc::getCourseId,courseId);
        List<Sc> list = list(lambdaQueryWrapper);
        return list;
    }
}

