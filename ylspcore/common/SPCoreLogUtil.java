package com.levending.ylspcore.common;

import android.util.Log;

public class SPCoreLogUtil {
    public static boolean isDebug = true;
    public static String tag = "ylspcore";

    public static void println(String str) {
        if (isDebug) {
            Log.v(tag, str);
        }
    }
}
