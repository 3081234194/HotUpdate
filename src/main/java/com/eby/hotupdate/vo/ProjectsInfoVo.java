package com.eby.hotupdate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
* 用于项目更新的视图层
* */
@Data
@ApiModel
public class ProjectsInfoVo {
    @ApiModelProperty("项目详情id")
    private Long id;
    @ApiModelProperty("更新文件链接")
    private String update_url;//热更新的url
    @ApiModelProperty("升级版本号")
    private String version;//版本号
    @ApiModelProperty("是否需要弹窗")
    private Boolean dialog;//是否需要弹窗
    @ApiModelProperty("更新消息")
    private String msg;//更新消息
    @ApiModelProperty("是否强制更新")
    private Boolean force;//是否强制更新
}
