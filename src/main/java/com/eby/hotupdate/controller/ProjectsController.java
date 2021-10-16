package com.eby.hotupdate.controller;


import com.eby.hotupdate.pojo.Projects;
import com.eby.hotupdate.pojo.ProjectsInfo;
import com.eby.hotupdate.pojo.ResCommonBean;
import com.eby.hotupdate.reqdto.ProjectsInfoReq;
import com.eby.hotupdate.reqdto.ProjectsReq;
import com.eby.hotupdate.service.impl.ProjectsInfoServiceImpl;
import com.eby.hotupdate.service.impl.ProjectsServiceImpl;
import com.eby.hotupdate.utils.RedisUtils;
import com.eby.hotupdate.vo.ProjectsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eby
 * @since 2021-09-30
 */
@RestController
@Api(tags = "项目管理")
@RequestMapping("/api/projects")
public class ProjectsController implements InitializingBean {

    @Autowired
    private ProjectsServiceImpl projectsService;
    @Autowired
    private ProjectsInfoServiceImpl projectsInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @ApiOperation(value = "新建项目，包含项目详情")
    @PostMapping("/insert")
    public ResCommonBean insert(@RequestHeader("Token") String token, ProjectsReq projectsReq,ProjectsInfoReq projectsInfoReq){
        //这里获取到的projects已经由mb-p注入了id
        Projects projects = projectsService.insert(token,projectsReq.getName(),projectsReq.getDescription());
        if(projects==null) return ResCommonBean.error("新建项目失败");//项目为空插入失败
        ProjectsInfo projectsInfo = projectsInfoService.insert(projects.getId(),projectsInfoReq);
        if(projectsInfo!=null){
            //往redis中插入数据
            redisUtils.set("url:projectInfo:"+projects.getUrl(),projectsInfo);
            return ResCommonBean.successData(projects.getUrl());
        }

        return ResCommonBean.error();
    }

    @ApiOperation(value = "修改项目")
    @PostMapping("/update")
    public ResCommonBean update(@RequestHeader("Token") String token, ProjectsVo projectsVo){
        Boolean res = projectsService.updateTo(token, projectsVo);
        if(res)return  ResCommonBean.success();
        return ResCommonBean.error("修改失败");
    }


    @ApiOperation(value = "返回用户的项目list--分页--swagger中返回的id不对")
    @PostMapping("/queryProjects")
    public ResCommonBean getProjectVoByUser(@RequestHeader("Token") String token,int pageIndex){
        List<ProjectsVo> res= projectsService.getProjectsByUser(token,pageIndex);
        if(res!=null) {
            System.out.println(res.toArray()[0]);
            return ResCommonBean.successData(res);
        }
        return ResCommonBean.error("未查询到结果");
    }

    @ApiOperation(value = "删除用户的项目")
    @PostMapping(value = "/delete")
    public ResCommonBean deleteProjectsById(@RequestHeader("Token") String token,Long id){
        if(projectsService.deleteTo(token,id))return ResCommonBean.success("操作成功");
        return ResCommonBean.error();
    }
    /**
     * 系统初始化，把所有的链接加入到redis缓存中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<Projects> projectsList = projectsService.getAll();
        if(projectsList==null) return;
        for (Projects projects : projectsList) {
            ProjectsInfo projectsInfo = projectsInfoService.queryTo(projects.getId());
            redisUtils.set("url:projectInfo:"+projects.getUrl(),projectsInfo);
        }
    }
}
