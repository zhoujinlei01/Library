package com.lay.common.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lay
 * @date 2023/12/6
 */
public class ActivityUtils {
    private static ActivityUtils instance;
    private List<Activity> activityList = new ArrayList<>();

    public static synchronized ActivityUtils getInstance() {
        if (instance == null) {
            instance = new ActivityUtils();
        }
        return instance;
    }

    /**
     * 添加Activity到集合中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 从集合中移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }
}
