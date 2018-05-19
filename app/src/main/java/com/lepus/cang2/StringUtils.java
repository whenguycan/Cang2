package com.lepus.cang2;

/**
 * Created by wang on 2018/5/19.
 */

public class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
