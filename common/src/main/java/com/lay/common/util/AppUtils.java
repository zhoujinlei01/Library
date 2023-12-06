package com.lay.common.util;

import android.content.Context;
import android.util.Log;

import com.lay.common.util.log.LogUtils;
import com.lay.common.util.log.MyLogger;

/**
 * @author sm3243
 * @date 2023/12/6
 */
public class AppUtils {
    private static Context mContext;

    /**
     * 初始化
     */
    public static void init(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("init parameter is null");
        }
        mContext = context;
        LogUtils.init(new MyLogger(), true, Log.VERBOSE, true);
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        if (mContext != null) {
            return mContext;
        }
        throw new IllegalStateException("AppUtils is not init");
    }
}
