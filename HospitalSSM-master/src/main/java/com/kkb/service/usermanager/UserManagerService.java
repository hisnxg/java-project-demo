package com.kkb.service.usermanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;
import com.kkb.bean.User;
import com.kkb.vo.usermanager.req.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserManagerService {

     // 根据用户名称查询分页
    PageInfo<User> queryByParam(SelectUserReq param);
    //添加用户
      void insertUser(AddUserReq param);
     void updateUser(EditUserReq editUserReq);
     //通过ids多条删除
   void deleteUserByIds(DeleteMoreUserReq param);
    User selectUserByName(SelectUserReq selectUserReq);
    //根据id查询信息
    User selectUserById(int i);
    void deleteUser(DeleteUserReq param);

    List<Menu> getSourcesList(Integer id);
}
