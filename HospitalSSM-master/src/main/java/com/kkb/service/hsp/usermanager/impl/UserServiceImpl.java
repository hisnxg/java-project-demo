package com.kkb.service.hsp.usermanager.impl;

import com.kkb.bean.User;
import com.kkb.dao.HeMapper;
import com.kkb.dao.UserMapper;
import com.kkb.service.hsp.usermanager.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HeMapper userMapper;

    /**
     * 根据账号密码查询用户ID
     * @return ID
     */
    @Override
    public Integer queryByParam(User user) {

        //查询用户ID
        Integer id = userMapper.selectUserLogin(user);
        return id;
    }

    /**
     * 根据User的ID查状态
     *
     * @param user
     * @return
     */
    @Override
    public Integer queryById(User user) {
        Integer state = userMapper.selectUserState(user);
        return state;
    }

    /**
     * 查询用户密码
     *
     * @param user 带用户密码就可以查
     * @return
     */
    @Override
    public String selectUserPassword(User user) {
        String s = userMapper.selectUserPassword(user);
        return s;
    }

    /**
     * 根据ID更改用户密码
     *
     * @param user
     */
    @Override
    public void updateUserPassword(User user) {
        userMapper.updateUserPassword(user);
    }

    /**
     * 根据ID查询用户名
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public String selectUserName(Integer id) {
        return userMapper.selectUserName(id);
    }
}
