package com.example.instagram.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public final class Base64Utils {

    public static String encode(String text){
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decode(String text){
        return new String(Base64.decode(text, Base64.DEFAULT));
    }

    public static String encodeByte(byte[] array){
        return Base64.encodeToString(array, Base64.DEFAULT);
    }

    public static byte[] decodeBase64ToByte(String base64){
        return Base64.decode(base64, Base64.DEFAULT);
    }

    public static Bitmap decodebase64InBitmap(byte[] array){
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }
}
