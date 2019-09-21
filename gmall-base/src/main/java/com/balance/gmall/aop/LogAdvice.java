package com.balance.gmall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author: yunzhang.du
 * @description:
 * @Date: 2019-06-25 20:13
 */
@Component
@Aspect
@Slf4j
public class LogAdvice {


    // service和controller层 配置切点 及要传的参数
    @Pointcut("execution(* com.balance.gmall.service..*(..)) || " +
            "execution(* com.balance.gmall.controller..*(..))")
    public void pointCut() {
    }

    // controller层 配置切点 及要传的参数
    @Pointcut("execution(* com.balance.gmall.controller..*(..))")
    public void pointCutController() {
    }

    // 方法开始执行时通知
    @Before("pointCutController()")
    public void beforeLog() {
        // 1.获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            log.info("|请求为空|");
        } else {
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String address = request.getRemoteAddr();
            String method = request.getMethod();
            Map<String, String[]> param = request.getParameterMap();
            String logInfo = "|接受请求|-|url|-[" + url + "]" +
                    "-|地址|-[" + address + "]" +
                    "-|方法|-[" + method + "]" +
                    "-|参数|-[" + param + "]";
            log.info(logInfo);
        }
    }
    /*
    // 方法执行完后通知
    @After("pointCut()")
    public void afterLog(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();
        log.info("|开始执行后置通知 日志记录|==> " + name);
    }
    // 执行成功后通知
    @AfterReturning("pointCut()")
    public void afterReturningLog(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();
        log.info("|方法成功执行后通知 日志记录|==> " + name);
    }
    // 抛出异常后通知
    @AfterThrowing("pointCut()")
    public void afterThrowingLog(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();
        log.info("|方法抛出异常后执行通知 日志记录|==> " + name);
    }*/


    /**
     * service层环绕通知
     *
     * @param: joinPoint 切点信息
     * @return: java.lang.Object
     * @throw:
     * @Date: 2019/9/20 - 10:40
     * @author: yunzhang.du
     */
    @Around("pointCut()")
    public Object ServiceAroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1.获取节点信息
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String param = getParam(args);
        Object result;
        // 2.执行完成日志打印
        beforeLog(className, methodName, param);
        try {
            // 有返回参数 则需返回值
            result = joinPoint.proceed();
            // 3.执行完成日志打印
            afterLog(result, className, methodName);
        } catch (Throwable t) {
            // 4.异常日志
            log.error("|出现异常|-|类|-[" + className + "]-|调用方法|-[" + methodName + "]-|异常信息|-[" + t.toString() + "]");
            throw t;
        } finally {
            log.info("|处理结束|-|类|-[" + className + "]-|调用方法|-[" + methodName + "]-|参数|-" + param);
        }
        return result;
    }

    /*
     * controller层环绕通知
     *
     * @param: joinPoint 切点信息
     * @return: java.lang.Object
     * @throw:
     * @Date: 2019/9/20 - 10:39
     * @author: yunzhang.du
     */
    /* @Around("pointCutController()")
    public Object ControllerAroundLog(ProceedingJoinPoint joinPoint) {
        // 1.获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null == attributes){
            log.info("|请求为空|");
        } else {
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String address = request.getRemoteAddr();
            String logInfo = "|接受请求|-|url|-[" + url + "]" +
                    "-|地址|-[" + address + "]";
            log.info(logInfo);
        }
        // 2.获取节点信息
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String param = getParam(args);
        Object result = null;
        // 3.执行完成日志打印
        beforeLog(className, methodName, param);
        try {
            // 有返回参数 则需返回值
            result = joinPoint.proceed();
            // 4.执行完成日志打印
            afterLog(result, className, methodName);
        } catch (Exception e) {
            // 5.异常日志
            log.error("|出现异常|-|类|-[" + className + "]-|调用方法|-[" + methodName + "]-|异常信息|-[" + e.toString() + "]");
            result = exceptionHandle.exceptionGet(e);
        } catch (Throwable t) {
            log.error("|出现错误|-|错误信息|-[" + t.toString() + "]");
        } finally {
            log.info("|处理结束|-|类|-[" + className + "]-|调用方法|-[" + methodName + "]-|参数|-" + param);
        }
        return result;
    }*/

    /**
     * 将入参对象转化为String字符串
     *
     * @param: args 入参对象
     * @return: java.lang.String
     * @throw:
     * @Date: 2019/9/20 - 10:33
     * @author: yunzhang.du
     */
    private String getParam(Object[] args) {
        StringJoiner param = new StringJoiner(", ", "[", "]");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                param.add(arg.toString());
            }
        }
        return param.toString();
    }

    /**
     * 执行前打印日志
     *
     * @param: result, 返回值
     * @param: className, 类名
     * @param: param, 入参
     * @return:
     * @throw:
     * @Date: 2019/9/20 - 10:34
     * @author: yunzhang.du
     */
    private void beforeLog(String className, String methodName, String param) {
        String logInfo = "|开始执行|-|类|-[" + className + "]" +
                "-|调用方法|-[" + methodName + "]" +
                "-|参数|-" +
                param;
        log.info(logInfo);
    }

    /**
     * 执行完成日志打印
     *
     * @param: result, 返回值
     * @param: className, 类名
     * @param: methodName, 方法名
     * @return:
     * @throw:
     * @Date: 2019/9/20 - 10:23
     * @author: yunzhang.du
     */
    private void afterLog(Object result, String className, String methodName) {
        StringBuilder logInfo = new StringBuilder();
        // 执行时间统计
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        logInfo.append("|执行用时|-|类|-[").append(className).append("]-|").append((end - start)).append(" 毫秒|");
        log.info(logInfo.toString());
        logInfo.setLength(0);
        // 执行成功日志
        logInfo.append("|执行成功|-|类|-[").append(className).append("]");
        logInfo.append("-|调用方法|-[").append(methodName).append("]");
        logInfo.append("-|返回值|-[").append(result.toString()).append("]");
        log.info(logInfo.toString());
    }
}
