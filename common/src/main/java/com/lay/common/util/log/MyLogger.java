package com.lay.common.util.log;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

/**
 * @author Lay
 * @date 2023/12/6
 */
public class MyLogger implements Logger {
    private static final String TAG = "SunmiLog";
    //Log输出的最大长度
    private final int LOG_MAX_LENGTH = 2000;
    private int level = Log.VERBOSE;
    private boolean writeLog = false;

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean getSaveFlag() {
        return writeLog;
    }

    @Override
    public void setSaveFlag(boolean flag) {
        writeLog = flag;
    }

    @Override
    public void v(String TAG, String msg) {
        if (getLevel() <= Log.VERBOSE) {
            logWithLink(Log.VERBOSE, TAG, msg);
        }
    }

    @Override
    public void d(String TAG, String msg) {
        if (getLevel() <= Log.DEBUG) {
            logWithLink(Log.DEBUG, TAG, msg);
        }
    }

    @Override
    public void i(String TAG, String msg) {
        if (getLevel() <= Log.INFO) {
            logWithLink(Log.INFO, TAG, msg);
        }
    }

    @Override
    public void w(String TAG, String msg) {
        if (getLevel() <= Log.WARN) {
            logWithLink(Log.WARN, TAG, msg);
        }
    }

    @Override
    public void e(String TAG, String msg) {
        if (getLevel() <= Log.ERROR) {
            logWithLink(Log.ERROR, TAG, msg);
        }
    }

    @Override
    public void e(String tag, String msg, Throwable e) {
        if (getLevel() <= Log.ERROR) {
            logWithLink(Log.ERROR, tag, msg + " 【Throwable:" + getStackTrace(e) + "】");
        }
    }

    public String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            sw.close();
        } catch (IOException e1) {
            //ignore
        }
        return sw.toString();
    }


    /**
     * 循环打印log，解决msg过长不打印问题
     */
    private void LogWrapperLoop(int logPriority, String TAG, String msg) {
        int msgLength = msg.length();
        /**
         * adb  lgh=3
         *  ^
         *  |   index=1
         * print a
         */
        int index = 0;//输出字符的位置
        while (index < msgLength) {
            if (index + LOG_MAX_LENGTH > msgLength) {
                LogWrapper(logPriority, TAG, msg.substring(index, msgLength));
            } else {
                LogWrapper(logPriority, TAG, msg.substring(index, index + LOG_MAX_LENGTH));
            }
            index += LOG_MAX_LENGTH;
        }
    }

    private void LogWrapper(int logPriority, String TAG, String msg) {
        switch (logPriority) {
            case Log.VERBOSE:
                if (getSaveFlag()) {
//                    LogFileUtil.v(TAG, msg);
                }
                Log.v(TAG, msg);
                break;
            case Log.DEBUG:
                if (getSaveFlag()) {
//                    LogFileUtil.d(TAG, msg);
                }
                Log.d(TAG, msg);
                break;
            case Log.INFO:
                if (getSaveFlag()) {
//                    LogFileUtil.i(TAG, msg);
                }
                Log.i(TAG, msg);
                break;
            case Log.WARN:
                if (getSaveFlag()) {
//                    LogFileUtil.w(TAG, msg);

                }
                Log.w(TAG, msg);
                break;
            case Log.ERROR:
                if (getSaveFlag()) {
//                    LogFileUtil.e(TAG, msg);
                }
                Log.e(TAG, msg);
                break;
            default:
                break;
        }
    }

    /**
     * 带有链接的log打印
     */
    private void logWithLink(int logPriority, String TAG, String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 5;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        methodName = methodName.substring(0, 1).toUpperCase(Locale.getDefault()) + methodName.substring(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");
        stringBuilder.append(msg);
        String logStr = stringBuilder.toString();
        LogWrapperLoop(logPriority, TAG, logStr);
    }
}
