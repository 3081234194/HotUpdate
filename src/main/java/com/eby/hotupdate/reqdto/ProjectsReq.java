package com.eby.hotupdate.reqdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目参数对象
 */
@Data
@AllArgsConstructor
@ApiModel
public class ProjectsReq {
    @ApiModelProperty(value="项目名")
    private String name;
    @ApiModelProperty(value = "项目描述")
    private String description;
}
