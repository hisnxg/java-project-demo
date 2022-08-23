package com.xzk.dao;

import com.xzk.bean.User;
import com.xzk.exception.DuplicateIdentifyCardException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BaseUserDao {
    /**
     * 用于查询数据库中的全部快用户(总数+日注册量),
     * @return [{size:总人数,day:日注册量}]
     */
    List<Map<String,Integer>> consoleUser();

    /**
     * 用于查询所有用户的信息
     * @param limit 是否分页的标记,true表示分页. false表示不分页
     * @param offset SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 用户的集合
     */
    List<User> findAllUser(boolean limit, int offset, int pageNumber);

    /**
     * 根据用户的手机号码.查询用户的所有记录
     * @param userPhone 手机号码
     * @return 查询的用户信息列表
     */
    User findByUserPhone(String userPhone);

    /**
     * 用户的录入
     * @param u 要录入的用户对象
     * @return 录入的结果,true表示成功,false表示失败
     */
    boolean insertUser(User u) throws DuplicateIdentifyCardException;

    /**未注册的手机号
     * 注册手机号 录入用户表
     * @param u 要录入的用户对象
     * @return 录入的结果,true表示成功,false表示失败
     */
    boolean insertUserRegistration(User u) throws DuplicateIdentifyCardException;
    //boolean insertUserRegistration(User u);
    /**
     * 用户信息的修改
     * @param id 要修改的用户id
     * @param newUser 新的用户对象(username,userPhone,identifyCard,password)
     * @return
     */
    boolean updateUser(int id, User newUser);

    /**
     * 更新用户登录时间
     * @param loginTime 要修改的用户id
     * @param userPhone 新的用户对象(username,userPhone,identifyCard,password)
     * @return
     */
    void updateUserLoginTime(Date loginTime, String userPhone);


    /**
     * 根据取件的数量,对用户进行排名
     * @param type  0表示查询总排名,1表示年排名,2表示月排名
     * @return
     */
    List<User> lazyboard(int type);


    /**
     * 根据id，删除单个用户信息
     * @param id 要删除的用户id
     * @return 删除的结果，true表示成功，false表示失败
     */
    boolean deleteUser(int id);
}
