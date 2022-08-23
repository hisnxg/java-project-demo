package com.xzk.dao.imp;

import com.xzk.bean.User;
import com.xzk.dao.BaseUserDao;
import com.xzk.exception.DuplicateIdentifyCardException;
import com.xzk.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMysql implements BaseUserDao {

    //用于查询数据库中的全部快递员信息(总数+日注册量)
    public static final  String SQL_CONSOLE_USER = "SELECT "+
            "COUNT(ID) data4_size," +
            "COUNT(TO_DAYS(REGISTRATIONTIME)=TO_DAYS(NOW()) OR NULL) data4_day "+
            "FROM USER";
    //通过查询数据库中的所有用户信息
    public static final  String SQL_FIND_ALL_USER = "SELECT * FROM USER";
    //用分页查询数据库中的用户信息
    public static final String SQL_FIND_USER_LIMIT = "SELECT * FROM USER LIMIT ?,?";
    //通过快递员手机号查询所有用户信息
    public static final  String SQL_FIND_BY_USER_USERPHONE = "SELECT * FROM USER WHERE USERPHONE=?";
    //用户信息认证
    public static final  String SQL_INSERT_USER = "INSERT INTO USER (USERNAME,USERPHONE,IDENTIFYCARD,PASSWORD,REGISTRATIONTIME) VALUES(?,?,?,?,NOW())";
    //wx用户注册
    public static final  String SQL_INSERT_USER_REGISTRATION = "INSERT INTO USER (USERPHONE,REGISTRATIONTIME) VALUES(?,NOW())";
    //用户信息修改
    public static final  String SQL_UPDATE_USER= "UPDATE USER SET USERNAME=?,USERPHONE,IDENTIFYCARD,PASSWORD=? WHERE ID=?";

    private static String SQL_FIND_ORDER_BY_ALL = " SELECT COUNT(STATUS = 1 OR NULL) NUMBER, USER.USERNAME USERNAME FROM USER, EXPRESS WHERE USER.USERPHONE = EXPRESS.USERPHONE GROUP BY USERNAME ORDER BY NUMBER DESC ";
    private static String SQL_FIND_ORDER_BY_YEAR = " SELECT COUNT(STATUS = 1 AND YEAR(OUTTIME) = YEAR(NOW()) OR NULL) NUMBER, USER.USERNAME USERNAME FROM USER, EXPRESS WHERE USER.USERPHONE = EXPRESS.USERPHONE GROUP BY USERNAME ORDER BY NUMBER DESC ";
    private static String SQL_FIND_ORDER_BY_MONTH = " SELECT COUNT(STATUS = 1 AND YEAR(OUTTIME) = YEAR(NOW()) AND MONTH(OUTTIME) = MONTH(NOW()) OR NULL) NUMBER, USER.USERNAME USERNAME FROM USER, EXPRESS WHERE USER.USERPHONE = EXPRESS.USERPHONE GROUP BY USERNAME ORDER BY NUMBER DESC ";



    //用户登录时间更新
    private static final String SQL_UPDATE_USER_LOGIN_TIME = "UPDATE USER SET LOGINTIME=?WHERE USERPHONE=?";
    //快递员信息的删除
    public static final  String SQL_DELETE_USER = "DELETE FROM USER WHERE ID=?";


    /**
     * 用于查询数据库中的全部快用户(总数+日注册量),
     *
     * @return [{size:总人数,day:日注册量}]
     */
    @Override
    public List<Map<String, Integer>> consoleUser() {
        ArrayList<Map<String,Integer>> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_CONSOLE_USER);
            //3.    填充参数(可选)
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            if (resultSet.next()){
                int data4_size = resultSet.getInt("data4_size");
                int data4_day = resultSet.getInt("data4_day");

                Map data1 = new HashMap();//集合
                data1.put("data4_size",data4_size);
                data1.put("data4_day",data4_day);

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
     * 用于查询所有用户的信息
     *
     * @param limit      是否分页的标记,true表示分页. false表示不分页
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 用户的集合
     */
    @Override
    public List<User> findAllUser(boolean limit, int offset, int pageNumber) {
        ArrayList<User> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            if (limit){
                state = conn.prepareStatement(SQL_FIND_USER_LIMIT);
                //3.    填充参数(可选)
                state.setInt(1,offset);
                state.setInt(2,pageNumber);
            }else{
                state = conn.prepareStatement(SQL_FIND_ALL_USER);
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
                Timestamp registrationTime = resultSet.getTimestamp("registrationTime");
                Timestamp loginTime = resultSet.getTimestamp("loginTime");
                User u = new User(id,username,userPhone,identifyCard,password,registrationTime,loginTime);
                data.add(u);
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
     * 根据用户的手机号码.查询用户的所有记录
     *
     * @param userPhone 手机号码
     * @return 查询的用户信息列表
     */
    @Override
    public User findByUserPhone(String userPhone) {
        //ArrayList data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        ResultSet resultSet = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_USER_USERPHONE);
            //3.    填充参数(可选)
            state.setString(1,userPhone);
            //4.    执行SQL语句
            resultSet = state.executeQuery();
            //5.    获取执行的结果
            User c = null;
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String identifyCard = resultSet.getString("identifyCard");
                String password = resultSet.getString("password");
                Timestamp registrationTime = resultSet.getTimestamp("registrationTime");
                Timestamp loginTime = resultSet.getTimestamp("loginTime");
                 c = new User(id,username,userPhone,identifyCard,password,registrationTime,loginTime);
                //data.add(c);
            }
            return c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6.    资源的释放
            DruidUtil.close(conn,state,resultSet);
        }
        return null;
    }

    /**
     * 用户的录入
     *
     * @param u 要录入的用户对象
     * @return 录入的结果, true表示成功, false表示失败
     */
    @Override
    public boolean insertUser(User u) throws DuplicateIdentifyCardException {
        //1.    获取数据库的连接的获取
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_INSERT_USER);
            //3.    填充参数

            state.setString(1,u.getUsername());
            state.setString(2,u.getUserPhone());
            state.setString(3,u.getIdentifyCard());
            state.setString(4,u.getPassword());
            //4.    执行SQL语句,并获取执行结果
            return state.executeUpdate()>0?true:false;
        } catch (SQLException e1) {
            /* throwables.printStackTrace();*/
            if (e1.getMessage().endsWith("for key 'identifyCard'")){
                //是因为身份证号重复,而出现了异常
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
     * 未注册的手机号
     * 注册手机号 录入用户表
     *
     * @param u 要录入的用户对象
     * @return 录入的结果, true表示成功, false表示失败
     */
    @Override
    public boolean insertUserRegistration(User u) throws DuplicateIdentifyCardException{
        //1.    获取数据库的连接的获取
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_INSERT_USER_REGISTRATION);
            //3.    填充参数
            state.setString(1,u.getUserPhone());
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
     * 用户信息的修改
     *
     * @param id      要修改的用户id
     * @param newUser 新的用户对象(username,userPhone,identifyCard,password)
     * @return
     */
    @Override
    public boolean updateUser(int id, User newUser) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state =null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_USER);
            //3.    填充参数(可选)
            state.setString(1,newUser.getUsername());
            state.setString(2,newUser.getUserPhone());
            state.setString(3,newUser.getIdentifyCard());
            state.setString(4,newUser.getPassword());
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

    @Override
    public void updateUserLoginTime(Date loginTime, String userPhone) {
        //1.    获取连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_USER_LOGIN_TIME);
            //3.    填充参数
            state.setTimestamp(1,new Timestamp(loginTime.getTime()));

            state.setString(2,userPhone);
            //4.    执行
            state.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //5.    释放资源
            DruidUtil.close(conn,state,null);
        }
    }

    /**
     * 用于排序查询快递数靠前的用户
     * @param type  0表示查询总排名,1表示年排名,2表示月排名
     * @return  返回这个User只放username和expressNumber快递数(不属于数据库中user表的一部分)
     */
    @Override
    public List<User> lazyboard(int type) {
        ArrayList<User>  data = new ArrayList<>();
        //1.获取连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        //2.预编译
        try {
            switch (type) {
                case 0:
                    state = conn.prepareStatement(SQL_FIND_ORDER_BY_ALL);
                    break;
                case 1:
                    state = conn.prepareStatement(SQL_FIND_ORDER_BY_YEAR);
                    break;
                case 2:
                    state = conn.prepareStatement(SQL_FIND_ORDER_BY_MONTH);
                    break;
                default:
                    break;
            }
            //3.填充参数(可选)
            //4.执行SQL语句
            result = state.executeQuery();
            //5.获取结果
            while (result.next()) {
                //int number = result.getInt("number");
                String username = result.getString("username");
                User user = new User();
                //user.setNumber(number);
                user.setUsername(username);
                data.add(user);
            }
            return data;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, result);
        }
        return null;
    }


    /**
     * 根据id，删除单个用户信息
     *
     * @param id 要删除的用户id
     * @return 删除的结果，true表示成功，false表示失败
     */
    @Override
    public boolean deleteUser(int id) {
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_DELETE_USER);
            //3.    填充参数(可选)
            state.setInt(1, id);
            //4.    执行SQL语句,并获取执行的结果
            return state.executeUpdate() > 0 ? true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.    资源的释放
            DruidUtil.close(conn, state, null);
        }
        return false;
    }
}
