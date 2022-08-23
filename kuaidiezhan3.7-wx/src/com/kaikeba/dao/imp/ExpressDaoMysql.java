package com.kaikeba.dao.imp;

import com.kaikeba.bean.Express;
import com.kaikeba.dao.BaseExpressDao;
import com.kaikeba.exception.DuplicateCodeException;
import com.kaikeba.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressDaoMysql implements BaseExpressDao {
    //用字符串拼接语句,缺点是造成资源浪费,
    // sql语句都用大写,而不小写,目的语句在执行的过程会转换为大写,它影响一点性能(很小)
    //用于查询数据库中的全部快递(总数+新增),待取件快递(总数+新增)
    public static final  String SQL_CONSOLE = "SELECT "+
            "COUNT(ID) data1_size," +
            "COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) OR NULL) data1_day ,"+
            "COUNT(STATUS=0 OR NULL) data2_size,"+
            "COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) AND STATUS=0 OR NULL) data2_day "+
            "FROM EXPRESS";
    //通过查询数据库中的所有快递信息
    public static final  String SQL_FIND_ALL = "SELECT * FROM EXPRESS";
    //用分页查询数据库中的快递信息
    public static final String SQL_FIND_LIMIT = "SELECT * FROM EXPRESS LIMIT ?,?";
    //通过快递单号查询快递信息
    public static final  String SQL_FIND_BY_NUMBER = "SELECT * FROM EXPRESS WHERE NUMBER=?";
    //通过取件码查询快递信息
    public static final  String SQL_FIND_BY_CODE = "SELECT * FROM EXPRESS WHERE CODE=?";
    //通过用户手机号查询用户所有快递
    public static final  String SQL_FIND_BY_USERPHONE = "SELECT * FROM EXPRESS WHERE USERPHONE=?";
    //通过用户手机号和状态查询用户所有快递
    public static final  String SQL_FIND_BY_USERPHONE_AND_STATUS = "SELECT * FROM EXPRESS WHERE USERPHONE=? AND STATUS=?";
    //通过录入人手机号查询快递信息
    public static final  String SQL_FIND_BY_SYSPHONE = "SELECT * FROM EXPRESS WHERE SYSPHONE=?";
    //快递录入
    public static final  String SQL_INSERT = "INSERT INTO EXPRESS (NUMBER,USERNAME,USERPHONE,COMPANY,CODE,INTIME,STATUS,SYSPHONE) VALUES(?,?,?,?,?,NOW(),0,?)";
    //快递修改
    public static final  String SQL_UPDATE= "UPDATE EXPRESS SET NUMBER=?,USERNAME=?,COMPANY=?,STATUS=? WHERE ID=?";
    //快递的状态码改变(取件)
    public static final  String SQL_UPDATE_STATUS = "UPDATE EXPRESS SET STATUS=1,OUTTIME=NOW(),CODE=NULL WHERE CODE=?";
    //快递的删除
    public static final  String SQL_DELETE = "DELETE FROM EXPRESS WHERE ID=?";


    /**
     * 用于查询数据库中的全部快递(总数+新增),待取件快递(总数+新增)
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String,Integer>> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
             state = conn.prepareStatement(SQL_CONSOLE);
            //3.    填充参数(可选)
            //4.    执行SQL语句
             resultSet = state.executeQuery();
            //5.    获取执行的结果
            if (resultSet.next()){
                int data1_size = resultSet.getInt("data1_size");
                int data1_day = resultSet.getInt("data1_day");
                int data2_size = resultSet.getInt("data2_size");
                int data2_day = resultSet.getInt("data2_day");
                Map data1 = new HashMap();//集合
                data1.put("data1_size",data1_size);
                data1.put("data1_day",data1_day);
                Map data2 = new HashMap();
                data2.put("data2_size",data2_size);
                data2.put("data2_day",data2_day);
                data.add(data1);
                data.add(data2);
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
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记,true表示分页. false表示所有快递
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    @Override
    public List<Express> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<Express> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            if (limit){
                state = conn.prepareStatement(SQL_FIND_LIMIT);
                //3.    填充参数(可选)
                state.setInt(1,offset);
                state.setInt(2,pageNumber);
            }else{
                state = conn.prepareStatement(SQL_FIND_ALL);
            }
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
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
     * 根据快递单号,查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息, 单号不存在是返回null
     */
    @Override
    public Express findByNumber(String number) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_NUMBER);
            //3.    填充参数(可选)
            state.setString(1,number);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                return e;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    资源的释放
            DruidUtil.close(conn,state,resultSet);
        }
        return null;
    }

    /**
     * 根据快递取件码,查询快递所有信息
     *
     * @param code 取件码
     * @return 查询的快递信息 ,取件码不存在时返回null
     */
    @Override
    public Express findBycode(String code) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_CODE);
            //3.    填充参数(可选)
            state.setString(1,code);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                return e;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    资源的释放
            DruidUtil.close(conn,state,resultSet);
        }
        return null;
    }

    /**
     * 根据用户手机号码,查询所有的快递信息
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        ArrayList<Express> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USERPHONE);
            //3.    填充参数(可选)
            state.setString(1,userPhone);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
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
     * 根据用户手机号码,查询所有的快递信息
     *
     * @param userPhone 手机号码
     * @param status    状态码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findByUserPhoneAndStatus(String userPhone, int status) {
        ArrayList<Express> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USERPHONE_AND_STATUS);
            //3.    填充参数(可选)
            state.setString(1,userPhone);
            state.setInt(2,status);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                String sysPhone = resultSet.getString("sysPhone");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
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
     * 根据录入人的手机号码.查询录入的所有记录
     *
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表
     */
    @Override
    public List<Express> findBySysPhone(String sysPhone) {
        ArrayList<Express> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_SYSPHONE);
            //3.    填充参数(可选)
            state.setString(1,sysPhone);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String username = resultSet.getString("username");
                String userPhone = resultSet.getString("userPhone");
                String company = resultSet.getString("company");
                String code = resultSet.getString("code");
                Timestamp inTime = resultSet.getTimestamp("inTime");
                Timestamp outTime = resultSet.getTimestamp("outTime");
                int status = resultSet.getInt("status");
                Express e = new Express(id, number, username, userPhone, company, code, inTime, outTime, status, sysPhone);
                data.add(e);
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
     * 快递的录入
     *INSERT INTO EXPRESS (NUMBER,USERNAME,USERPHONE,COMPANY,CODE,INTIME,STATUS,SYSPHONE) VALUES(?,?,?,?,?,NOW(),0,?)
     * @param e 要录入的跨地对象
     * @return 录入的结果, true表示成功, false表示失败
     */
    @Override
    public boolean insert(Express e) throws DuplicateCodeException {
        //1.    获取数据库的连接的获取
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_INSERT);
            //3.    填充参数
            state.setString(1,e.getNumber());
            state.setString(2,e.getUsername());
            state.setString(3,e.getUserPhone());
            state.setString(4,e.getCompany());
            state.setString(5,e.getCode());
            state.setString(6,e.getSysPhone());
            //4.    执行SQL语句,并获取执行结果
            return state.executeUpdate()>0?true:false;
        } catch (SQLException e1) {
           /* throwables.printStackTrace();*/
            if (e1.getMessage().endsWith("for key 'code'")){
                //是因为取件码重复,而出现了异常
                DuplicateCodeException e2 = new DuplicateCodeException(e1.getMessage());
                throw e2;
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
     * 快递的修改
     * UPDATE EXPRESS SET NUMBER=?,USERNAME=?,COMPANY=?,STATUS=? WHERE ID=?
     * @param id         要修改的快递id
     * @param newExpress 新的快递对象(number,company,username,userPhone)
     * @return
     */
    @Override
    public boolean update(int id, Express newExpress) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            //3.    填充参数(可选)
            state.setString(1,newExpress.getNumber());
            state.setString(2,newExpress.getUsername());
            state.setString(3,newExpress.getCompany());
            state.setInt(4,newExpress.getStatus());
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
     * 更改快递的状态为1,表示取件完成
     *
     * @param code 要修改的快递取件码
     * @return 修改的结果, true表示成功, false表示失败
     */
    @Override
    public boolean updateStatus(String code) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_STATUS);
            //3.    填充参数(可选)
            state.setString(1,code);
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
    public boolean delete(int id) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_DELETE);
            //3.    填充参数(可选)
            state.setInt(1,id);
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
}
