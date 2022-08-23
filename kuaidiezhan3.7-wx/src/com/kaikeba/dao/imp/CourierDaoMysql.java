package com.kaikeba.dao.imp;

import com.kaikeba.bean.Courier;

import com.kaikeba.dao.BaseCourierDao;
import com.kaikeba.exception.DuplicateIdentifyCardException;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierDaoMysql implements BaseCourierDao {
    //用于查询数据库中的全部快递员信息(总数+日注册量)
    public static final  String SQL_CONSOLE_COURIER = "SELECT "+
            "COUNT(ID) data3_size," +
            "COUNT(TO_DAYS(REGISTRATIONTIME)=TO_DAYS(NOW()) OR NULL) data3_day "+
            "FROM COURIER";
    //通过查询数据库中的所有快递员信息
    public static final  String SQL_FIND_ALL_COURIER = "SELECT * FROM COURIER";
    //用分页查询数据库中的快递员信息
    public static final String SQL_FIND_COURIER_LIMIT = "SELECT * FROM COURIER LIMIT ?,?";
    //通过快递员手机号查询所有快递员信息
    public static final  String SQL_FIND_BY_COURIER_USERPHONE = "SELECT * FROM COURIER WHERE USERPHONE=?";
    //快递员录入
    public static final  String SQL_INSERT_COURIER = "INSERT INTO COURIER (USERNAME,USERPHONE,IDENTIFYCARD,PASSWORD,REGISTRATIONTIME) VALUES(?,?,?,?,NOW())";
    //快递员信息修改
    public static final  String SQL_UPDATE_COURIER= "UPDATE COURIER SET USERNAME=?,USERPHONE,IDENTIFYCARD,PASSWORD=? WHERE ID=?";
    //快递员信息的删除
    public static final  String SQL_DELETE_COURIER = "DELETE FROM COURIER WHERE ID=?";



    /**
     * 用于查询数据库中的全部快递员(总数+新增),
     *
     * @return [{size:总人数,day:新增}]
     */
    @Override
    public  List<Map<String, Integer>> consoleCourier() {
        ArrayList<Map<String,Integer>> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_CONSOLE_COURIER);
            //3.    填充参数(可选)
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            if (resultSet.next()){
                int data3_size = resultSet.getInt("data3_size");
                int data3_day = resultSet.getInt("data3_day");

                Map data1 = new HashMap();//集合
                data1.put("data3_size",data3_size);
                data1.put("data3_day",data3_day);

                data.add(data1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    资源的释放
            DruidUtil.close(conn,state,resultSet);
        }
        return data;
    }

    /**
     * 用于查询所有快递员的信息
     *
     * @param limit      是否分页的标记,true表示分页. false表示不分页
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递员的集合
     */
    @Override
    public List<Courier> findAllCourier(boolean limit, int offset, int pageNumber) {
        ArrayList<Courier> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            if (limit){
                state = conn.prepareStatement(SQL_FIND_COURIER_LIMIT);
                //3.    填充参数(可选)
                state.setInt(1,offset);
                state.setInt(2,pageNumber);
            }else{
                state = conn.prepareStatement(SQL_FIND_ALL_COURIER);
            }
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");

                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String identifyCard = resultSet.getString("identifyCard");
                String password = resultSet.getString("password");
                int dispatches = resultSet.getInt("dispatches");
                Timestamp registrationTime = resultSet.getTimestamp("registrationTime");
                Timestamp loginTime = resultSet.getTimestamp("loginTime");
                Courier c = new Courier(id,username,userPhone,identifyCard,password,dispatches,registrationTime,loginTime);
                data.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    资源的释放
            DruidUtil.close(conn,state,resultSet);
        }
        return data;
    }

    /**
     * 根据快递员的手机号码.查询快递员的所有记录
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Courier> findByCourierPhone(String userPhone) {
        ArrayList<Courier> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_COURIER_USERPHONE);
            //3.    填充参数(可选)
            state.setString(1,userPhone);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String identifyCard = resultSet.getString("identifyCard");
                String password = resultSet.getString("password");
                int dispatches = resultSet.getInt("dispatches");
                Timestamp registrationTime = resultSet.getTimestamp("registrationTime");
                Timestamp loginTime = resultSet.getTimestamp("loginTime");
                Courier c = new Courier(id,username,userPhone,identifyCard,password,dispatches,registrationTime,loginTime);
                data.add(c);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    资源的释放
            DruidUtil.close(conn,state,resultSet);
        }
        return data;
    }

    /**
     * 快递员信息的录入
     *
     * @param c 要录入的快递员对象
     * @return 录入的结果, true表示成功, false表示失败
     */
    @Override
    public boolean insertCourier(Courier c) {
        //1.    获取数据库的连接的获取
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_INSERT_COURIER);
            //3.    填充参数

            state.setString(1,c.getUsername());
            state.setString(2,c.getUserPhone());
            state.setString(3,c.getIdentifyCard());
            state.setString(4,c.getPassword());
            //4.    执行SQL语句,并获取执行结果
            return state.executeUpdate()>0?true:false;
        } catch (SQLException e1) {
            /* throwables.printStackTrace();*/
            if (e1.getMessage().endsWith("for key 'identifyCard'")){
                //是因为取件码重复,而出现了异常
                DuplicateIdentifyCardException e2 = new DuplicateIdentifyCardException(e1.getMessage());
                try {
                    throw e2;
                } catch (DuplicateIdentifyCardException e) {
                    e.printStackTrace();
                }
            }else{
                e1.printStackTrace();
            }
        }finally {
            //5.    资源的释放
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 快递员信息的修改
     *
     * @param id  要修改的快递员id
     * @param newCourier 新的快递员对象(username,userPhone,identifyCard,password)
     * @return
     */
    @Override
    public boolean updateCourier(int id, Courier newCourier) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_COURIER);
            //3.    填充参数(可选)
            state.setString(1,newCourier.getUsername());
            state.setString(2,newCourier.getUserPhone());
            state.setString(3,newCourier.getIdentifyCard());
            state.setString(4,newCourier.getPassword());
            state.setInt(5,id);
            //4.    执行SQL语句,并获取执行的结果
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //5.    资源的释放
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
     */
    @Override
    public boolean deleteCourier(int id) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_DELETE_COURIER);
            //3.    填充参数(可选)
            state.setInt(1, id);
            //4.    执行SQL语句,并获取执行的结果
            return state.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.    资源的释放
            DruidUtil.close(conn, state, null);
        }
        return false;
    }
}
