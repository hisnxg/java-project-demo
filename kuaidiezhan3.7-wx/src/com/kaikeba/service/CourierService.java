package com.kaikeba.service;

import com.kaikeba.bean.Courier;
import com.kaikeba.dao.BaseCourierDao;
import com.kaikeba.dao.imp.CourierDaoMysql;

import com.kaikeba.exception.DuplicateIdentifyCardException;

import java.util.List;
import java.util.Map;

public class CourierService{
    private static BaseCourierDao dao =  new CourierDaoMysql();
    /**
     * 用于查询数据库中的全部快递员(总数+新增),
     *
     * @return [{size:总人数,day:新增}]
     */
    public static List<Map<String, Integer>> consoleCourier() {
        return dao.consoleCourier();
    }

    /**
     * 用于查询所有快递员的信息
     *
     * @param limit      是否分页的标记,true表示分页. false表示不分页
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递员的集合
     */

    public static List<Courier> findAllCourier(boolean limit, int offset, int pageNumber) {
        return dao.findAllCourier(limit, offset, pageNumber);
    }

    /**
     * 根据快递员的手机号码.查询快递员的所有记录
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */

    public static List<Courier> findByCourierPhone(String userPhone) {
        return dao.findByCourierPhone(userPhone);
    }

    /**
     * 快递的录入
     *
     * @param c 要录入的跨地对象
     * @return 录入的结果, true表示成功, false表示失败
     */

    public static boolean insertCourier(Courier c) throws DuplicateIdentifyCardException {
        try {
            return dao.insertCourier(c);
        } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
            return insertCourier(c); //通过递归的方式,避免身份证号重复问题
        }
//        return dao.insertCourier(c);
    }

    /**
     * 快递员信息的修改
     *
     * @param id  要修改的快递员id
     * @param newCourier 新的快递员对象(username,userPhone,identifyCard,password)
     * @return
     */

    public static boolean updateCourier(int id, Courier newCourier) {

               boolean update =false;
                    try {
                        if (newCourier.getUserPhone()!=null){
                            dao.deleteCourier(id);
                           return insertCourier(newCourier);
                        }else {
                             update = dao.updateCourier(id, newCourier);
//                            List<Courier> c = dao.findByCourierPhone(newCourier.getUserPhone());

                        }
                    } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
                        duplicateIdentifyCardException.printStackTrace();
                    }
          return update;
//        return dao.updateCourier(id,newCourier);
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
     */

    public static boolean deleteCourier(int id) {
        return dao.deleteCourier(id);
    }
}
