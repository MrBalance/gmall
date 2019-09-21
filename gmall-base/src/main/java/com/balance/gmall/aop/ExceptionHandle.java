package com.balance.gmall.aop;

import com.balance.gmall.exception.DescribeException;
import com.balance.gmall.response.ExceptionEnum;
import com.balance.gmall.response.Response;
import com.balance.gmall.util.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月20日
 * @version: v1.0
 * @since: JDK 1.8
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionGet(Exception e){
        if(e instanceof DescribeException){
            DescribeException MyException = (DescribeException) e;
            return ResponseUtil.error(MyException.getCode(),MyException.getMessage());
        }
        return ResponseUtil.error(ExceptionEnum.OPERATION_FAILED);
    }
}
