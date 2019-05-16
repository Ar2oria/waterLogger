package edu.hrbust.common.enums;


import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * Api返回状态码
 *
 * @author zhengweichao
 * @since 2018/12/17
 */

@lombok.Getter
@AllArgsConstructor
public enum ResultStatusEnum implements EnumTrait {

    /****************************** (100+)数据库异常 ******************************/
    DB_ERROR_INSERT(1001, "数据更新失败"),
    DB_ERROR_SELECT(1002, "数据查询失败"),
    DB_ERROR_DELETE(1003, "数据删除失败"),
    DB_ERROR_UPDATE(1004, "数据更新失败"),
    DB_ERROR_TX(1005, "数据库事务异常"),
    DB_ERROR_MULTI_RECORD(1006, "查询到多条记录"),
    DB_ERROR_UPDATE_HOPEFUL_VERSION(1007, "多次更新数据"),

    /****************************** (200+)执行成功 ******************************/
    SUCCESS(200, "执行成功"),
    SUCCESS_ACCEPTED(2002, "数据正在被处理"),
    WORK_TYPE_BE_RELATED(2003, "工作类型已经被使用,无法删除"),
    MATTER_TYPE_BE_RELATED(2004, "项目类型已经被使用,无法删除"),

    /****************************** (300+)请求资源异常 ******************************/
    RESOURCE_ERROR(300, "请求资源异常"),

    /****************************** (400+)请求错误，可能是权限问题, 参数校验 ******************************/
    REQUEST_ERROR(400, "请求异常"),
    REQUEST_ERROR_AUTHENTICATION(4001, "权限不合法"),
    REQUEST_ERROR_PARAM_ILLEGAL(4002, "参数不合法"),
    REQUEST_ERROR_PARAM_CHECK(4003, "参数校验失败"),
    REQUEST_ERROR_NOT_ALLOW_OPTION(4004, "不允许的操作"),

    /****************************** (500+)服务端异常 ******************************/
    FAILURE(500, "执行失败"),
    FAILURE_SYSTEM(5001, "系统异常"),
    FAILURE_RPC(5002, "系统异常"),
    FAILURE_PUBLIC_PERMISSION(5003, "请求公共组权限服务异常"),

    /****************************** (600+)业务异常 ******************************/
    BIZ_ERROR(600, "业务异常"),
    ;
    public static final ResultStatusEnum DEFAULT = null;
    private static final Map<Integer, ResultStatusEnum> CODE_ENUM_MAP =
            Maps.uniqueIndex(Arrays.asList(values()), ResultStatusEnum::getCode);
    private final int code;
    private final String text;

    public static ResultStatusEnum codeOf(int code) {
        return CODE_ENUM_MAP.get(code);
    }

    public boolean isSuccess() {
        return StringUtils.startsWithIgnoreCase(String.valueOf(code), "2");
    }
}