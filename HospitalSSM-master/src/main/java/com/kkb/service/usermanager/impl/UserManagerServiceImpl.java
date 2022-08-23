package com.kkb.service.usermanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;
import com.kkb.bean.User;
import com.kkb.dao.MenuMapper;
import com.kkb.dao.UserMapper;
import com.kkb.service.usermanager.UserManagerService;
import com.kkb.vo.usermanager.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;


    //根据名称查询后分页
    @Override
    public PageInfo<User> queryByParam(SelectUserReq param) {
        PageHelper.startPage(param.getPageNum(),param.getPageSize());
        List<User> userList = userMapper.selectUserByParam(param);
        return new PageInfo<>(userList);
    }
    //添加用户
    @Override
    public void  insertUser(AddUserReq param) {
      userMapper.insertUser(param);
    }

    //更新用户
    @Override
    public void updateUser(EditUserReq editUserReq) {
     userMapper.updateByPrimaryKey(editUserReq);

    }

    @Override
    public void deleteUserByIds(DeleteMoreUserReq param) {

       userMapper.deleteByUserIds(param);

    }

    @Override
    public User selectUserByName(SelectUserReq selectUserReq) {
       User user= userMapper.selectUserByName(selectUserReq);
       return user;
    }

    //根据id查询信息
    @Override
    public User selectUserById(int i) {
        User user=userMapper.selectUserById(i);
        return user;
    }


    @Override
    public void deleteUser(DeleteUserReq param) {
      userMapper.deleteByUserId(param);

    }

    @Override
    public List<Menu> getSourcesList(Integer id) {
        return menuMapper.selectByUserId(id);
    }
}
