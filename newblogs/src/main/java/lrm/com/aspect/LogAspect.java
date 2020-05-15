package lrm.com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* lrm.com.web.*.*(..))")
    public void log(){}
    @Before("log()")
    public void doBefore(JoinPoint joinpoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI().toString();
        String ip = request.getRemoteAddr();
        String classMethed = joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName();
        Object args = joinpoint.getArgs();
        RequstLog requestLog = new RequstLog(url,ip,classMethed,args);
        logger.info("Result:{}",requestLog);
    }
    @After("log()")
    public void doAfter(JoinPoint joinpoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI().toString();
        String ip = request.getRemoteAddr();
        String classMethed = joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName();
        Object args = joinpoint.getArgs();
        RequstLog requestLog = new RequstLog(url,ip,classMethed,args);
        logger.info("Result:{}",requestLog);
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result){
        logger.info("Result:{}",result);
    }
    private class RequstLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object args;

        public RequstLog(String url, String ip, String classMethod, Object args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + args +
                    '}';
        }
    }
}
