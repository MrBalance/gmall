package com.balance.gmall.exception;

import com.balance.gmall.response.ExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月20日
 * @version: v1.0
 * @since: JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DescribeException extends RuntimeException{

    /** 错误码*/
    private Integer code;

    /** 错误信息*/
    private String message;

    public DescribeException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public DescribeException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

}
