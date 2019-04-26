package com.swe.proxy;

/**
 * 一个演员
 */
public class Actor implements IActor{


    /**
     * 简单的表演
     * @param money
     */
    @Override
    public void basicAct(float money){
        System.out.println("拿到钱，开始基本的表演:"+money);
    }

    /**
     * 危险表演
     * @param money
     */
    @Override
    public void dangerAct(float money){
        System.out.println("拿到钱，开始危险的表演:"+money);
    }

}
