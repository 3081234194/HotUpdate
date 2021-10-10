package com.eby.hotupdate.controller;


import com.eby.hotupdate.pojo.ResCommonBean;
import com.eby.hotupdate.service.impl.ProjectsServiceImpl;
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
@RequestMapping("/api/projects")
public class ProjectsController implements InitializingBean {

    @Autowired
    private ProjectsServiceImpl projectsService;
    @PostMapping("/insert")
    public ResCommonBean insert(@RequestHeader("Token") String token,String name,String description){
        String res = projectsService.insert(token,name,description);
        if(res!=null) return ResCommonBean.successData(res);
        return ResCommonBean.error();
    }

    /**
     * 系统初始化，把所有的链接加入到redis中
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
