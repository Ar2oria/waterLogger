package edu.hrbust.common.exception;

public class BizException extends Exception {
    public BizException(String fileName){
        super("下载文件异常，文件名："+fileName);
    }
}
