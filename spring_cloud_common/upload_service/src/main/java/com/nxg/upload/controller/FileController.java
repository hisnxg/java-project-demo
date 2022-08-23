package com.nxg.upload.controller;

import com.nxg.upload.util.FileDfsUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nxg
 * date 2022/3/8
 * @apiNote
 */
@RestController
public class FileController {

    @Autowired
    private FileDfsUtil fileDfsUtil;
    /**
     * http://localhost:7010/swagger-ui.html
     * http://192.168.72.130/group1/M00/00/00/wKhIgl0n4AKABxQEABhlMYw_3Lo825.png
     */

    /**
     *文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile",headers = "content-type=multipart/form-data",method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

//        return ResponseEntity.ok("success"); //200 success
//        return  new ResponseEntity<>("错误请求", HttpStatus.BAD_GATEWAY);// 400 bad

        String result = "";
        try{
            String path = fileDfsUtil.upload(file);
            if(StringUtils.isEmpty(path)){
                result = "删除失败";
            }else {
                result = path;
            }

        }catch (Exception e){
            e.printStackTrace();
            result = "服务异常";
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 删除文件
     * @param filePathName
     * @return
     */
    @RequestMapping(value = "/deleteByPath",method = RequestMethod.GET)
    public ResponseEntity<String> deleteFile(String filePathName) {
        //filePathName //group1/M00/00/00/wKhIgl0n4AKABxQEABhlMYw_3Lo825.png
        fileDfsUtil.deleteFile(filePathName);
        return ResponseEntity.ok("success delete");
    }
}
