package org.intership.clubmate.config;


import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.intership.clubmate.entity.ErrorLog;
import org.intership.clubmate.entity.OperaLog;
import org.intership.clubmate.service.ErrorLogService;
import org.intership.clubmate.service.OperaLogService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class OperationLogAspect {
    @Resource
    private OperaLogService operaLogService;

    @Resource
    private ErrorLogService errorLogService;

    @Pointcut("@annotation(org.intership.clubmate.config.Log)")
    public void operaLogPointCut() {

    }

    @Pointcut("@annotation(org.intership.clubmate.config.Log)")
    public void errorLogPointCut() {
    }

    @AfterReturning(value = "operaLogPointCut()", returning = "keys")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取到的RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperaLog operaLog = new OperaLog();

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取织入点的方法
            Method method = signature.getMethod();
            //获取操作
            Log log = method.getAnnotation(Log.class);
            if (log != null) {
                String operaModule = log.operaModule();
                String operaType = log.operaType();
                operaLog.setOperaModule(operaModule);
                operaLog.setOperaType(operaType);
            }
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法
            String methodName = method.getName();
            methodName = className + "." + methodName;

            Map<String, String> rtnMap = converMap(request.getParameterMap());
            //将参数所在的数组转为json
            String params = JSON.toJSONString(rtnMap);

            //请求参数
            operaLog.setOperateRequest(params);
            //返回结果
            operaLog.setOperateResponse(JSON.toJSONString(keys));
            //操作员ip地址
            operaLog.setIp(request.getRemoteAddr());
            //请求URI
            operaLog.setOperaUrl(request.getRequestURI());
            //创建时间（操作时间）
            operaLog.setOperaTime(new Date());
            operaLog.setId(1);
            operaLogService.add(operaLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /*@AfterReturning(value = "errorLogPointCut()", returning = "a")
    public void saveErrorLog(JoinPoint joinPoint, Object a){
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ErrorLog errorLog = new ErrorLog();
        try {
            //在切面织入点通过反射机制获取织入点的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取织入点的方法
            Method method = signature.getMethod();
            //获取操作
            Log log = method.getAnnotation(Log.class);
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法
            String methodName = method.getName();
            methodName = className + "." + methodName;
            //请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            String params = JSON.toJSONString(rtnMap);
            //请求方法名
            errorLog.setOperaMethod(methodName);
            //异常信息
            errorLog.setErrMessage(JSON.toJSONString(a));
            //请求URI
            errorLog.setOperaUrl(request.getRequestURI());
            //操作员ip地址
            errorLog.setOperaIp(request.getRemoteAddr());
            //发生异常的时间
            errorLog.setOperaTime(new Date());
            errorLog.setUserId(1);

            errorLogService.save(errorLog);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }*/


    public Map<String, String> converMap(Map<String,String[]> paramMap){
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key,paramMap.get(key)[0]);
        }
        return rtnMap;
    }



}


