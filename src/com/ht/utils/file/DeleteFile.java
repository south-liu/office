package com.ht.utils.file;

import java.io.File;

public class DeleteFile {
    public static boolean deleteFile(String filePath){
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除"+filePath+"成功");
                return true;
            } else {
                System.out.println("删除"+filePath+"失败");
                return false;
            }
        } else {
            System.out.println("文件"+filePath+"不存在");
            return false;
        }
    }
}
