package com.itheima.ssm.contoller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService iSysLogService;

    //前置通知 主要获取开始时间，执行的类是哪个，执行的方法是哪个
    @Before("execution(* com.itheima.ssm.contoller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        //获取具体执行的方法的method对象
        if (args == null || args.length ==0){
            method = clazz.getMethod(methodName);//只能获取无参的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    @AfterReturning("execution(* com.itheima.ssm.contoller.*.*(..))")
    public void doAfterReturning(JoinPoint jp) throws Exception {
        //获取操作时间
        long time = new Date().getTime()-visitTime.getTime();
        //获取url
        String url="";
        //获取urL
        if (clazz != null && method != null && clazz != LogAop.class){
            RequestMapping clazzAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] classValue = clazzAnnotation.value();
                //获取方法上的requestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] value = methodAnnotation.value();
                    url = classValue[0]+value[0];

                    //获取ip
                    String remoteAddr = request.getRemoteAddr();

                    //获取操作者名字
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    User principal = (User) securityContext.getAuthentication().getPrincipal();
                    String username = principal.getUsername();

                    //封装到syslog 对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(remoteAddr);
                    sysLog.setMethod("[类名] "+clazz.getName() + "[方法名 ]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    iSysLogService.save(sysLog);
                }
            }
        }


    }

}
