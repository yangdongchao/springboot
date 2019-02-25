package com.example.demo.utils;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/12 19:31
 * @Version 1.0
 **/
import java.io.File;
import java.io.FileOutputStream;

/**
 * .
 */
public class FileUtil{

    /**
     * 获取文件类型
     * @param fileName
     * @return
     */
    public static String getFileExtName(String fileName) {
        if (fileName!=null ) {
            int i = fileName.lastIndexOf('.');
            if (i>-1) {
                return fileName.substring(i+1).toLowerCase();
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
    //文件上传工具类服务方法

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
