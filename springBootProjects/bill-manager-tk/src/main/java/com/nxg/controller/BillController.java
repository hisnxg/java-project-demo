package com.nxg.controller;

import com.github.pagehelper.PageInfo;
import com.nxg.entity.Bill;
import com.nxg.entity.BillType;
import com.nxg.service.BillService;
import com.nxg.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author nxg
 * date 2022/1/13
 * @apiNote
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillTypeService billTypeService;

    /**
     * 查询分页
     * @param pageNum
     * @param pageSize
     * @param b 查询条件
     * @param model
     * @return
     */
    @RequestMapping("/list-page")
    public  String listPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Bill b,
            Model model){
        List<BillType> types = billTypeService.list();
        model.addAttribute("types",types);

        PageInfo<Bill> pageInfo = billService.listPage(b,pageNum,pageSize);
        model.addAttribute("page",pageInfo);
        model.addAttribute("bill",b);
        return "/bill/list-page";
    }
    /**
     * 查询
     * @param b
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Bill b, Model model){
        List<BillType> types = billTypeService.list();
        model.addAttribute("types",types);

        List<Bill> list = billService.billList(b);
        model.addAttribute("bills",list);
        model.addAttribute("bill",b);

        return "/bill/list";
    }

    /**
     * 添加到页面
     * @param model
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<BillType> types = billTypeService.list();
        model.addAttribute("types", types);
        return "/bill/add";
    }

    /**
     * 添加
     * @param b
     * @return
     */
    @RequestMapping("/add")
    public String add(Bill b){
         billService.add(b);
        return "redirect:/bill/list-page";//重定向
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        billService.delete(id);
        return "redirect:/bill/list-page";
    }

    /**
     * 修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Long id,Model model){
        List<BillType> types = billTypeService.list();
        model.addAttribute("types",types);

        Bill bill = billService.get(id);
        model.addAttribute("bill",bill);

        return "/bill/update";
    }

    /**
     * 修改
     * @param bill
     * @return
     */
    @RequestMapping("/update")
    public String update(Bill bill){
        billService.update(bill);
        return "redirect:/bill/list-page";
    }


}
