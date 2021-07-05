package com.itStuManager.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {
    public static String upload(HttpServletRequest request) throws IOException, ServletException {
        //��ȡ�ļ���Դ
        Part part = request.getPart("fileName");
        //���ļ���Դ�л�ȡ�ļ���
        String fileName = part.getSubmittedFileName();
        //�������ļ���
        fileName = UUID.randomUUID() + fileName;
        //��װд���ַ
        File file = new File("D:/x_upload");
        if (!file.exists()){
            //�����ļ���
            file.mkdir();
        }

        String path = file+"/"+fileName;
        //���ļ�д�뵽ָ��λ��
        part.write(path);
        //�����ļ���
        return fileName;
    }
}
