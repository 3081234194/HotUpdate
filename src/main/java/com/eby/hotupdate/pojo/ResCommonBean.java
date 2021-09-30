package com.eby.hotupdate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResCommonBean {
    private Integer code;
    private String msg;
    private Object data;

    /*
    * 正确信息返回
    * */
    //无数据返回
    public static ResCommonBean success(){
        return new ResCommonBean(ResCommonBeanEnum.success.getCode(),ResCommonBeanEnum.success.getMsg(), null);
    }
    //更改消息
    public static ResCommonBean success(String msg){
        return new ResCommonBean(ResCommonBeanEnum.success.getCode(),msg, null);
    }
    //有数据返回
    public static ResCommonBean successData(Object obj){
        return new ResCommonBean(ResCommonBeanEnum.success.getCode(),ResCommonBeanEnum.success.getMsg(),obj);
    }
    //有数据返回,且消息更改
    public static ResCommonBean success(String msg, Object obj){
        return new ResCommonBean(ResCommonBeanEnum.success.getCode(), msg, obj);
    }

    /*
    * 错误信息返回
    * */
    public static ResCommonBean error(){
        return new ResCommonBean(ResCommonBeanEnum.error.getCode(), ResCommonBeanEnum.error.getMsg(), null);
    }
    public static ResCommonBean error(String msg){
        return new ResCommonBean(ResCommonBeanEnum.error.getCode(), msg, null);
    }
}
