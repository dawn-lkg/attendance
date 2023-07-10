package com.example.attendance_admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenliming
 * @date 2023/1/31 15:05
 */
@Slf4j
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {
    private final String TIME_CREATE="createTime";
    private final String TIME_UPDATE="updateTime";
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(TIME_CREATE,new Date(),metaObject);
        this.setFieldValByName(TIME_UPDATE,new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(TIME_UPDATE,new Date(),metaObject);
    }
}
