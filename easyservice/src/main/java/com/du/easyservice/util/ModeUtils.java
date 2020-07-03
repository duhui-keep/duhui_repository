package com.du.easyservice.util;

public class ModeUtils {

    private static ModeUtils modeUtils = null;
    public static ModeUtils getInstant(){

        if (modeUtils == null){

            return new ModeUtils();
        }
        return modeUtils;

    }

}
