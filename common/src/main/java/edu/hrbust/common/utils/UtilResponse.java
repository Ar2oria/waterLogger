package edu.hrbust.common.utils;


import edu.hrbust.common.ResultResponse;

public class UtilResponse {
    public static ResultResponse getResponseFail(String message){
        return ResultResponse.fail(message);
    }
}
