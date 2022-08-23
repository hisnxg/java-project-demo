package com.kkb.controller.usermanager;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;
import com.kkb.bean.User;
import com.kkb.service.usermanager.UserManagerService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.usermanager.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author myp
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;


    //根据用户名称模糊查询所有用户并分页
    @PostMapping("/selectUser")
    public BaseResp<Map<String, Object>> selectUser(SelectUserReq param) {
        System.out.println(param.getUserName());
        if (param.getPageNum()==null){
            param.setPageNum(1);
        }
        PageInfo<User> userPage = userManagerService.queryByParam(param);

        Map<String, Object> map = new HashMap<>();
        map.put("userPage", userPage);
        map.put("param", param);

        return new BaseResp<>(map);
    }

     //添加用户到数据库
    @PostMapping("/addUser")
    public BaseResp<Map<String,Object>> addUser( AddUserReq param){
        System.out.println(param);

        try {
         userManagerService.insertUser(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"添加失败");
        }

        return new BaseResp<>();
    }
    //通过用户名更新数据
    @PostMapping("/updateUser")
    public BaseResp<Map<String,Object>> updateUser( EditUserReq param){
        System.out.println(param);

        try {
            userManagerService.updateUser(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"更新失败");
        }


        return new BaseResp<>();
    }
    //删除user
    @PostMapping("/deleteUser")
    public BaseResp<Map<String,Object>> deleteUser( DeleteUserReq param){
        System.out.println(param);

        try {
           userManagerService.deleteUser(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }

        return new BaseResp<>();
    }
    @PostMapping("/deleteUserByIds")
    public BaseResp<Map<String,Object>> deleteUserByIds(DeleteMoreUserReq param){
        System.out.println(param);

        try {
          userManagerService.deleteUserByIds(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }
        return new BaseResp<>();
    }
    @PostMapping("/selectUserById")
    public BaseResp<Map<String,Object>> selectUserById(int i) {
       User user=userManagerService.selectUserById(i);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);

        return new BaseResp<>(map);

    }
    @PostMapping("/selectUserByName")
    public BaseResp<Map<String,Object>> selectUserByName(SelectUserReq paraam) {
        User user=userManagerService.selectUserByName(paraam);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);

        return new BaseResp<>(map);

    }

    /**
     * 查询资源列表
     * @param session
     * @return
     */
    @PostMapping("/getSourcesList")
    public BaseResp<Map<String,Object>> getSourcesList(HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");
        List<Menu> menus = userManagerService.getSourcesList(id);
        Map<String, Object> map = new HashMap<>();
        map.put("menus", menus);

        return new BaseResp<>(map);

    }
}
