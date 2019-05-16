package edu.hrbust.common.enums;

import lombok.Getter;

@Getter
public enum DownloadStatusEnum {

    OK(200, "下载成功"),
    ERROR(1001, "下载失败"),
    FILE_HANDLE_ERROR(1002, "文件句柄关闭异常");

    DownloadStatusEnum(Integer code, String status){
        this.code = code;
        this.status = status;
    }

    private String status;
    private Integer code;
}
