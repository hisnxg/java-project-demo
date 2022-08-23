package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.pojo.Drug;
import com.kaikeba.service.DrugService;
import com.kaikeba.vo.QueryDrugVO;
import com.kaikeba.vo.ResultVO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * @description 药品管理的控制器
 */
@Controller
@RequestMapping("drug")
@ResponseBody
public class DrugController {
    @Resource
    DrugService drugService;

    /**
     * 药瓶管理主页面展示数据
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Drug> queryByPage(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue="5")Integer pageSize, QueryDrugVO vo){
        PageInfo<Drug> info = drugService.queryByPage(pageNum, pageSize, vo);
        return new ResultVO<>(info);
    }

    /**
     * 根据药品id查询药品
     */
    @RequestMapping(value="{id}",method = RequestMethod.GET)
    public ResultVO<Drug> queryById(@PathVariable("id") Integer id){
        Drug drug = drugService.queryById(id);
        return new ResultVO<>(drug);
    }

    /**
     * 添加一个新药品
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO<Drug> addDrug(Drug drug){
        System.out.println(drug);
        int i = drugService.addDrug(drug);
        if(i==1){
            return new ResultVO<Drug>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");

    }

    /**
     * 更新药品
     */
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO<Drug> updateDrug(@PathVariable("id")Integer id, Drug drug){
        drug.setId(id);
        int i = drugService.updateDrug(drug);
        if(i==1){
            return new ResultVO<Drug>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }

    /**
     * 添加库存
     */
    @RequestMapping(value="/{id}/{resetNumber}/{increase}",method = RequestMethod.PUT)
    public ResultVO<Drug> addNumber(@PathVariable("id")Integer id,@PathVariable("resetNumber")Integer resetNumber, @PathVariable("increase") Integer increase){
        Drug old = drugService.queryById(id);
        Drug drug = new Drug();
        drug.setId(id);
        drug.setTotalNumber(old.getTotalNumber()+increase);
        drug.setResetNumber(resetNumber+increase);
        int i = drugService.updateDrug(drug);
        if(i==1){
            return new ResultVO<Drug>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }

    /**
     * 上传图片
     */
    @RequestMapping(value = "{drugId}",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Drug> uploadLogo(@RequestParam("logo") MultipartFile myFile, HttpServletRequest request, @PathVariable("drugId")Integer drugId){

        System.out.println("--------------upload-------------------");
        //文件上传到指定的服务器位置
        String path = request.getServletContext().getRealPath("/Images/");
        System.out.println(path);
        //获取原始文件的名称
        String originalFilename = myFile.getOriginalFilename();
        //定义文件的新名称：随机生成名称+原有文件的后缀名
        String randomName = UUID.randomUUID().toString().replace("-", "");
        int index = originalFilename.lastIndexOf(".");
        String hz = originalFilename.substring(index);
        String logoName = randomName + hz;
        System.out.println("loginName:"+logoName);
        int num = 0;

        try {
            myFile.transferTo(new File(path+"/"+logoName));
            System.out.println("上传成功！"+path+"/"+logoName);
            //将图片的名称更新到数据库中
            Drug drug = new Drug();
            drug.setId(drugId);
            drug.setImg(logoName);
            num = drugService.updateDrug(drug);
            if (num==1){
                return new ResultVO<Drug>();
            }else{
                return new ResultVO<>(500,"服务器内部异常，请稍后再试!");
            }

        }catch (IOException e){
            e.printStackTrace();
            return new ResultVO<>(500,"图片上传出现问题！"+e.getMessage());
        }

    }

    /**
     * 导出药品表里所有的数据
     */
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        System.out.println("导出到excel");
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            //创建sheet
            HSSFSheet sheet = workbook.createSheet();
            //定义头标题
            String title[] = {"药品编号","药品名称","药品图片","进价","售价","类型","简述","保质期","详细信息","生产厂家","服用说明","库存","备注"};

            //先创建标题
            HSSFRow titleRow = sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                titleRow.createCell(i).setCellValue(title[i]);
            }
            //查询数据库
            List<Drug> list = drugService.selectAll();
            for(int i=0;i<list.size();i++){
                HSSFRow row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(list.get(i).getId());
                row.createCell(1).setCellValue(list.get(i).getDrugName());
                row.createCell(2).setCellValue(list.get(i).getImg());
                row.createCell(3).setCellValue(list.get(i).getInPrice());
                row.createCell(4).setCellValue(list.get(i).getOutPrice());
                String type = list.get(i).getDrugType();
                row.createCell(5).setCellValue(type);
                row.createCell(6).setCellValue(list.get(i).getDescription());
                row.createCell(8).setCellValue(list.get(i).getQuality());
                row.createCell(9).setCellValue(list.get(i).getDescription());
                row.createCell(10).setCellValue(list.get(i).getFactory());
                row.createCell(11).setCellValue(list.get(i).getResetNumber());
                row.createCell(12).setCellValue(list.get(i).getNote());

            }

            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("药品信息列表.xls", "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");
            workbook.write(response.getOutputStream());
            workbook.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



