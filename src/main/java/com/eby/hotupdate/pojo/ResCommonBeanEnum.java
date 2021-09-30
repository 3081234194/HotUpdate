package com.eby.hotupdate.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResCommonBeanEnum {
    success(200,"SUCCESS"),
    error(500,"服务端故障");
    private Integer code;
    private String msg;
}
