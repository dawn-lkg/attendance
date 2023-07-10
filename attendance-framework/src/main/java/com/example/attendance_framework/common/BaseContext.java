package com.example.attendance_framework.common;

/**
 * @author chenliming
 * @date 2023/1/9 20:25
 */

public class BaseContext {
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(String id){
        threadLocal.set(id);
    }
    public static String getCurrentId(){
        return threadLocal.get();
    }
}
