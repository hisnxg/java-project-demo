package com.kkb.dao;

import com.kkb.bean.Menu;
import com.kkb.bean.Role;
import com.kkb.vo.menumanager.req.SelectMenuReq;
import com.kkb.vo.rolemanager.req.*;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RoleMapper {

    //通过id删除角色
    int deleteByPrimaryKey(DeleteRoleReq deleteRoleReq);
    //添加角色
    int insert(AddRoleReq addRoleReq);

    int insertSelective(Role record);

    Role selectByName(String roleName);
    Role selectRoleById(Integer roleId);
    Role selectRoleByName(SelectRoleReq selectRoleReq);
    int updateByPrimaryKeySelective(Role record);
    //更新角色
    int updateByRoleId(EditRoleReq editRoleReq);
    //模糊查询角色
    List<Role> selectRoleByParam(SelectRoleReq param);
    int deleteRoleByIds(@Param("deleteMoreRoleReq") DeleteMoreRoleReq deleteMoreRoleReq);

}