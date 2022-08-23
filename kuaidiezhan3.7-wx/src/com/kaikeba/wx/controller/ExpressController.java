package com.kaikeba.wx.controller;

import com.kaikeba.bean.BootStrapTableExpress;
import com.kaikeba.bean.Express;
import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.exception.DuplicateIdentifyCardException;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.service.ExpressService;
import com.kaikeba.service.UserService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExpressController {

    /**
     * wx用户通过手机号方式查询快递信息
     * @param req
     * @param resp
     * @return
     * 注意需要是用户登录后才行,不然又bug
     */
    @ResponseBody("/wx/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest req, HttpServletResponse resp){
        //获取当前wx用户手机号, 进行相应处理
        User wxUser = UserUtil.getWxUser(req.getSession());
        String userPhone = wxUser.getUserPhone();
        //用户列表
        List<Express> list = ExpressService.findByUserPhone(userPhone);
        ArrayList<BootStrapTableExpress> list2 = new ArrayList<>();
        //对集合进行格式转换
        for (Express e:list){
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserPhone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        Message msg = new Message();
        if (list.size()==0){
            //查询后,没有快递
            msg.setStatus(-1);
        }else{
            msg.setStatus(0);
            //这里也可以使用for循环遍历添加到集合的方式进行添加,下面是使用新的技术
            //JDK8新技术  Stream流  更简单的处理抽象的数据
            //状态status0Express的快递信息  未出库
            Stream<BootStrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;//如果是,则在流中保留
                } else {
                    return false;
                }
            }).sorted((o1, o2) -> {
                //排序   按时间来计算
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int) (o1Time-o2Time);//装换成int类型
            });
            //状态status1Express的快递信息 已出库
            Stream<BootStrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;//如果是,则在流中保留
                } else {
                    return false;
                }
            }).sorted((o1, o2) -> {
                //排序   按时间来计算
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int) (o1Time-o2Time);//装换成int类型
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap();//哈希map 集合
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        String json = JSONUtil.toJSON(msg.getData());
        return json;
    }

    @ResponseBody("/wx/userExpressList.do")
    public String expressList(HttpServletRequest req,HttpServletResponse resp){
        String userPhone = req.getParameter("userPhone");
        List<Express> list = ExpressService.findByUserPhoneAndStatus(userPhone, 0);
        ArrayList<BootStrapTableExpress> list2 = new ArrayList<>();
        //对集合进行格式转换
        for (Express e:list){
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserPhone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        Message msg = new Message();
        if (list.size() == 0){
            msg.setStatus(-1);
            msg.setResult("未查询到快递信息");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(list2);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/userFindByNumber.do")
    public String userFindByNumber(HttpServletRequest req,HttpServletResponse resp){
        //1.获取参数
        String number = req.getParameter("number");
        Express e = ExpressService.findByNumber(number);
        BootStrapTableExpress e2 = null;
        if(e!=null) {//不等于空,才转换
             e2 = new BootStrapTableExpress(e.getId(), e.getNumber(), e.getUsername()
                    , e.getUserPhone(), e.getCompany(), e.getCode(),
                    DateFormatUtil.format(e.getInTime()), e.getOutTime() == null ? "未出库" : DateFormatUtil.format(e.getOutTime()), e.getStatus() == 0 ? "待取件" : "已取件", e.getSysPhone());
        }
        Message msg = new Message();
        if (e == null){
            msg.setStatus(-1);
            msg.setResult("快递单号不存在");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e2);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 用户认证个人信息
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/wx/userInsertPerson.do")
    public String userInsertPerson(HttpServletRequest req,HttpServletResponse resp){
        //需要判断输入的验证码与手机短信发送的验证码是否一致
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        String password = req.getParameter("password");
        String code = req.getParameter("code");//获取的验证码
        User user = new User();//需要传相应的参数值
        Message msg = new Message();
        if (user!=null) {
            try {
                boolean flag = UserService.insertUser(user);
                if (flag){
                    msg.setStatus(0);
                    msg.setResult("认证成功");
                    msg.setData(user);
                }else{
                    msg.setStatus(-1);
                    msg.setResult("认证失败");
                }

            } catch (DuplicateIdentifyCardException duplicateIdentifyCardException) {
                duplicateIdentifyCardException.printStackTrace();
            }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 快递员录入快递信息
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/wx/courierInsertExpress.do")
    public String courierInsertExpress(HttpServletRequest req,HttpServletResponse resp){
        User wxUser = UserUtil.getWxUser(req.getSession());
        String sysPhone = wxUser.getUserPhone();
        String number = req.getParameter("number");
        String username = req.getParameter("username");
        String userPhone = req.getParameter("phone");
        String company = req.getParameter("company");
        Express e = new Express(number,username,userPhone,company,sysPhone);
        boolean flag = false;
        if (e!=null){
             flag = ExpressService.insert(e);
        }

        Message msg = new Message();
        if (flag){
            msg.setStatus(0);
            msg.setResult("录入成功");
            msg.setData(e);
        }else {
            msg.setStatus(-1);
            msg.setResult("录入失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }


}
