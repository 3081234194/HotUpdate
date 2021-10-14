package com.eby.hotupdate.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class ProjectsVo {
    @ApiModelProperty("项目id")
    private Long id;
    @ApiModelProperty("项目名")
    private String name;
    @ApiModelProperty("项目描述")
    private String description;
    @ApiModelProperty("项目链接")
    private String url;
    @ApiModelProperty("创建时间")
    private Date create_time;
    @ApiModelProperty("最近一次更新时间")
    private Date update_time;
}
