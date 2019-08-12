package com.nblog.utils;

import java.util.UUID;

/**
 * Created by syang on 2019/8/12.
 */
public class BaseUtils {

    public static final String UUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUID());
    }
}
