package com.lay.common.util.log;

import static android.util.Log.ASSERT;
import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Lay
 * @date 2023/12/6
 */
public interface Logger {
    /**
     * 获取日志级别
     *
     * @return
     */
    int getLevel();

    /**
     * 设置日志级别
     *
     * @param level
     */
    void setLevel(@Level int level);

    /**
     * 获取是否保存日志
     *
     * @return
     */
    boolean getSaveFlag();

    /**
     * 设置是否保存日志
     *
     * @param flag
     */
    void setSaveFlag(boolean flag);

    /**
     * @param tag     the tag
     * @param message the message
     */
    void v(String tag, String message);

    /**
     * @param tag     the tag
     * @param message the message
     */
    void d(String tag, String message);

    /**
     * @param tag     the tag
     * @param message the message
     */
    void i(String tag, String message);

    /**
     * @param tag     the tag
     * @param message the message
     */
    void w(String tag, String message);

    /**
     * @param tag     the tag
     * @param message the message
     */
    void e(String tag, String message);

    /**
     * @param tag     the tag
     * @param message the message
     * @param e       the e
     */
    void e(String tag, String message, Throwable e);

    @IntDef({ASSERT, ERROR, WARN, INFO, DEBUG, VERBOSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }
}
