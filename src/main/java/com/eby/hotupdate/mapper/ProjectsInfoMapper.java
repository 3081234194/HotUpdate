package com.eby.hotupdate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eby.hotupdate.pojo.ProjectsInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eby
 * @since 2021-10-11
 */
public interface ProjectsInfoMapper extends BaseMapper<ProjectsInfo> {


    @Select("SELECT id,update_url,version,dialog,msg,forces AS `force` FROM projects_info WHERE id=#{id}")
    ProjectsInfo selectById(@Param("id") Serializable id);
}
