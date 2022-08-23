package com.kaikeba.service;

import com.kaikeba.bean.User;
import com.kaikeba.dao.BaseUserDao;
import com.kaikeba.dao.imp.UserDaoMysql;
import com.kaikeba.exception.DuplicateIdentifyCardException;

import java.util.List;
import java.util.Map;

public class UserService{
    private static BaseUserDao dao = new UserDaoMysql();
    /**
     * 用于查询数据库中的全部快用户(总数+日注册量),
     *
     * @return [{size:总人数,day:日注册量}]
     */

    public static List<Map<String, Integer>> consoleUser() {
        return dao.consoleUser();
    }

    /**
     * 用于查询所有用户的信息
     *
     * @param limit      是否分页的标记,true表示分页. false表示不分页
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 用户的集合
     */

    public static List<User> findAllUser(boolean limit, int offset, int pageNumber) {
        return dao.findAllUser(limit,offset,pageNumber);
    }

    /**
     * 根据用户的手机号码.查询用户的所有记录
     *
     * @param userPhone 手机号码
     * @return 查询的用户信息列表
     */

    public static List<User> findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 用户的录入
     *
     * @param u 要录入的用户对象
     * @return 录入的结果, true表示成功, false表示失败
     */
    public static boolean insertUser(User u) throws DuplicateIdentifyCardException {
        try {
            return dao.insertUser(u);
        } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
            return insertUser(u); //通过递归的方式,避免身份证号重复问题
        }
    }

    /**
     * 用户信息的修改
     *
     * @param id      要修改的用户id
     * @param newUser 新的用户对象(username,userPhone,identifyCard,password)
     * @return
     */
    public static boolean updateUser(int id, User newUser) {

        boolean update =false;
        try {
            if (newUser.getUserPhone()!=null){
                dao.deleteUser(id);
                return insertUser(newUser);
            }else {
                update = dao.updateUser(id, newUser);
//                            List<User> c = dao.findByUserPhone(newUser.getUserPhone());

            }
        } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
            duplicateIdentifyCardException.printStackTrace();
        }
        return update;

//        return dao.updateUser(id,newUser);
    }

    /**
     * 根据id，删除单个用户信息
     *
     * @param id 要删除的用户id
     * @return 删除的结果，true表示成功，false表示失败
     */

    public static boolean deleteUser(int id) {
        return dao.deleteUser(id);
    }
}
