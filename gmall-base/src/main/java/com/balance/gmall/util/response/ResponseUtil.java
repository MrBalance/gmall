package com.balance.gmall.util.response;

import com.balance.gmall.response.ExceptionEnum;
import com.balance.gmall.response.Response;

/**
 * 操作返回对象的工具类
 * @author: yunzhang.du
 * @date: 2019年09月20日
 * @version: v1.0
 * @since: JDK 1.8
 */
public class ResponseUtil {

    /**
     * 返回成功，传入返回体具体返回值
     * @param: data 返回值对象
     * @return: com.balance.gmall.response.Response<T>
     * @throw:
     * @Date: 2019/9/20 - 11:21
     * @author: yunzhang.du
     */
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setMsg(ExceptionEnum.SUCCESS.getMessage());
        response.setStatus(ExceptionEnum.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    /**
     * 提供给部分不需要出參的接口
     * @param:
     * @return: com.balance.gmall.response.Response
     * @throw:
     * @Date: 2019/9/20 - 11:22
     * @author: yunzhang.du
     */
    public static Response success(){
        return success(null);
    }

    /**
     * 自定义错误信息
     * @param: code, 错误码
     * @param: msg,  错误信息
     * @return: com.balance.gmall.response.Response
     * @throw:
     * @Date: 2019/9/20 - 11:22
     * @author: yunzhang.du
     */
    public static  Response error(Integer code,String msg) {
        Response response = new Response();
        response.setMsg(msg);
        response.setStatus(code);
        return response;
    }

    /**
     * 返回异常信息，在已知的范围内
     * @param: exceptionEnum 已知异常信息枚举对象
     * @return: com.balance.gmall.response.Response
     * @throw:
     * @Date: 2019/9/20 - 11:23
     * @author: yunzhang.du
     *
     */
    public static  Response error(ExceptionEnum exceptionEnum) {
        Response response = new Response();
        response.setMsg(exceptionEnum.getMessage());
        response.setStatus(exceptionEnum.getCode());
        return response;
    }
}
