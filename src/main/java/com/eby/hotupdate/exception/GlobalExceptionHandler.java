package com.eby.hotupdate.exception;


import com.eby.hotupdate.pojo.ResCommonBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResCommonBean exceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException exception = (GlobalException) e;
            return ResCommonBean.send(exception.getResCommonBeanEnum());
        }

        return ResCommonBean.error();
    }
}
