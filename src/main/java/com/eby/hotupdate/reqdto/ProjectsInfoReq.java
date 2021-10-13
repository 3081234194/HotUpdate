package com.eby.hotupdate.reqdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目详情参数
 */
@Data
@AllArgsConstructor
@ApiModel
public class ProjectsInfoReq {
    @ApiModelProperty(value = "项目详情版本号")
    private String version;//版本号
    @ApiModelProperty(value = "项目详情是否需要弹窗")
    private Boolean dialog;//是否需要弹窗
    @ApiModelProperty(value = "项目详情更新消息")
    private String msg;//更新消息
    @ApiModelProperty(value = "项目详情是否强制更新")
    private Boolean force;//是否强制更新
    @ApiModelProperty(value = "项目详情更新链接")
    private String update_url;
}
