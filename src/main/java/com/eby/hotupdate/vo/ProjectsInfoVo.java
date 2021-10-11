package com.eby.hotupdate.vo;

import lombok.Data;

/*
* 用于项目更新的视图层
* */
@Data
public class ProjectsInfoVo {
    private String update_url;//热更新的url
    private String version;//版本号
    private Boolean dialog;//是否需要弹窗
    private String msg;//更新消息
    private Boolean force;//是否强制更新
}
