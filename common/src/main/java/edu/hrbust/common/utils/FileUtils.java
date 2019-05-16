package edu.hrbust.common.utils;

import org.springframework.util.DigestUtils;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtils {
    public static String getMD5FromFile(String filePath){
        FileInputStream fis = null;
        String md5 = null;
        File f = new File(filePath);
        try {
            fis = new FileInputStream(f);
            md5 = DigestUtils.md5DigestAsHex(fis);
        }catch (FileNotFoundException e){
            return "FILE_READ_ERROR:"+e.getMessage();
        } catch (IOException e) {
            return "FILE_READ_ERROR:"+e.getMessage();
        }

        return md5;
    }

    public static String getCurProjectPath(){
        File file = new File("");
        String path = null;
        try{
            path = file.getCanonicalPath();
        }catch (Exception e){
        }
        return path;
    }

    public static void copy(String src, String des){
        File f = new File(src);
        assert (f.exists());

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try{
            inputChannel = new FileInputStream(src).getChannel();
            outputChannel = new FileOutputStream(des).getChannel();
            outputChannel.transferFrom(inputChannel, 0 ,inputChannel.size());
        }catch (Exception e){}
        finally {
            try {
                outputChannel.close();
                inputChannel.close();
            }catch (Exception e){}
        }
    }

    public static String getExtensionName(String path){
        return path.substring(path.lastIndexOf("."));
    }

    public static Boolean exists(String path, String name){
        String sourceImagePath = path+"\\"+name;
        File f = new File(sourceImagePath);
        return f.exists();
    }
}
