package com.lay.common.util.log;

/**
 * @author Lay
 * @date 2023/12/6
 */
public class LogUtils {
    private static volatile Logger logger;
    private static volatile boolean logEnable;

    public static boolean isLogEnable() {
        return logEnable;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void init(Logger l, boolean enable) {
        logger = l;
        logEnable = enable;
    }

    public static void init(Logger l, boolean enable, @Logger.Level int level, boolean flag) {
        logger = l;
        logEnable = enable;
        logger.setLevel(level);
        logger.setSaveFlag(flag);
    }

    public static void setLevel(@Logger.Level int level) {
        if (logger != null) {
            logger.setLevel(level);
        }
    }

    public static void setSaveFlag(boolean flag) {
        if (logger != null) {
            logger.setSaveFlag(flag);
        }
    }

    public static void v(String tag, String message) {
        if (logEnable && logger != null) {
            logger.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (logEnable && logger != null) {
            logger.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (logEnable && logger != null) {
            logger.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (logEnable && logger != null) {
            logger.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (logEnable && logger != null) {
            logger.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable e) {
        if (logEnable && logger != null) {
            logger.e(tag, message, e);
        }
    }
}
