package com.swe.proxy;

/**
 * 一个经济公司
 *
  */
public interface IActor {
    public void basicAct(float money);

    /**
     * 危险表演
     * @param money
     */
    public void dangerAct(float money);
}
