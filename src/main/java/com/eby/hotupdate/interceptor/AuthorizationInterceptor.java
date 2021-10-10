package com.eby.hotupdate.interceptor;

import com.alibaba.fastjson.JSON;
import com.eby.hotupdate.annotion.AuthToken;
import com.eby.hotupdate.pojo.ResCommonBean;
import com.eby.hotupdate.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final String tokenName =  "Token";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader(tokenName);//获取到token
            //PrintWriter writer = response.getWriter();
            if(token!=null&&token.length()!=0){
                Boolean hasToken = redisTemplate.hasKey("token:user:"+token);
                log.info("认证结果："+hasToken);
                if(!hasToken){
                    //writer.println("illegal request");
                    returnErrorResponse(response,ResCommonBean.error("用户token已过期"));
                    return false;
                }
            }else{
                //writer.println("null token");
                returnErrorResponse(response,ResCommonBean.error("token为空"));
                return false;
            }
        }
        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, ResCommonBean result) throws IOException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
