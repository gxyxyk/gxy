package com.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OneAspectAnno {
    @After("com.aop.OneAspectAnno.test()")
    public void after(){
        System.out.println("记录日志！");
    }
    @Pointcut("execution(* com.entity.Animal.eat(..))")
    private void test(){}
}
