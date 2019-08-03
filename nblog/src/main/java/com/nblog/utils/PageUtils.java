package com.nblog.utils;

import com.nblog.base.PAGE;
import org.springframework.util.StringUtils;

/**
 * Created by syang on 2019/8/3.
 */
public class PageUtils {
    public static int getStart(Integer page, Integer count){
        if(StringUtils.isEmpty(page)){
            page = 1;
        }
        if(StringUtils.isEmpty(count)){
            count = PAGE.COUNT.getIndex();
        }
        return (page -1)*count;
    }
}
