package com.ai_education.interceptor;

import com.ai_education.context.BaseContext;
import com.ai_education.properties.JwtProperties;
import com.ai_education.result.Result;
import com.ai_education.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * jwt令牌校验的拦截器
 */
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("当前线程在JWT方法的id:"+Thread.currentThread().getId());

        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)){
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getTokenName());
        Result result = new Result();
        // token为空,未登录时的处理逻辑
        if (token == null || token.isEmpty() || token.equals("{{token}}")) {
            // 返回一个特定的错误响应，比如：令牌缺失或无效
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置为 401
            result.setCode(401);
            result.setData(null);
            result.setMsg("token为空,未登录");
            String json = new ObjectMapper().writeValueAsString(result);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }

        //2、校验令牌
        try{
            System.out.println("jwt检验："+token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            System.out.println(claims);
            String identity = claims.get("identity").toString();
            String id;
            if ("0".equals(identity)) { // 学生身份
                id = claims.get("studentId").toString();
                System.out.println("当前学生id："+id);
            } else if ("1".equals(identity)) { // 老师身份
                id = claims.get("teacherId").toString();
                System.out.println("当前老师id："+id);
            } else {
                throw new RuntimeException("未知身份类型");
            }

            //设置上下文
            BaseContext.setCurrentId(id);
            BaseContext.setCurrentRole(identity);

            //3、根据身份和请求URL进行权限验证
            String requestURI = request.getRequestURI();
            if (!checkPermission(requestURI, identity)) {
                //权限不足，返回401
                result.setCode(401);
                result.setData(null);
                result.setMsg("权限不足");
                String json = new ObjectMapper().writeValueAsString(result);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
                return false;
            }

            //通过，放行
            return true;
        } catch (Exception ex){
            //4、不通过，响应401状态码，token失效
            result.setCode(401);
            result.setData(null);
            result.setMsg("token失效");
            String json = new ObjectMapper().writeValueAsString(result);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }
    }

    //清理线程
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.clear();
    }


    /**
     * 检查权限
     * @param requestURI 当前请求的URI
     * @param identity 当前用户的身份
     * @return 是否有权限访问
     */
    private boolean checkPermission(String requestURI, String identity) {
        // 公共接口，可以被学生和老师访问
        if (isCommonInterface(requestURI)) {
            return true;
        }

        // 学生只能访问 /aiEducation/student/** 路径
        if ("0".equals(identity) && requestURI.startsWith("/aiEducation/teacher")) {
            return false;
        }
        // 老师只能访问 /aiEducation/teacher/** 路径
        if ("1".equals(identity) && requestURI.startsWith("/aiEducation/student")) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为公共接口
     * @param requestURI 当前请求的URI
     * @return 是否为公共接口
     */
    private boolean isCommonInterface(String requestURI) {
        // 列出所有公共接口的前缀或具体路径
        return requestURI.startsWith("/aiEducation/common") ||
                requestURI.equals("/aiEducation/course");
    }
}
