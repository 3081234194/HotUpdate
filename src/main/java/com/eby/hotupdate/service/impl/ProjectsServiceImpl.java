package com.eby.hotupdate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eby.hotupdate.mapper.ProjectsMapper;
import com.eby.hotupdate.mapper.UserMapper;
import com.eby.hotupdate.pojo.Projects;
import com.eby.hotupdate.pojo.ProjectsInfo;
import com.eby.hotupdate.pojo.User;
import com.eby.hotupdate.reqdto.ProjectsReq;
import com.eby.hotupdate.service.IProjectsService;
import com.eby.hotupdate.utils.RedisUtils;
import com.eby.hotupdate.vo.ProjectsVo;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public Projects insert(String token, String name, String description) {
        User user = (User) redisUtils.get("token:user:"+token);//获取user对象
        if(user==null) return null;
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
        return null;
    }
    @Override
    public List<Projects> getAll(){
        List<Projects> projectsList = projectsMapper.selectList(null);
        if(!projectsList.isEmpty())return projectsList;
        return null;
    }

    @Override
    public Boolean updateTo(String token, ProjectsVo projectsVo) {
        User user = (User) redisUtils.get("token:user:"+token);//获取user对象
        if(user==null) return false;//判断是否登录
        else{
            //判断项目所属人是否为本用户
            Projects projects = projectsMapper.selectById(projectsVo.getId());
            if(projects==null)return false;//如果查询为空则返回
            else{
                if(projects.getBelong().equals(user.getId())){//所属匹配则进行更改
                    //能改的就这三项
                    projects.setUpdateTime(new Date());
                    projects.setName(projectsVo.getName());
                    projects.setDescription(projectsVo.getDescription());
                    return projectsMapper.updateById(projects)==1?true:false;
                }
            }
        }
        return false;
    }
}
