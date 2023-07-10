package com.example.attendance_wx.filter;

import com.alibaba.fastjson.JSON;
import com.example.attendance_framework.common.BaseContext;
import com.example.attendance_framework.entity.AppHttpCodeEnum;
import com.example.attendance_framework.entity.Result;
import com.example.attendance_framework.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
//        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type","application/json;charset=utf-8");
        String requestURI=request.getRequestURI();
        log.info("请求uri:{}",requestURI);
        String[] urls=new String[]{
                "/login/**",
                "/images/**",
                "/upload/**",
                "/test/**"
                //"/**"
        };
        boolean check=check(urls,requestURI);
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        String token = request.getHeader("token");
        if(token==null){
            Result result = Result.error(AppHttpCodeEnum.NEED_LOGIN);
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        log.info("token:{}",token);
        String no=null;
        try {
            no=JwtTokenUtil.parseToken(token);
        }catch (Exception e) {
            e.printStackTrace();
            Result result = Result.error(AppHttpCodeEnum.NEED_LOGIN);
            response.getWriter().write(JSON.toJSONString(result));
            return;
        }
        if(no==null){
            log.info("未登录");
            response.getWriter().write(JSON.toJSONString(Result.error(AppHttpCodeEnum.NEED_LOGIN)));
            return;
        }
        log.info("用户已登录,id:{}",no);
        BaseContext.setCurrentId(no);
        filterChain.doFilter(request,response);
        return;
    }
    public boolean check(String[] urls,String requestURI){
        for(String url:urls){
            boolean math=PATH_MATCHER.match(url,requestURI);
            if(math) {
                return true;
            }
        }
        return false;
    }
}
