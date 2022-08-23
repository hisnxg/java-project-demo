package com.nxg.upload.util;

import com.github.tobato.fastdfs.domain.fdfs.StorageState;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nxg
 * date 2022/3/8
 * @apiNote
 */
@Component //反转到spring容器
public class FileDfsUtil {

    @Autowired
    private FastFileStorageClient fileStorageClient;
/**
 * http://localhost:7010/swagger-ui.html
 * http://192.168.72.130/group1/M00/00/00/wKhIgl0n4AKABxQEABhlMYw_3Lo825.png
 */

    /**
     * 文件上传
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public String upload(MultipartFile multipartFile) throws  Exception{
        String extensionName = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        StorePath storePath = fileStorageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), extensionName,null);

        return storePath.getFullPath();// "group1/M00/00/00/wKhIgl0n4AKABxQEABhlMYw_3Lo825.png"
    }


    /**
     * 删除文件
     * @param fileUrl
     */
    public void deleteFile(String fileUrl){
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        fileStorageClient.deleteFile(storePath.getGroup(),storePath.getPath());

    }
}
