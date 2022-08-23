package com.kkb.controller.Drugpeople;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Drugpeople;
import com.kkb.service.Drugpeople.DrugpeopleService;
import com.kkb.vo.BaseResp;
import com.kkb.vo.Drugpeople.req.SelectDrugpeopleReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sufenglong
 * @date 2021/3/24 22:21
 * @describe:
 */
@RestController
@RequestMapping("/hospital")
public class DrugpeopleController {

    @Autowired
    private DrugpeopleService drugpeopleService;


    /**
     * 查询
     *
     * @param param
     * @return
     */
    @PostMapping("/selectDrugpeopleByParam")
    public BaseResp<Map<String, Object>> selectDrugpeopleByParam(SelectDrugpeopleReq param) {
        PageInfo<Drugpeople> pageInfo = drugpeopleService.selectDrugPeopleByParam(param);

        Map<String, Object> map = new HashMap<>();
        map.put("drugpeople", pageInfo);
        return new BaseResp<>(map);
    }


    /**
     * 详情显示
     *
     * @param peopleId
     * @return
     */
    @PostMapping("selectDrugPeopleById")
    public BaseResp<Map<String,Object>> selectDrugPeopleById(Integer peopleId,@RequestParam(defaultValue = "1") Integer pageNum) {

        try {
            Map<String, Object> map = drugpeopleService.selectDrugPeopleById(peopleId, pageNum);
            return new BaseResp<>(map);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "查询失败");
        }

    }

    /**
     * 添加发药
     *
     * @param drugpeople
     * @return
     */
    @PostMapping("/addSendMedicine")
    public BaseResp<Map<String, Object>> sendMedicine(Drugpeople drugpeople) {
        try {
            drugpeopleService.addSendDrug(drugpeople);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "添加失败");
        }
        return new BaseResp<>();

    }

    /**
     * 全能发药
     *
     * @param peopleId
     * @return
     */
    @RequestMapping(value = "/updateSendDrug", method = RequestMethod.POST)
    public BaseResp<Map<String, Object>> updateSendDrug(Integer peopleId, Integer yao,Integer drugId) {


            //Drugpeople drugpeople = new Drugpeople();
        Drugpeople drugpeople = drugpeopleService.selectByHosrIdAndDrugId(peopleId,drugId);
        drugpeople.setPeopleId(peopleId);
        //已发数量
        Integer number1 = drugpeople.getDrNumberIssued();
        //未发数量
        Integer number2 = drugpeople.getDrNumberWeifa();
        //总数量
        Integer number3 = drugpeople.getDrNumber();

//        if (number2 < yao) {
//            //return new BaseResp<>("1000", false, "药品数量不足");
//            number2 = 0;
//            number1 = number3;
//
//        }

        if (yao == -1 || number2 < yao){
            number2 = 0;
            number1 = number3;
        }else {
            //未发数量
            number2 = number2 - yao;
            //已发
            number1 = number1 + yao;
        }

        drugpeople.setDrNumberIssued(number1);
        drugpeople.setDrNumberWeifa(number2);
        try {
            drugpeopleService.updateSendDrug(drugpeople);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000", false, "修改失败");
        }
        return new BaseResp<>("200", true, "修改成功");
    }


}
