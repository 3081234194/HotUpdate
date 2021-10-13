package com.eby.hotupdate.controller;


import com.eby.hotupdate.pojo.ProjectsInfo;
import com.eby.hotupdate.utils.RedisUtils;
import com.eby.hotupdate.vo.ProjectsInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/{url}")
    @ApiOperation(value = "获取项目详情")
    public ProjectsInfoVo getProjectsInfo(@PathVariable String url){
        ProjectsInfo projectsInfo = (ProjectsInfo) redisUtils.get("url:projectInfo:"+url);
        if(projectsInfo!=null){
            ProjectsInfoVo projectsInfoVo = new ProjectsInfoVo();
            projectsInfoVo.setDialog(projectsInfo.getDialog());
            projectsInfoVo.setForce(projectsInfo.getForce());
            projectsInfoVo.setMsg(projectsInfo.getMsg());
            projectsInfoVo.setVersion(projectsInfo.getVersion());
            return projectsInfoVo;
        }
        return null;
    }
}
