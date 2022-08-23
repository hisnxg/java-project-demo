package com.kkb.controller.drugmanager;


import com.github.pagehelper.PageInfo;
import com.kkb.bean.Doctor;
import com.kkb.bean.Drug;
import com.kkb.constant.ProjectConstant;
import com.kkb.service.drugmanager.DrugManagerService;
import com.kkb.service.drugmanager.impl.DrugManagerServiceImpl;
import com.kkb.utils.DownLoadIMGUtils;
import com.kkb.vo.BaseResp;
import com.kkb.vo.drugmanager.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  @author: MaXia
 *  @Date: 2021/3/18 23:45
 *  @Description:
 */
@RestController
@RequestMapping("/medicine")
public class DrugManagerController {

    @Autowired
    private DrugManagerService drugManagerService;

    @GetMapping("queryDrug")
    public BaseResp<Map<String,Object>> queryDrug(SelectDrugReq param){
        if (param.getPageNum()==null){
            param.setPageNum(1);
        }
        PageInfo<Drug> drugPage = drugManagerService.queryDrugByParam(param);
        Map<String,Object> map = new HashMap<>();
        map.put("drugPage",drugPage);
        map.put("param",param);
        return new BaseResp<>(map);
    }

    @GetMapping("queryDrugById")
    public BaseResp<Map<String,Object>> selectDrugById(SelectOneParam param){
        Map<String,Object> map = null;
        try {
            map = drugManagerService.selectDrugByDrugId(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"查询失败");
        }
        return new BaseResp<>(map);
    }

    @PostMapping("addNewDrug")
    public BaseResp<Map<String,Object>> addNewDrug(AddDrugParam param){
        try {
            drugManagerService.addNewDrug(param);
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResp<>("1000",true,"添加失败");
        }
        return new BaseResp<>();
    }

    @PostMapping("addDrugNumber")
    public BaseResp<Map<String,Object>> addDrugNumber( AddDrugNumberParam param){
        System.out.println(param);
        try {
            drugManagerService.addDrugNumber(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"添加库存失败");
        }
        return new BaseResp<>();
    }

    @PostMapping("updateDrugById")
    public BaseResp<Map<String,Object>> updateDrugById(UpdateDrugParam param){
        try {
            drugManagerService.updateByPrimaryKeySelective(param);
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResp<>("1000",true,"更新失败");
        }
        return new BaseResp<>();
    }

    /**
     * 导出excel
     * @param drugIds
     * @return
     */
    @GetMapping("exportExcelDrug")
    public BaseResp<String> exportExcelDrug(Integer[] drugIds){
        /*try {
            doctorManagerService.exportExcelDoctor(Arrays.asList(doctorIds));
            return new BaseResp<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1002",true,"医生修改失败");
        }*/
        try {
            drugManagerService.exportExcelDrug(Arrays.asList(drugIds));
            return new BaseResp<>();
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResp<>("1002",true,"导出药品失败");
        }
    }

    @PostMapping({"/uploadIMGFile"})
    @ResponseBody
    public BaseResp<Map<String,Object>> upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(ProjectConstant.DOWNLOAD_PATH);
        //创建文件
        File destFile = new File(ProjectConstant.DOWNLOAD_PATH + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            String uri = DownLoadIMGUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName;
            Map<String,Object> map = new HashMap<>();
            map.put("uri",uri);
            return new BaseResp<>(map);
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResp<>("1002",true,"图片上传失败");
        }
    }
}
