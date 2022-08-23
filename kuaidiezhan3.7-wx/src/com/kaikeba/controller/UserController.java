package com.kaikeba.controller;

import com.kaikeba.bean.*;
import com.kaikeba.exception.DuplicateIdentifyCardException;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.UserService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {
    /**
     * 控制台部分
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/user/console_u.do")
    public String  consoleUser(HttpServletRequest req, HttpServletResponse resp){
        List<Map<String, Integer>> data = UserService.consoleUser();
        Message msg = new Message();
        if (data.size() == 0){//判断获取的对象是否为空
            msg.setStatus(-1);//为空则消息状态为-1
        }else{
            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        //返回出去,会自动响应
        return json;
    }

    /**
     *查询列表
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/user/list.do")
    public String listUser(HttpServletRequest req, HttpServletResponse resp){
        //1.    获取查询数据的起始索引值
        int offset = Integer.parseInt(req.getParameter("offset"));
        //2.    获取当前要查询的数据量
//        int pageNumber =req.getParameter("pageNumber")==null?0:Integer.parseInt(req.getParameter("pageNumber"));
        int pageNumber =Integer.parseInt(req.getParameter("pageNumber"));
        //3.    进行查询
        List<User> list = UserService.findAllUser(true,offset,pageNumber);
        ArrayList<BootStrapTableUser> list2 = new ArrayList<>();
        //对集合进行格式转换
        for (User u:list){
            String  registrationTime = DateFormatUtil.format(u.getRegistrationTime());
            String loginTime =u.getLoginTime()==null?"":DateFormatUtil.format(u.getLoginTime());
            BootStrapTableUser u2 = new BootStrapTableUser(u.getId(),u.getUsername(),u.getUserPhone(),u.getIdentifyCard(),u.getPassword(),registrationTime,loginTime);
            list2.add(u2);
        }

        List<Map<String, Integer>> console = UserService.consoleUser();
        Integer total = console.get(0).get("data4_size");
        //4.    `将集合封装为 bootstrap-table识别格式
        ResultData<BootStrapTableUser> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        //转换为JSON格式
        String json = JSONUtil.toJSON(data);
        return json;//返回给客户端
    }

    /**
     * 录入快递员信息
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/user/insert.do")
    public String insertUser(HttpServletRequest req,HttpServletResponse resp){
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        String identifyCard = req.getParameter("identifyCard");
        String password = req.getParameter("password");
        //注意,Courier中的顺序需要按照Courier对象中参数的顺序
        User u = new User(username,userPhone,identifyCard,password);
        boolean flag = false;
        try {
            flag = UserService.insertUser(u);
        } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
            duplicateIdentifyCardException.printStackTrace();
        }
        Message msg = new Message();//创建消息对象
        if(flag){
            //录入成功
            msg.setStatus(0);
            msg.setResult("快递录入成功");
        }else{
            //录入失败
            msg.setStatus(-1);
            msg.setResult("快递录入失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    /**
     * 查询快递单号
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/user/find.do")
    public String findCourier(HttpServletRequest req,HttpServletResponse resp){
        String userPhone = req.getParameter("userPhone");//获取参数
        List c = UserService.findByUserPhone(userPhone);
        Message msg = new Message();
        if(c == null){
            msg.setStatus(-1);
            msg.setResult("单号不存在");
        }else {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(c);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    /**
     * 修改快递员信息
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/user/update.do")
    public String updateUser(HttpServletRequest req,HttpServletResponse resp){
        int id =req.getParameter("id")==null?-1:Integer.parseInt(req.getParameter("id"));

        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        String identifyCard = req.getParameter("identifyCard");
        String password = req.getParameter("password");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setUserPhone(userPhone);
        newUser.setIdentifyCard(identifyCard);
        newUser.setPassword(password);

        boolean flag = UserService.updateUser(id, newUser);
        Message msg = new Message();
        if (flag){
            msg.setStatus(0);
            msg.setResult("修改成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 删除用户信息
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/user/delete.do")
    public String deleteUser(HttpServletRequest req,HttpServletResponse resp){
        int id =req.getParameter("id")==null?-1:Integer.parseInt(req.getParameter("id"));
//        System.out.println("你删除的用户id:"+req.getParameter("id"));
        boolean flag = UserService.deleteUser(id);
        Message msg = new Message();
        if (flag){
            msg.setStatus(0);
            msg.setResult("删除成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
