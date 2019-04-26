package com.swe.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模拟一个剧组
 */
public class Client {
    /**
     * 动态代理
     *  	作用：在不改变源码的基础上，对已有 方法增强。（它是AOP思想的实现技术）
     *      分类：
     *          基于接口的动态代理：
     *              要求：被代理类至少实现一个接口；
     *              提供者：JDK官方
     *              涉及的类：Proxy
     *              创建代理对象的方法：newProxyInstance(ClassLoader,Class[],InvocationHandler)
     *                  参数的含义：
     *                      ClassLoader：类加载器，和被代理对象使用相同的类加载器，一般都是固定写法；
     *                      Class[]：字节码数组，被代理类实现的接口，（要求代理对象和被代理对象具有相同的行为）；一般都是固定写法
     *                      InvocationHandler:他是一个接口，就是用于我们提供增强代码的，我们一般都是些一个该接口的实现类，实现类可以是匿名内部类；他的含义就是：如何实现代理，此处的代码只能是谁用谁提供。
     *                           策略模式：
     *                              使用要求：数据已经有了
     *                                        目的明确
     *                                         达成目标的过程就是策略；
     *                                         （在dbutils中的ResultSetHandler就是策略模式的具体应用）
     *            基于子类的动态代理：
     *                要求：被代理类不能是最终类，即被代理的类不能被final修饰
     *                提供者：第三方CGlib
     *                涉及的类：Enhancer
     *                创建代理对象的方法：create(Class,Callback);
     *                      参数的含义;
     *                          Class:被代理对象的字节码
     *                          Callback:如何代理。它和InvocatioonHandler的作用是一样的，它也是一个接口，我们一般使用该接口的子接口的MethodInterceptor
     *                              在使用时我们也是创建该接口的实现类，即匿名内部类。
     * @param args
     */
    public static void main(String[] args) {
        final Actor actor=new Actor();
        Actor cglibActor = (Actor) Enhancer.create(actor.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法，都会经过该方法(intercept)，它和基于接口动态代理的invoke方法是一模一样的。
             * 方法参数：
             *     Object proxy：? 代理对象的引用，不一定每次都会有；
             *     Method method：当前执行的方法；
             *     Object[] args：当前执行方法所需要的参数；
             *     (前面的三个和invoke方法的参数含义和作用都一样)
             *     MethodProxy methodProxy：当前执行方法的代理对象，一般不用；
             * @param proxy
             * @param method
             * @param args
             * @param methodProxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object rtValue = null;
                //1.取出执行方法中的参数：给多少钱
                float money = (float) args[0];
                //2.判断当前方法执行的是什么方法
                if ("basicAct".equals(method.getName())) {
                    //基本演出
                    if (money > 10000) {
                        //执行方法：开始表演
                        rtValue = method.invoke(actor, money);
                    }
                }
                if ("dangerAct".equals(method.getName())) {
                    //危险演出
                    if (money > 50000) {
                        rtValue = method.invoke(actor, money);
                    }
                }
                return rtValue;
            }
        });

        cglibActor.basicAct(20000);
        cglibActor.dangerAct(60000);
    }
}

