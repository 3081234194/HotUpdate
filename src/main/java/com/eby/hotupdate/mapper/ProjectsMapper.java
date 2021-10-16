package com.eby.hotupdate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eby.hotupdate.pojo.Projects;
import com.eby.hotupdate.vo.ProjectsVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eby
 * @since 2021-09-30
 */
public interface ProjectsMapper extends BaseMapper<Projects> {
    /**
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * 注意!!: 如果入参是有多个,需要加注解指定参数名才能在xml中取值
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @return 分页对象
     */
//    IPage<Projects> selectPageVo(Page page);
}
