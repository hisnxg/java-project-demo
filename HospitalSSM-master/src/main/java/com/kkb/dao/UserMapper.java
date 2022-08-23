package com.kkb.dao;
import com.kkb.bean.Role;
import com.kkb.bean.User;
import com.kkb.vo.rolemanager.req.SelectRoleReq;
import com.kkb.vo.usermanager.req.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //通过用户名称删除用户
    int  deleteByUserId(DeleteUserReq param);
    //添加用户
    int insertUser(AddUserReq param);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);
    User selectUserByName(SelectUserReq selectUserReq);
    int updateByPrimaryKey(EditUserReq param);
    int deleteByUserIds(@Param("deleteMoreUserReq") DeleteMoreUserReq deleteMoreUserReq);
    User selectUserById(int i);
    /**
     * 根据用户名称模糊查询所有
     * @param param
     * @return
     */
    List<User> selectUserByParam( SelectUserReq param);





}