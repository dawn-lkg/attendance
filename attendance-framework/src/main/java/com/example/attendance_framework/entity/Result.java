package com.example.attendance_framework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈黎明
 * @since 2022/10/22 21:39
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Result<T>{
    private T data;
    private Integer code;
    private String msg;
    public Result(){
        this.code=AppHttpCodeEnum.SUCCESS.getCode();
        this.msg=AppHttpCodeEnum.SUCCESS.getMsg();
    }
    public Result(Integer code, T data){
        this.code=code;
        this.data=data;
    }
    public Result(Integer code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public Result(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public static <T> Result ok(){
        Result<T> result = new Result<>();
        return result;
    }
    public static<T> Result ok(T data,String msg,Integer code){
        Result<T> result = new Result<>(code,msg,data);
        return result;
    }
    public static<T> Result ok(T data,AppHttpCodeEnum appHttpCodeEnum){
        Result<T> result = new Result<>(appHttpCodeEnum.getCode(),appHttpCodeEnum.getMsg(),data);
        return  result;
    }
    public static<T> Result ok(T data){
        Result<T> result = new Result<>();
        result.setData(data);
        return  result;
    }
    public static<T> Result error(T data,String msg,Integer code){
        Result<T> result = new Result<>(code,msg,data);
        return result;
    }
    public static<T> Result error(AppHttpCodeEnum codeEnum){
        Result<T> result = new Result<>(codeEnum.getCode(),codeEnum.getMsg());
        return result;
    }
    public static<T> Result error(T msg,AppHttpCodeEnum appHttpCodeEnum){
        Result<T> result = new Result<T>(appHttpCodeEnum.getCode(),appHttpCodeEnum.getMsg());
        return  result;
    }
}
