package com.chinajey.sdk.wrappers;

/**
 * Created by wt on 2018/6/7.
 */

public class ImmersionBarFactory {
    private static ImmersionBarWrapper instance;

    public static ImmersionBarWrapper getInstance(){
//        if(instance == null){
//            synchronized (ImmersionBarFactory.class){
                instance = new ImmersionBarImp();
//            }
//        }
        return instance;
    }
}
