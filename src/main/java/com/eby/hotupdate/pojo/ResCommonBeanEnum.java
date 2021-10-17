package com.eby.hotupdate.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResCommonBeanEnum {
    /*
    * 10x 用户相关
    * 20x 业务正常，正在处理
    * 30x 项目异常相关
    * 40x 参数校验异常
    * 50x 服务端异常
    * 80x 非法尝试
    * */
    unLogin(100,"未登录或token已过期"),
    emptyProject(300,"项目为空"),
    insertProject(301,"插入项目异常"),
    success(200,"SUCCESS"),
    error(500,"服务端故障"),
    illegalDelete(801,"尝试非法删除他人项目，账号已被记录，请规范使用"),
    illegalUpdate(802,"尝试非法更改他人项目，账号已被记录，请规范使用"),
    unauthorized(800,"为登录或未授权访问");

    private Integer code;
    private String msg;
}
