package com.swe.cglib;

import com.swe.proxy.IActor;

/**
 * 一个演员
 */
public class Actor {


    /**
     * 简单的表演
     * @param money
     */
    public void basicAct(float money){
        System.out.println("cglib 拿到钱，开始基本的表演:"+money);
    }

    /**
     * 危险表演
     * @param money
     */
    public void dangerAct(float money){
        System.out.println("cglib 拿到钱，开始危险的表演:"+money);
    }

}
