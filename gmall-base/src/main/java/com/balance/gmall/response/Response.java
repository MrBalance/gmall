package com.balance.gmall.response;

import lombok.Data;

/**
 * 返回对象
 *
 * 返回报文的特征:
 *  1. 成功标示：可以用boolean型作为标示位。
 *  2. 错误代码：一般用整型作为标示位，罗列的越详细，前端的容错也就能做的更细致。
 *  3. 错误信息：使用String作为错误信息的描述，留给前端是否展示给用户或者进入其他错误流程的使用。
 *  4. 结果集：在无错误信息的情况下所得到的正确数据信息。一般是个Map，前端根据Key取值。
 *
 * @author: yunzhang.du
 * @date: 2019年09月20日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Data
public class Response<T> {

    /**
     * error_code 状态值：0 极为成功，其他数值代表失败
     */
    private T data;
    /**
     * error_msg 错误信息，若status为0时，为success
     */
    private Integer status;
    /**
     * content 返回体报文的出参，使用泛型兼容不同的类型
     */
    private String msg;

}
