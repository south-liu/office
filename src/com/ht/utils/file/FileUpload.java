package com.ht.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUpload {
    /**
     * 普通上传文件
     * @param path
     * @param file
     */
    public static void upload(String path, MultipartFile file){
        String filename = file.getOriginalFilename();
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        try {
            file.transferTo(new File(dirFile,filename));
            System.out.println("本地路径---->>" + path+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件并重命名
     * @param path
     * @param file
     */
    public static String uploadAndRename(String path, MultipartFile file){
        String pickName = UUID.randomUUID().toString();//别名
        String filename = file.getOriginalFilename();//文件名
        String extName = filename.substring(filename.lastIndexOf("."));//截取文件后缀名
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String newFileName = pickName+extName;
        try {
            file.transferTo(new File(dirFile,newFileName));
            System.out.println("本地路径---->>" + path+newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

}
