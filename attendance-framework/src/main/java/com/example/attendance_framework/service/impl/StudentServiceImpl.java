package com.example.attendance_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendance_framework.dao.StudentDao;
import com.example.attendance_framework.entity.Class;
import com.example.attendance_framework.entity.College;
import com.example.attendance_framework.entity.PoliticalStatus;
import com.example.attendance_framework.entity.Student;
import com.example.attendance_framework.entity.vo.StudentListVo;
import com.example.attendance_framework.entity.vo.StudentVo;
import com.example.attendance_framework.service.ClassService;
import com.example.attendance_framework.service.CollegeService;
import com.example.attendance_framework.service.PoliticalStatusService;
import com.example.attendance_framework.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (Student)表服务实现类
 *
 * @author 陈黎明
 * @since 2023-04-22 21:28:23
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Autowired
    ClassService classService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    PoliticalStatusService politicalStatusService;
    @Override
    public Page list(Integer pageNum, Integer pageSize, String query) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(query!=null,Student::getName,query);
        Page<Student> page = page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        return page;
    }

    @Override
    public Student getStudentNoPassword(String username, String password) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getId,username);
        lambdaQueryWrapper.eq(Student::getPassword,password);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public StudentVo getStudent(Integer id) {
        Student student = getById(id);
        College college = collegeService.getById(student.getCollegeId());
        Class aClass = classService.getById(student.getClassId());
        PoliticalStatus politicalStatus = politicalStatusService.getById(student.getPoliticalStatusId());
        StudentVo studentVo=new StudentVo();
        String s[]=new String[]{"id"};
        BeanUtils.copyProperties(student,studentVo);
        BeanUtils.copyProperties(college,studentVo,s);
        BeanUtils.copyProperties(aClass,studentVo,s);
        BeanUtils.copyProperties(politicalStatus,studentVo,s);
        return studentVo;
    }

    @Override
    public List<StudentListVo> getStudentListVo(List<Student> list) {
        List<StudentListVo> collect = list.stream().map(item -> {
            StudentListVo studentListVo = new StudentListVo();
            BeanUtils.copyProperties(item, studentListVo);
            College college = collegeService.getById(item.getCollegeId());
            Class aClass = classService.getById(item.getClassId());
            PoliticalStatus politicalStatus = politicalStatusService.getById(item.getPoliticalStatusId());
            studentListVo.setCollegeName(college.getCollegeName());
            studentListVo.setClassName(aClass.getClassName());
            studentListVo.setPoliticalName(politicalStatus.getPoliticalName());
            return studentListVo;
        }).collect(Collectors.toList());
        return collect;
    }
}

