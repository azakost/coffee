package com.levending.ylcoffeelib.common;

import android.util.Log;

public class CoffeeLibLog {
    public static boolean isDebug = true;
    public static String tag = "coffeelib";

    public static void println(String str) {
        if (isDebug) {
            Log.v(tag, str);
        }
    }
}
