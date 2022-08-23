package com.nxg.quickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nxg.quickstart.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/10
 * @apiNote
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 如果映射的接口方法有2个参数需要@Param定义参数名，定义参数名后，映射文件中使用p.属性 c.属性，具体访问
     *
     * @param page
     * @param conditioin
     * @return
     */
    public IPage<User> selectUserByPage2(@Param("p") IPage<User> page, @Param("c") User conditioin);
    public List<User> selectUserByPage3(User conditioin);
}