package com.kkb.dao;

import com.kkb.bean.Drugpeople;
import com.kkb.bean.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author HSP
 */
@MapperScan
public interface HeMapper {
    /**
     * 根据部分条件查询用户ID
     * @return
     */
    Integer selectUserLogin(User user);

    /**
     * 根据用户信id查询用户状态
     * @param user
     * @return
     */
    Integer selectUserState(User user);

    /**
     * 根据ID查询密码
     * @param user
     * @return
     */
    String selectUserPassword(User user);

    /**
     * 根据ID修改密码
     * @param user 传入带ID和password的User对象
     */
    void updateUserPassword(User user);

    /**
     * 查询用户名称
     * @param id
     * @return
     */
    String selectUserName(Integer id);
}
