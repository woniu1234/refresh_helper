package com.common.refreshviewlib.utils;

import android.util.Log;

/**
 * <pre>
 *     desc  : 日志工具类
 *     revise: 支持多种状态切换；支持上拉加载更多，下拉刷新；支持添加头部或底部view
 */
public final class RefreshLogUtils {

    private static final String TAG = "RefreshLogUtils";
    private static boolean mIsLog = true;

    public static void setLog(boolean isLog) {
        mIsLog = isLog;
    }

    public static void d(String message) {
        if (mIsLog) {
            Log.d(TAG, message);
        }
    }

    public static void i(String message) {
        if (mIsLog) {
            Log.i(TAG, message);
        }

    }

    public static void e(String message) {
        if (mIsLog) {
            Log.e(TAG, message);
        }
    }

    public static void e(String message, Throwable throwable) {
        if (mIsLog) {
            Log.e(TAG, message, throwable);
        }
    }

}
