package com.nblog.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by syang on 2019/8/12.
 */
public class Env {
    @Autowired
    private static Environment environment;

    public static final String ImgUploadPath = environment.getProperty("spring.datasource.password");
}
