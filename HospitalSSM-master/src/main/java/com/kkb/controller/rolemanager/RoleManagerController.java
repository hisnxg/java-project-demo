package com.kkb.controller.rolemanager;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Role;
import com.kkb.bean.RoleMenu;
import com.kkb.service.rolemanager.RoleManagerService;
import com.kkb.service.rolemenu.RoleMenuService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.rolemanager.req.*;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;
import com.kkb.vo.rolemenu.req.InsertRoleMenuReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/role")
public class RoleManagerController {
    @Autowired
    private RoleManagerService roleManagerService;
    @Autowired
    private RoleMenuService roleMenuService;
    private Integer roleId;
    //根据角色名称模糊查询所有
    @PostMapping("/selectRole")
    public BaseResp<Map<String,Object>> selectRole(SelectRoleReq param){
        if (param.getPageNum()==null){
            param.setPageNum(1);
        }
        PageInfo<Role> rolePage = roleManagerService.queryByParam(param);
        Map<String,Object> map = new HashMap<>();
        map.put("rolePage",rolePage);
        map.put("param",param);
        return new BaseResp<>(map);
    }

    @PostMapping("/addRole")
    public BaseResp<Map<String,Object>> addRole(AddRoleReq param){
        System.out.println(param);
         Role role;
         Integer rid;
        try {
            roleManagerService.addRole(param);
            System.out.println(param.getRoleName());
          role =roleManagerService.selectRidByName(param.getRoleName());
          System.out.println(role);
            rid= role.getrId();
            AddRoleMenuReq addRoleMenuReq=new AddRoleMenuReq(rid,param.getRoleIds());
            addRoleMenu(addRoleMenuReq);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"添加失败");
        }
        return new BaseResp<>();
    }
    //更新角色
    @PostMapping("/upDateRole")
    public BaseResp<Map<String,Object>> upDateRole(EditRoleReq param){
        try {
             param.setRoleId(roleId);
             roleManagerService.editRole(param);
             roleMenuService.deleteRoleMenuById(roleId);
              InsertRoleMenuReq insertRoleMenuReq =new InsertRoleMenuReq(roleId, param.getRmIds());
              insertRoleMenu(insertRoleMenuReq);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"更新失败");
        }
        return new BaseResp<>();
    }
    //通过id删除角色
    @PostMapping("/deleteRole")
    public BaseResp<Map<String,Object>> deleteRole(DeleteRoleReq param){
        System.out.println(param);
        try {
            roleManagerService.deleteRole(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }
        return new BaseResp<>();
    }
    //通过角色ids删除多个角色
    @PostMapping("/deleteRoleByIds")
    public BaseResp<Map<String,Object>> deleteRoleByIds(DeleteMoreRoleReq param){
        System.out.println(param);
        try {
            roleManagerService.deleteRoleByIds(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }
        return new BaseResp<>();
    }
      //插入role_menu表
    public void insertRoleMenu(InsertRoleMenuReq insertRoleMenuReq){
        roleMenuService.insertRoleMenu(insertRoleMenuReq);
    }
     //
     public void addRoleMenu(AddRoleMenuReq addRoleMenuReq){
         roleMenuService.addRoleMenu(addRoleMenuReq);
     }
    @PostMapping("/selectRoleById")
    public BaseResp<Map<String,Object>> selectRoleById(Integer roleId){
        this.roleId=roleId;

        Role role= roleManagerService.SelectRoleById(roleId);
        List<RoleMenu> roleMenu= roleMenuService.selectMenuId(roleId);
        Map<String,Object> map = new HashMap<>();
        map.put("role",role);
        map.put("roleMenu",roleMenu);
        return new BaseResp<>(map);
    }
    @PostMapping("/selectRoleByName")
    public BaseResp<Map<String,Object>> selectRoleByName(SelectRoleReq param){

        Role role= roleManagerService.selectRoleByName(param);
        Map<String,Object> map = new HashMap<>();
        map.put("role",role);
        return new BaseResp<>(map);
    }
}
