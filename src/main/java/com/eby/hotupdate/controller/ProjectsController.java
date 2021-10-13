package com.eby.hotupdate.controller;


import com.eby.hotupdate.pojo.Projects;
import com.eby.hotupdate.pojo.ResCommonBean;
import com.eby.hotupdate.reqdto.ProjectsInfoReq;
import com.eby.hotupdate.reqdto.ProjectsReq;
import com.eby.hotupdate.service.impl.ProjectsInfoServiceImpl;
import com.eby.hotupdate.service.impl.ProjectsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/insert")
    public ResCommonBean insert(@RequestHeader("Token") String token, ProjectsReq projectsReq,ProjectsInfoReq projectsInfoReq){
        //这里获取到的projects已经由mb-p注入了id
        Projects projects = projectsService.insert(token,projectsReq.getName(),projectsReq.getDescription());
        if(projects==null) return ResCommonBean.error("新建项目失败");//项目为空插入失败
        if(projectsInfoService.insert(projects.getId(),projectsInfoReq)) return ResCommonBean.successData(projects.getUrl());
        return ResCommonBean.error();
    }
    /**
     * 系统初始化，把所有的链接加入到redis缓存中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
