package com.eby.hotupdate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eby.hotupdate.mapper.ProjectsInfoMapper;
import com.eby.hotupdate.pojo.ProjectsInfo;
import com.eby.hotupdate.reqdto.ProjectsInfoReq;
import com.eby.hotupdate.service.IProjectsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eby
 * @since 2021-10-11
 */
@Service
public class ProjectsInfoServiceImpl extends ServiceImpl<ProjectsInfoMapper, ProjectsInfo> implements IProjectsInfoService {
    @Resource
    private ProjectsInfoMapper projectsInfoMapper;
    //往数据库中插入新项目详情
    @Override
    public Boolean insert(Long id,ProjectsInfoReq projectsInfoReq) {
        ProjectsInfo projectsInfo = new ProjectsInfo();
        projectsInfo.setId(id);
        projectsInfo.setDialog(projectsInfoReq.getDialog());
        projectsInfo.setForce(projectsInfoReq.getForce());
        projectsInfo.setMsg(projectsInfoReq.getMsg());
        projectsInfo.setVersion(projectsInfoReq.getVersion());
        projectsInfo.setUpdateUrl(projectsInfoReq.getUpdate_url());
        System.out.println(projectsInfo);
        int res = projectsInfoMapper.insert(projectsInfo);
        return res==1?true:false;//更改记录是否是一行,不是则插入失败
    }

    @Override
    public void update(ProjectsInfo projectsInfo) {
        //todo:这里传入id就能修改，还需要判断一下是不是属于用户的项目--在这里判断还是在外面判断
        projectsInfoMapper.updateById(projectsInfo);
    }
}
