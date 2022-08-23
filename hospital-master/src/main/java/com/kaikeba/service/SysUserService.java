package com.kaikeba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaikeba.mapper.RoleMapper;
import com.kaikeba.mapper.SysUserMapper;
import com.kaikeba.pojo.Role;
import com.kaikeba.pojo.SysUser;
import com.kaikeba.pojo.SysUserExample;
import com.kaikeba.vo.QueryUserRoleMenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *用户管理的逻辑层
 */
@Service
public class SysUserService {
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;

    //1.分页 多条件查询
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public PageInfo<SysUser> selectByPage(Integer pageNum, Integer pageSize, QueryUserRoleMenuVO vo) {

        SysUserExample sysUserExample = new SysUserExample();//盛放条件的对象
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();//准则
        if (vo.getLoginName()!=null&&""!= vo.getLoginName().trim()){
            criteria.andLoginNameLike("%"+vo.getLoginName()+"%");
        }

        System.out.println("用户的分页查询开始");
        PageHelper.startPage(pageNum,pageSize); //分页
        List<SysUser> sysUsers = userMapper.selectByExample(sysUserExample);
        System.out.println("查看结果:"+sysUsers);

        for (SysUser sysUser : sysUsers) {  //给每个用户绑定角色信息，页面要显示用户对应的角色的名称
            Role role = roleMapper.selectByPrimaryKey(sysUser.getRoleId());
            System.out.println("用户对应的角色:"+role);
            sysUser.setRole(role);
        }
        return new PageInfo<>(sysUsers); //填充
    }

    //2.添加用户
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int insertUser(SysUser user){
        return userMapper.insertSelective(user);
    }

    //3.删除用户 --逻辑删除，禁用
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int deleteSysUser(Integer id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setStatus(1); // 1:禁用
        return userMapper.updateByPrimaryKeySelective(sysUser);
    }

    //4.更新用户
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int updateSysUser(SysUser sysUser){
        return userMapper.updateByPrimaryKeySelective(sysUser);
    }


    //5.查询所有角色  添加/更新用户时角色的下拉菜单
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public List<Role>selectAllRole(){
        return roleMapper.selectByExample(null);
    }


    //6.根据id查询用户信息  用于更新用户时进行回显
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public SysUser selectSysUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //7.批量删除用户 --逻辑删除，禁用
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public boolean deleteSelected(String[] userIds) {
        SysUser sysUser = new SysUser();
        for (String userId : userIds) {
            sysUser.setId(Integer.parseInt(userId));
            sysUser.setStatus(1); // 1:禁用
            int update = userMapper.updateByPrimaryKeySelective(sysUser);
            if (update!=1) return false;
        }
        return true;
    }

    //8.根据账号获取用户  包含角色 菜单
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public SysUser selectByLoginName(String loginName) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = userMapper.selectByExample(sysUserExample);
        if (sysUsers==null) return null;
        SysUser sysUser=sysUsers.get(0);
        sysUser.setRole(roleMapper.selectByPrimaryKey(sysUser.getRoleId()));
        sysUser.getRole().setMenuList(roleMenuService.selectMenuByRoleId(sysUser.getRoleId()));
        System.out.println("该账号的详细信息:"+sysUser);
        return sysUser;
    }

    //9.检查原密码
    public boolean checkPassword(Integer sysUserId,String oldPassword) {
        if (userMapper.selectByPrimaryKey(sysUserId).getPassword().equals(oldPassword)) return true;
        else return false;
    }

    //10.更新密码
    public int updatePassword(Integer sysUserId,String newPassword) {
        return userMapper.updateByPrimaryKeySelective(new SysUser(sysUserId,newPassword));
    }

    //11.核实用户账号是否存在 --账号唯一性验证
    public boolean checkByLoginName(String loginName) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = userMapper.selectByExample(sysUserExample);
        if (sysUsers==null||sysUsers.size()<1) return false;
        else return true;
    }
}

