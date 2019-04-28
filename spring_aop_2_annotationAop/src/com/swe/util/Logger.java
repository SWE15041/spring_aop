package com.swe.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("logger")
//配置切面
@Aspect
public class Logger {

    @Pointcut("execution( * com.swe.service.impl.*.*(..))")
    private void pt1(){}

    /**
     *
     * 前置通知
     */
    @Before("pt1()")
    public void beforePrintLog(){
        System.out.println("前置---Logger中的beforePrintLog方法开始记录日志");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt1()")
    public void afterReturnPrintLog(){
        System.out.println("后置---Logger中的afterReturnPrintLog方法开始记录日志");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public void afterThrowsPrintLog(){
        System.out.println("异常---Logger中的afterThrowsPrintLog方法开始记录日志");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知，切入点方法没有执行，二环绕通知里的代码执行了。
     * 分析：
     *      由动态代理可知，环绕通知指的是invoke方法，并且里面有明确的切入点方法调用，而我们现在的环绕通知没有明确切入点方法调用。
     * 解决：
     *      Spring为我们提供了一个接口，ProceedingJoinPoint ,该接口可以作为环绕通知的方法来使用。
     *      在程序运行时，Spring框架会为我们提供该接口的实现类，供我们使用。
     *      该接口中有一个方法proceed().他的作用就等同于method.invoke方法，也就是明确调用业务层核心方法（切入点方法）；
     * 环绕通知：
     *      它是Spring框架为我们提供的一种可以在代码中手动控制通知方法什么时候执行的方式。
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint){
        Object rtValue=null;
        try {
            System.out.println("Logger中的aroundPrintLog方法开始记录日志---------前置");
            rtValue = proceedingJoinPoint.proceed();
            System.out.println("Logger中的aroundPrintLog方法开始记录日志---------后置");
        } catch (Throwable e) {
            System.out.println("Logger中的aroundPrintLog方法开始记录日志---------异常");
            e.printStackTrace();
        }finally {
            System.out.println("Logger中的aroundPrintLog方法开始记录日志---------最终");
        }
        return rtValue;
    }

    /**
     * 最终通知
     */
    @After("pt1()")
    public void afterPrintLog(){
        System.out.println("最终---Logger中的afterPrintLog方法开始记录日志");
    }



}
