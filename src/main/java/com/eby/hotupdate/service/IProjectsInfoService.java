package com.eby.hotupdate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eby.hotupdate.pojo.ProjectsInfo;
import com.eby.hotupdate.reqdto.ProjectsInfoReq;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eby
 * @since 2021-10-11
 */
public interface IProjectsInfoService extends IService<ProjectsInfo> {

    //往数据库中添加项目详情
    Boolean insert(Long id, ProjectsInfoReq projectsInfoReq);
    //更改项目详情
    void update(ProjectsInfo projectsInfo);
}
