package com.itStuManager.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    public static String upload(HttpServletRequest request) throws IOException, ServletException {
        //获取文件资源
        Part part = request.getPart("fileName");
        //从文件资源中获取文件名
        String fileName = part.getSubmittedFileName();
        //重命名文件名
        fileName = UUID.randomUUID() + fileName;
        //组装写入地址
        File file = new File("D:/x_upload");
        if (!file.exists()){
            //创建文件夹
            file.mkdir();
        }

        String path = file+"/"+fileName;
        //将文件写入到指定位置
        part.write(path);
        //返回文件名
        return fileName;
    }
}
