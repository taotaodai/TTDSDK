package com.ttd.sdk.wrappers.statusbar;

import android.app.Activity;

/**
 * Created by wt on 2018/6/7.
 */

public interface ImmersionBarWrapper {
    void initImmersionBar(Activity activity, StatusBarOptions options);

    void destroy();
}
