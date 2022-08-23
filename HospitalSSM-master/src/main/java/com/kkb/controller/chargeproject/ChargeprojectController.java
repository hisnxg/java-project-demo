package com.kkb.controller.chargeproject;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Chargeproject;
import com.kkb.service.chargeproject.ChargeprojectService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.chargeproject.req.AddChargeprojectParam;
import com.kkb.vo.chargeproject.req.ChaChongParam;
import com.kkb.vo.chargeproject.req.SelectChergeproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hospital")
public class ChargeprojectController {

    @Autowired
    private ChargeprojectService chargeprojectService;

    /**
     * 添加收费项目
     *
     * @param param
     * @return
     */
    @PostMapping("addchargeproject")
    public BaseResp<Map<String, Object>> addchargeproject(AddChargeprojectParam param) {

        try {
            param.setChapStime(new Date());
            chargeprojectService.addChargeproject(param);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "添加失败");
        }
        return new BaseResp<>();
    }

    /**
     * 根据参数查询收费项目信息
     *
     * @param param
     * @return
     */
    @PostMapping("selectChargeproject")
    public BaseResp<Map<String, Object>> selectChargeproject(SelectChergeproject param) {
        PageInfo<Chargeproject> chargeprojectPage = chargeprojectService.selectChargeproject(param);
        Map<String, Object> map = new HashMap();
        map.put("chargeprojectPage", chargeprojectPage);
        map.put("param", param);
        return new BaseResp<>(map);

    }


    /**
     * 添加查重
     *
     * @param chapName
     * @return
     */
    @PostMapping("/chachong")
    public BaseResp<Map<String, Object>> chaChong(String chapName) {
        ChaChongParam chaChongParam = new ChaChongParam();
        chaChongParam.setChapName(chapName);
        Chargeproject chargeproject = chargeprojectService.selectByChapName(chaChongParam);

        Map<String, Object> map = new HashMap<>();
        map.put("chargeproject", chargeproject);
        return new BaseResp<>(map);


    }

    /**
     * 修改数据回显
     *
     * @param chapId
     * @return
     */
    @PostMapping("selectByChapId")
    public BaseResp<Chargeproject> selectByChapId(Chargeproject chapId) {
        try {
            Chargeproject chargeproject = chargeprojectService.selectByChapId(chapId);
            return new BaseResp<>(chargeproject);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("100", false, "查询失败");
        }

    }

    /**
     * 修改
     *
     * @param chapId
     * @return
     */
    @GetMapping("updateByPrimaryKeySelective")
    public BaseResp<Map<String, Object>> updateByPrimaryKeySelective(Chargeproject chapId) {
        try {
            chargeprojectService.updateByPrimaryKeySelective(chapId);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("100", false, "修改失败");
        }
        return new BaseResp<>();
    }

    /**
     * 删除
     *
     * @param chapId
     * @return
     */
    @GetMapping("deleteById")
    public BaseResp<Map<String, Object>> deleteById(Integer chapId) {
        try {
            chargeprojectService.deleteById(chapId);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("100", false, "删除失败");
        }
        return new BaseResp<>();
    }


}
