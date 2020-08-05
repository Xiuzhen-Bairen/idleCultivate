package com.idlewow.rms.config;

import com.alibaba.fastjson.JSON;
import com.idlewow.admin.model.SysAdmin;
import com.idlewow.rms.controller.BaseController;
import com.idlewow.rms.vo.RequestLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

@Aspect
@Component
public class LogAspect {

    private final static Logger logger = LogManager.getLogger(LogAspect.class);

    // ..表示包及子包 该方法代表controller层的所有方法
    @Pointcut("execution(public * com.idlewow.rms.controller..*.*(..))")
    public void commonPoint() {
    }

    @Pointcut("@annotation(com.idlewow.rms.support.annotation.LogResult)")
    public void returnPoint() {
    }


    @Before("commonPoint()")
    public void before(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        String username = "anyone";
        if (session != null && session.getAttribute(BaseController.LoginUserKey) != null) {
            username = ((SysAdmin) session.getAttribute(BaseController.LoginUserKey)).getUsername();
        }

        String trackId = username + "_" + System.currentTimeMillis() + "_" + new Random().nextInt(100);
        request.setAttribute("ct_begin", new Date().getTime());
        request.setAttribute("ct_id", trackId);
        RequestLog requestLog = new RequestLog();
        requestLog.setUrl(request.getRequestURI());
        requestLog.setType(request.getMethod());
        requestLog.setIp(request.getRemoteAddr());
        requestLog.setMethod(joinPoint.getSignature().toShortString());
        requestLog.setArgs(joinPoint.getArgs());
        logger.info("[" + trackId + "]请求开始：" + requestLog.toString());
    }

    @After("commonPoint()")
    public void after(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String trackId = request.getAttribute("ct_id").toString();
        long totalTime = new Date().getTime() - (long) request.getAttribute("ct_begin");
        logger.info("[" + trackId + "]请求耗时：" + totalTime + "ms");
    }

    @AfterReturning(returning = "result", pointcut = "commonPoint()")
    public void afterReturning(JoinPoint joinPoint, Object result) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String trackId = request.getAttribute("ct_id").toString();
        logger.info("[" + trackId + "]请求结果：" + JSON.toJSONString(result));
    }

    @AfterThrowing(value = "commonPoint()", throwing = "t")
    public void afterThrowing(JoinPoint joinPoint, Throwable t) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String trackId = request.getAttribute("ct_id").toString();
        logger.error("[" + trackId + "]系统异常：" + t.getMessage(), t);
    }
}
