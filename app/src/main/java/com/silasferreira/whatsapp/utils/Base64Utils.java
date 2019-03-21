package com.silasferreira.whatsapp.utils;

import android.util.Base64;

public final class Base64Utils {

    public static String encode(String text){
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decode(String text){
        return new String(Base64.decode(text, Base64.DEFAULT));
    }
}
