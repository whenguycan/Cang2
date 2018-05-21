package com.lepus.cang2;

/**
 * Created by wang on 2018/5/21.
 */

public class Counter {

    public String name;
    public int count;

    public static Counter newInstance(String name, int count){
        Counter c = new Counter();
        c.name = name;
        c.count = count;
        return c;
    }

}
