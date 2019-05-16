package edu.hrbust.common;

import edu.hrbust.common.enums.ResultStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 重构Result支持状态码
 *
 * @author zhengweichao
 * @since 2018/12/17
 */
@Getter
public class ResultResponse<R> {

    private static final long serialVersionUID = 345794768389168018L;
    /**
     * 返回结果状态码
     */

    @Setter
    private Integer resultCode;

    @Setter
    private R data;//返回数据

    @Setter
    private String resultMsg = "执行失败!";//结果信息


    private boolean isSuccess = false;//成功标识

    /****************************** 私有化构造函数 ******************************/
    private ResultResponse(ResultStatusEnum resultStatusEnum) {
        this.setResultCode(resultStatusEnum.getCode());
        this.setResultMsg(resultStatusEnum.getText());
    }

    private ResultResponse(ResultStatusEnum resultStatusEnum, R data) {
        this.setResultCode(resultStatusEnum.getCode());
        this.setResultMsg(resultStatusEnum.getText());
        this.setData(data);
    }

    private ResultResponse() {
    }

    /****************************** 执行成功 ******************************/

    public static <R> ResultResponse<R> success() {
        ResultResponse<R> res = new ResultResponse<R>(ResultStatusEnum.SUCCESS);
        res.setIsSuccess(true);
        return res;
    }

    public static <R> ResultResponse<R> success(R data) {
        ResultResponse<R> res = new ResultResponse<R>(ResultStatusEnum.SUCCESS, data);
        res.setIsSuccess(true);
        return res;
    }

    public static <R> ResultResponse<R> success(ResultStatusEnum resultStatusEnum) {
        ResultResponse<R> res = new ResultResponse<R>(resultStatusEnum);
        res.setIsSuccess(true);
        return res;
    }

    public static <R> ResultResponse<R> success(ResultStatusEnum resultStatusEnum, R data) {
        ResultResponse<R> res = new ResultResponse<R>(resultStatusEnum, data);
        res.setIsSuccess(true);
        return res;
    }

    /****************************** 执行失败 ******************************/

    public static <R> ResultResponse<R> fail() {
        ResultResponse<R> res = new ResultResponse<R>(ResultStatusEnum.FAILURE);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> ResultResponse<R> fail(R data) {
        ResultResponse<R> res = new ResultResponse<R>(ResultStatusEnum.FAILURE, data);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> ResultResponse<R> fail(ResultStatusEnum resultStatusEnum) {
        ResultResponse<R> res = new ResultResponse<R>(resultStatusEnum);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> ResultResponse<R> fail(ResultStatusEnum resultStatusEnum, R data) {
        ResultResponse<R> res = new ResultResponse<R>(resultStatusEnum, data);
        res.setIsSuccess(false);
        return res;
    }

    public static <R> ResultResponse<R> fail(ResultStatusEnum resultStatusEnum, Integer resultCode) {
        ResultResponse<R> res = new ResultResponse<R>(resultStatusEnum);
        res.setIsSuccess(false);
        res.setResultCode(resultCode);
        return res;
    }

    public static <R> ResultResponse<R> fail(ResultStatusEnum resultStatusEnum, String resultMsg) {
        ResultResponse<R> res = new ResultResponse<R>(resultStatusEnum);
        res.setIsSuccess(false);
        res.setResultMsg(resultMsg);
        return res;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setResultStatusEnum(ResultStatusEnum resultStatusEnum) {
        this.setResultCode(resultStatusEnum.getCode());
        this.setResultMsg(resultStatusEnum.getText());
    }

}
