package com.kaikeba.wx.controller;

import com.kaikeba.bean.*;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.mvc.ResponseView;
import com.kaikeba.service.ExpressService;
import com.kaikeba.util.DateFormatUtil;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QRCodeController {

    /**
     * ResponseView 作用 直接跳转到retrun地址
     * @param req
     * @return
     */
    @ResponseView("/wx/createQRCode.do")
    public String createQRCode(HttpServletRequest req, HttpServletResponse resp){
        String code = req.getParameter("code");
        //express | user
        String type = req.getParameter("type");//类型
        String userPhone = null;
        String qRCodeContent = null;
        if ("express".equals(type)){
            //快递二维码:被扫描后,展示单个快递的信息
            //code
            qRCodeContent = "express_"+code;
        }else{
            //用户二维码:被扫后,快递员(柜子)端展示用户所有快递信息
            //userPhone
            User wxUser = UserUtil.getWxUser(req.getSession());
            userPhone = wxUser.getUserPhone();
            qRCodeContent = "userPhone_"+userPhone;
        }
        HttpSession session =  req.getSession();
        session.setAttribute("QRCode",qRCodeContent);
        return "/personQRcode.html";
    }

    @ResponseBody("/wx/qrcode.do")
    public String getQRCode(HttpServletRequest req,HttpServletResponse resp){
        HttpSession session = req.getSession();
        String qrCode = (String) session.getAttribute("QRCode");
        Message msg = new Message();
        if (qrCode == null){
            msg.setStatus(-1);
            msg.setResult("取件码获取出错,请用户重新操作");
        }else{
            msg.setStatus(0);
            msg.setResult(qrCode);
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 通过取件码更新取件状态
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/wx/updataStatus.do")
    public String updateExpressStatus(HttpServletRequest req,HttpServletResponse resp){
        String code = req.getParameter("code");
        boolean flag = ExpressService.updateStatus(code);
        Message msg = new Message();
        if (flag){
            //取件成功
            msg.setStatus(0);
            msg.setResult("取件成功");
        }else {
            msg.setStatus(-1);
            msg.setResult("取件码不存在,请用户更新二维码");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 通过取件码查询快递信息
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/wx/findExpressByCode.do")
    public  String findExpressByCode(HttpServletRequest req,HttpServletResponse resp){
        String code = req.getParameter("code");
        Express e = ExpressService.findBycode(code);
        BootStrapTableExpress e2 = null;
        if(e!=null) {//不等于空,才转换
             e2 = new BootStrapTableExpress(e.getId(), e.getNumber(), e.getUsername()
                    , e.getUserPhone(), e.getCompany(), code,
                    DateFormatUtil.format(e.getInTime()), e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime()), e.getStatus() == 0 ? "待取件" : "已取件", e.getSysPhone());
        }
        Message msg = new Message();
        if (e == null){
            msg.setStatus(-1);
            msg.setResult("取件码不存在");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e2);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
