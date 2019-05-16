package edu.hrbust.waterLogger.advice;


import edu.hrbust.common.ResultResponse;
import edu.hrbust.common.exception.BizException;
import edu.hrbust.common.utils.UtilResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultResponse errorHandler(Exception e) {
        if (e instanceof BizException) {
            log.info("BizException={}", e);
            return UtilResponse.getResponseFail(e.getMessage());
        }  else {
            log.error("未捕获异常=====>{}", e);
            return UtilResponse.getResponseFail("未知异常");
        }
    }
}