package com.kkb.service.hsp.usermanager;

import com.kkb.bean.User;
import com.kkb.dao.HeMapper;
import com.kkb.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {



    /**
     * 根据账号密码查询用户ID
     * @return ID
     */
    Integer queryByParam(User user);

    /**
     * 根据User的ID查状态
     * @param user
     * @return
     */
    Integer queryById(User user);

    /**
     * 查询用户密码
     * @param user 根据ID查密码
     * @return
     */
    String selectUserPassword(User user);

    /**
     * 根据ID更改用户密码
     * @param user
     */
    void updateUserPassword(User user);

    /**
     * 根据ID查询用户名
     * @param id 用户ID
     * @return
     */
    String selectUserName(Integer id);

}
