package com.xzk.controller;

import com.xzk.bean.BootStrapTableExpress;
import com.xzk.bean.Express;
import com.xzk.bean.Message;
import com.xzk.bean.ResultData;

import com.xzk.service.ExpressService;
import com.xzk.util.DateFormatUtil;
import com.xzk.util.JSONUtil;
import com.xzk.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ExpressController {

    /**
     * 控制台部分
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/express/console.do")
    @ResponseBody
    public String  console(HttpServletRequest req, HttpServletResponse resp){
        List<Map<String, Integer>> data = ExpressService.console();
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
    @RequestMapping("/express/list.do")
    @ResponseBody
    public String list(HttpServletRequest req, HttpServletResponse resp){
        //1.    获取查询数据的起始索引值
        int offset =Integer.parseInt(req.getParameter("offset"));
        //2.    获取当前要查询的数据量
//        int pageNumber =req.getParameter("pageNumber")==null?0:Integer.parseInt(req.getParameter("pageNumber"));
        int pageNumber =Integer.parseInt(req.getParameter("pageNumber"));
        //3.    进行查询
        List<Express> list = ExpressService.findAll(true,offset,pageNumber);
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

        List<Map<String, Integer>> console = ExpressService.console();
        Integer total = console.get(0).get("data1_size");
        //4.    `将集合封装为 bootstrap-table识别格式
        ResultData<BootStrapTableExpress> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        //转换为JSON格式
        String json = JSONUtil.toJSON(data);
        return json;//返回给客户端
    }

    /**
     * 录入快递信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/express/insert.do")
    @ResponseBody
    public String insert(HttpServletRequest req,HttpServletResponse resp){
        String number = req.getParameter("number");
        String company = req.getParameter("company");
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        //注意,Express中的顺序需要按照Express对象中参数的顺序
        Express e = new Express(number,username,userPhone,company,UserUtil.getSysPhone(req.getSession()));
        boolean flag = ExpressService.insert(e);
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
    @RequestMapping("/express/find.do")
    @ResponseBody
    public String find(HttpServletRequest req,HttpServletResponse resp){
        String number = req.getParameter("number");//获取参数
        Express e = ExpressService.findByNumber(number);
        Message msg = new Message();
        if(e == null){
            msg.setStatus(-1);
            msg.setResult("单号不存在");
        }else {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 修改快递信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/express/update.do")
    @ResponseBody
    public String update(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        String number = req.getParameter("number");
        String company = req.getParameter("company");
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        int status = Integer.parseInt(req.getParameter("status"));

        Express newExpress = new Express();
        newExpress.setNumber(number);
        newExpress.setCompany(company);
        newExpress.setUsername(username);
        newExpress.setUserPhone(userPhone);
        newExpress.setStatus(status);
        boolean flag = ExpressService.update(id, newExpress);
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
     * 删除快递信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/express/delete.do")
    @ResponseBody
    public String delete(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        boolean flag = ExpressService.delete(id);
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
