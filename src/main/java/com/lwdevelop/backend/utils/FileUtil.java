package com.lwdevelop.backend.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    public static String upload(MultipartFile file, String path, String fileName){
 
        String newFileName = getFileName(fileName);
 
        // 生成新的文件名
        String realPath = path + "/" + newFileName;
 
        File dest = new File(realPath);
 
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
 
        try {
            //保存文件
            file.transferTo(dest);
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
 
    }


    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
 
    /**
     * 和随机的UUID生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return getUUID() + getSuffix(fileOriginName);
    }

    
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
