package com.xzk.dao;

import com.xzk.bean.Courier;
import com.xzk.exception.DuplicateIdentifyCardException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BaseCourierDao {

    /**
     * 用于查询数据库中的全部快递员(总数+新增),
     * @return [{size:总人数,day:新增}]
     */
    List<Map<String,Integer>> consoleCourier();

    /**
     * 用于查询所有快递员的信息
     * @param limit 是否分页的标记,true表示分页. false表示不分页
     * @param offset SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递员的集合
     */
    List<Courier> findAllCourier(boolean limit, int offset, int pageNumber);

    /**
     * 根据快递员的手机号码.查询快递员的所有记录
     * @param userPhone 手机号码
     * @return 查询的快递员信息列表
     */
    Courier findByCourierPhone(String userPhone);

    /**
     * 根据快递员的id.查询快递员的信息·
     * @param id 快递员id
     * @return 查询的快递员信息列表
     */
    Courier findByCourierId(int id);
    /**
     * 快递的录入
     * @param c 要录入的快递员对象
     * @return 录入的结果,true表示成功,false表示失败
     */
    boolean insertCourier(Courier c) throws DuplicateIdentifyCardException;

    /**
     * 快递员信息的修改
     * @param id 要修改的快递员id
     * @param newCourier 新的快递员对象(username,userPhone,identifyCard,password)
     * @return
     */
    boolean updateCourier(int id, Courier newCourier);

    /**
     * 更新用户登录时间
     * @param loginTime 要修改的用户id
     * @param userPhone 新的用户对象(username,userPhone,identifyCard,password)
     * @return
     */
    void updateCourierLoginTime(Date loginTime, String userPhone);

    /**
     * 根据id，删除单个快递员信息
     * @param id 要删除的快递员id
     * @return 删除的结果，true表示成功，false表示失败
     */
    boolean deleteCourier(int id);
}
