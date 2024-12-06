package org.intership.clubmate.config;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.intership.clubmate.entity.BaseLog;
import org.intership.clubmate.entity.ErrorLog;
import org.intership.clubmate.entity.OperaLog;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ErrorLogService;
import org.intership.clubmate.service.OperaLogService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.Date;


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


    @AfterReturning(value = "operaLogPointCut()", returning = "keys")
    public void saveOperationLog(JoinPoint joinPoint, ResponseResult keys) {
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取到的RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);


        BaseLog baseLog =getLog(keys.getCode()) ;

        try {

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取织入点的方法
            Method method = signature.getMethod();
            //获取操作
            Log log = method.getAnnotation(Log.class);
            if (log != null) {
                String operaModule = log.operaModule();
                String operaType = log.operaType();
                baseLog.setOperaModule(operaModule);
                baseLog.setOperaType(operaType);
            }
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();



            // 获取方法的参数
            Object[] args = joinPoint.getArgs();
            ObjectMapper objectMapper = new ObjectMapper();
            String params = "";
            try {
                params =  objectMapper.writeValueAsString(args);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            baseLog.setId(null);
            //请求参数
            baseLog.setOperaRequest(params);
            //返回结果

            //操作员ip地址
            baseLog.setIp(request.getRemoteAddr());
            //请求URI
            baseLog.setOperaUrl(request.getRequestURI());
            //创建时间（操作时间）
            baseLog.setOperaTime(new Date());
            baseLog.setUserId(1);
            if(keys.getCode()==200){
                OperaLog operaLog =(OperaLog) baseLog;
                operaLog.setOperaResponse(JSON.toJSONString(keys));
                operaLogService.add(operaLog);
            }
            else {
                ErrorLog errorLog =(ErrorLog) baseLog;
                errorLog.setErrCode(keys.getCode());
                errorLog.setErrMessage(keys.getMsg());
                errorLogService.add(errorLog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public BaseLog getLog(int code){
        if(code==200) {
           return new OperaLog();}
        else {
            return new ErrorLog();
        }
    }

}


