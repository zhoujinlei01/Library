package com.lay.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lay.common.util.ActivityUtils;
import com.lay.common.util.log.LogUtils;

/**
 * @author Lay
 * @date 2023/12/6
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static Toast toast;
    protected final String TAG = getClass().getSimpleName();
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.getInstance().addActivity(this);
        setContentView(getLayoutId());
        context = this;
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getInstance().removeActivity(this);
    }

    /**
     * 界面初始化操作
     */
    protected abstract void initView();

    /**
     * 数据初始化操作
     */
    protected abstract void initData();

    /**
     * 获取布局ID
     *
     * @return LayoutId 布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 显示提示信息
     *
     * @param msg
     */
    public void showToast(String msg) {
        try {
            if (toast == null) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /**
     * 跳转到指定Activity
     *
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 日志输出(DEBUG)
     *
     * @param msg
     */
    public void loggerInfo(String msg) {
        LogUtils.i(TAG, msg);
    }
}
