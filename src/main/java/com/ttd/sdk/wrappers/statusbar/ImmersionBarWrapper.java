package com.chinajey.sdk.wrappers;

import android.app.Activity;

/**
 * Created by wt on 2018/6/7.
 */

public interface ImmersionBarWrapper {
    void initImmersionBar(Activity activity,StatusBarOptions options);

    void destroy();
}
