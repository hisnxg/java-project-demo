package com.xzk.controller;

import com.xzk.bean.*;

import com.xzk.service.CourierService;

import com.xzk.util.DateFormatUtil;
import com.xzk.util.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CourierController {

    /**
     * 控制台部分
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/courier/console_c.do")
    @ResponseBody
    public String  consoleCourier(HttpServletRequest req, HttpServletResponse resp){
        List<Map<String, Integer>> data = CourierService.consoleCourier();
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
    @RequestMapping("/courier/list.do")
    @ResponseBody
    public String listCourier(HttpServletRequest req, HttpServletResponse resp){
        //1.    获取查询数据的起始索引值
        int offset =Integer.parseInt(req.getParameter("offset"));
        //2.    获取当前要查询的数据量
      //int pageNumber =req.getParameter("pageNumber")==null?0:Integer.parseInt(req.getParameter("pageNumber"));
        int pageNumber =Integer.parseInt(req.getParameter("pageNumber"));
        //3.    进行查询
        List<Courier> list = CourierService.findAllCourier(true,offset,pageNumber);
        ArrayList<BootStrapTableCourier> list2 = new ArrayList<>();
        //对集合进行格式转换
        for (Courier c:list){
            String  registrationTime = DateFormatUtil.format(c.getRegistrationTime());
            String loginTime =c.getLoginTime()==null?"":DateFormatUtil.format(c.getLoginTime());
            String dispatches = String.valueOf(c.getDispatches());
            BootStrapTableCourier c2 = new BootStrapTableCourier(c.getId(),c.getUsername(),c.getUserPhone(),c.getIdentifyCard(),c.getPassword(),dispatches,registrationTime,loginTime);
            list2.add(c2);
        }

        List<Map<String, Integer>> console = CourierService.consoleCourier();
        Integer total = console.get(0).get("data3_size");
        //4.    `将集合封装为 bootstrap-table识别格式
        ResultData<BootStrapTableCourier> data = new ResultData<>();
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
    @RequestMapping("/courier/insert.do")
    @ResponseBody
    public String insert(HttpServletRequest req,HttpServletResponse resp){
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        String identifyCard = req.getParameter("identifyCard");
        String password = req.getParameter("password");
        //注意,Courier中的顺序需要按照Courier对象中参数的顺序
        Courier c = new Courier(username,userPhone,identifyCard,password);
        boolean flag = false;
            flag = CourierService.insertCourier(c);

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
    @RequestMapping("/courier/find.do")
    @ResponseBody
    public String findCourier(HttpServletRequest req,HttpServletResponse resp){
        String userPhone = req.getParameter("userPhone");//获取参数
        Courier c = CourierService.findByCourierPhone(userPhone);
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
    @RequestMapping("/courier/update.do")
    @ResponseBody
    public String updateCourier(HttpServletRequest req,HttpServletResponse resp){
        //int id = req.getParameter("id")==null?-1:Integer.parseInt(req.getParameter("id"));
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        String identifyCard = req.getParameter("identifyCard");
        String password = req.getParameter("password");

        /*Courier newCourier = new Courier();
        newCourier.setId(id);
        newCourier.setUsername(username);
        newCourier.setUserPhone(userPhone);
        newCourier.setIdentifyCard(identifyCard);
        newCourier.setPassword(password);*/
        Courier c = CourierService.findByCourierId(id);
        c.setUsername(username);
        c.setUserPhone(userPhone);
        c.setIdentifyCard(identifyCard);
        c.setPassword(password);
        boolean flag = CourierService.updateCourier(id, c);
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
     * 删除快递员信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/courier/delete.do")
    @ResponseBody
    public String deleteCourier(HttpServletRequest req,HttpServletResponse resp){
        int id = req.getParameter("id")==null?-1:Integer.parseInt(req.getParameter("id"));
        boolean flag = CourierService.deleteCourier(id);
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
