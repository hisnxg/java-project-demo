package com.nxg.controller;

import com.github.pagehelper.PageInfo;
import com.nxg.entity.Bill;
import com.nxg.entity.BillType;
import com.nxg.service.BillService;
import com.nxg.service.BillTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author nxg
 * date 2022/1/13
 * @apiNote
 */
@RestController
@RequestMapping("/api/bill")
@RefreshScope
@Slf4j
public class BillController {

    @Value("${key1}")
    private String key1;

    @Value("${key2}")
    private String key2;

    @Autowired
    private BillService billService;

    @Autowired
    private BillTypeService billTypeService;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param b
     * @return
     */
    @GetMapping("/list-page")
    public ResponseEntity listPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            Bill b){
        List<HashMap<String,Object>> arr = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        List<BillType> types = billTypeService.list();
        map.put("types",types);
        PageInfo<Bill> pageInfo = billService.listPage(b,pageNum,pageSize);
        map.put("pageInfo",pageInfo);
        map.put("bill",b);
        arr.add(map);
        return ResponseEntity.ok(arr);
    }

    /**
     *
     * @param b
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity list(Bill b){
        List<HashMap<String,Object>> arr = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        List<BillType> types = billTypeService.list();
        map.put("types",types);

        List<Bill> list = billService.billList(b);
        map.put("list",list);
        map.put("bill",b);
        arr.add(map);
        log.info("key1={}, key2={}, key3={}", key1, key2);
        return ResponseEntity.ok(arr);
    }

    @GetMapping("/toAdd")
    public ResponseEntity toAdd() {
        List<HashMap<String,Object>> arr = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();

        List<BillType> types = billTypeService.list();
        map.put("types",types);
        arr.add(map);
        return ResponseEntity.ok(arr);
    }

    /**
     * 添加
     * @param b
     * @return
     */
    @GetMapping("/doadd")
    public ResponseEntity doAdd(Bill b){
        List arr = new ArrayList();
         billService.add(b);
         arr.add(b);
        return ResponseEntity.ok(arr);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ResponseEntity doDelete(@PathVariable("id") Long id){
        int status = billService.delete(id);
        return ResponseEntity.ok(status);
    }

    /**
     * 修改
     * @param id
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public ResponseEntity<List> toUpdate(@PathVariable("id") Long id){
        List arr = new ArrayList();
        List<BillType> types = billTypeService.list();
        arr.add(types);
        Bill bill = billService.get(id);
        arr.add(bill);

        return ResponseEntity.ok(arr);
    }

    /**
     * 修改
     * @param bill
     * @return
     */
    @GetMapping("/update")
    public ResponseEntity update(Bill bill){
        int status = billService.update(bill);
        return ResponseEntity.ok(status);
    }

}
