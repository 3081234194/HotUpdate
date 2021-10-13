package com.eby.hotupdate.controller;


import com.eby.hotupdate.pojo.ProjectsInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eby
 * @since 2021-10-11
 */
@RestController
@Api(tags = "项目详情")
@RequestMapping("/api/projects-info")
public class ProjectsInfoController {

    @RequestMapping("/{url}")
    @ApiOperation(value = "获取项目详情")
    public ProjectsInfo getProjectsInfo(@PathVariable String url){
        System.out.println(url);
        return null;
    }
}
