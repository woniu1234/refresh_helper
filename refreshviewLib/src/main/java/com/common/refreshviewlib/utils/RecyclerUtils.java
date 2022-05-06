package com.common.refreshviewlib.utils;

import android.app.Application;
import android.content.Context;

/**
 * desc  : 工具类
 */
public final class RecyclerUtils {

    public static void checkContent(Context context) {
        if (context == null) {
            throw new NullPointerException("context is not null");
        }
        if (context instanceof Application) {
            throw new UnsupportedOperationException("context is not application");
        }
    }

}
