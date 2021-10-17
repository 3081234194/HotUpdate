package com.eby.hotupdate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eby.hotupdate.exception.GlobalException;
import com.eby.hotupdate.mapper.ProjectsInfoMapper;
import com.eby.hotupdate.mapper.ProjectsMapper;
import com.eby.hotupdate.mapper.UserMapper;
import com.eby.hotupdate.pojo.Projects;
import com.eby.hotupdate.pojo.ProjectsInfo;
import com.eby.hotupdate.pojo.ResCommonBeanEnum;
import com.eby.hotupdate.pojo.User;
import com.eby.hotupdate.reqdto.ProjectsReq;
import com.eby.hotupdate.reqdto.ProjectsUpdateReq;
import com.eby.hotupdate.service.IProjectsService;
import com.eby.hotupdate.utils.RedisUtils;
import com.eby.hotupdate.vo.ProjectsVo;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eby
 * @since 2021-09-30
 */
@Service
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects> implements IProjectsService{

    @Resource
    private ProjectsMapper projectsMapper;
    @Resource
    private ProjectsInfoMapper projectsInfoMapper;

    @Autowired
    private RedisUtils redisUtils;
    @Override
    public Projects insert(String token, String name, String description) {
        User user = (User) redisUtils.get("token:user:"+token);//获取user对象
        if(user==null) throw new GlobalException(ResCommonBeanEnum.unLogin);
        String url = UUID.randomUUID().toString().replace("-","");
        Projects projects = new Projects();
        projects.setBelong(user.getId());//设置所属
        projects.setDescription(""+description);//设置描述
        projects.setCreateTime(new Date());//创建日期
        projects.setName(name);//设置名字
        projects.setUrl(url);//设置url
        projects.setUpdateTime(new Date());
//        projects.set

        if(projectsMapper.insert(projects)==1) return projects;
        else throw new GlobalException(ResCommonBeanEnum.insertProject);
    }
    @Override
    public List<Projects> getAll(){
        List<Projects> projectsList = projectsMapper.selectList(null);
        if(!projectsList.isEmpty())return projectsList;
        return null;
    }
    //分页查询用户所属的项目
    @Override
    public List<ProjectsVo> getProjectsByUser(String token,int pageIndex){
        User user = (User) redisUtils.get("token:user:"+token);//获取user对象
        if(user==null) throw new GlobalException(ResCommonBeanEnum.unLogin);//判断是否登录

        List<Projects> projectsList = projectsMapper.selectPage(new Page<Projects>(pageIndex, 5),
                new QueryWrapper<Projects>().eq("belong", user.getId())).getRecords();
        if(!projectsList.isEmpty()){
            List<ProjectsVo> projectsVoList = new ArrayList<>();
            for (Projects projects : projectsList) {
                ProjectsVo projectsVo = new ProjectsVo();
                projectsVo.setId(projects.getId());
                projectsVo.setName(projects.getName());
                projectsVo.setDescription(projects.getDescription());
                projectsVo.setUrl(projects.getUrl());
                projectsVo.setCreate_time(projects.getCreateTime());
                projectsVo.setUpdate_time(projects.getUpdateTime());
                projectsVoList.add(projectsVo);
            }
            return projectsVoList;
        }else throw new GlobalException(ResCommonBeanEnum.emptyProject);
    }



    //@Transactional
    @Override
    public Boolean deleteTo(String token,Long id){
        User user = (User) redisUtils.get("token:user:"+token);//获取user对象
        if(user==null) throw new GlobalException(ResCommonBeanEnum.unLogin);//判断是否登录
        else{
            //判断项目所属人是否为本用户
            Projects projects = projectsMapper.selectOne(new QueryWrapper<Projects>().eq("id",id).eq("belong",user.getId()));
            if(projects==null)throw new GlobalException(ResCommonBeanEnum.illegalDelete);//如果查询为空则返回
            else{
                    //项目和项目详情id是一样的
                    int res1 = projectsMapper.deleteById(id);
                    //int res2 = projectsInfoMapper.deleteById(id);
                    if(res1==1){
                        redisUtils.del("url:projectInfo:"+projects.getUrl());
                        return true;
                    }
                }
        }
        return false;
    }
    @Override
    public Boolean updateTo(String token, ProjectsUpdateReq projectsUpdateReq) {
        User user = (User) redisUtils.get("token:user:"+token);//获取user对象
        if(user==null) throw new GlobalException(ResCommonBeanEnum.unLogin);//判断是否登录
        else{
            //判断项目所属人是否为本用户
            Projects projects = projectsMapper.selectById(projectsUpdateReq.getId());
            if(projects==null)throw new GlobalException(ResCommonBeanEnum.illegalUpdate);//如果查询为空则返回
            else{
                if(projects.getBelong().equals(user.getId())){//所属匹配则进行更改
                    //能改的就这三项
                    projects.setUpdateTime(new Date());
                    projects.setName(projectsUpdateReq.getName());
                    projects.setDescription(projectsUpdateReq.getDescription());
                    return projectsMapper.updateById(projects)==1?true:false;
                }
            }
        }
        return false;
    }
}
