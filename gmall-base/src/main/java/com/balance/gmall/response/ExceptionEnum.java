package com.balance.gmall.response;

/**
 * @description: 错误信息
 * @author: yunzhang.du
 * @date: 2019年09月20日
 * @version: v1.0
 * @since: JDK 1.8
 */
public enum ExceptionEnum {

    SUCCESS(0,"执行成功"),
    OPERATION_FAILED(-1,"操作失败"),
    USER_NOT_FIND(1001,"用户不存在"),
    ;

    private Integer code;

    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
