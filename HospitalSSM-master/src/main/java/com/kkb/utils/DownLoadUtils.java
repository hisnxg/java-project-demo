package com.kkb.utils;

import com.kkb.constant.ProjectConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author mj
 * @create 2021-03-22 16:01
 * @describe:
 */
@Controller
public class DownLoadUtils {

    @GetMapping("/downloadFile")
    public void downloadFile(String fileName, HttpServletResponse response) throws FileNotFoundException {

        // 读到流中
        InputStream inStream = new FileInputStream(ProjectConstant.DOWNLOAD_PATH+fileName);
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
