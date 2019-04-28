package com.swe.util;

public class Logger {
    /**
     * 记录日志的操作
     * 计划a让其在业务核心方法（切入点方法）执行之前执行
     */
    public void printLog(){
        System.out.println("Logger中的printLog方法开始记录日志");
    }
}
