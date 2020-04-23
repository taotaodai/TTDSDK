package com.ttd.sdk.wrappers.statusbar;

import android.app.Activity;

import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by wt on 2018/6/7.
 */

public class ImmersionBarImp implements ImmersionBarWrapper{
    private ImmersionBar mImmersionBar;
    @Override
    public void initImmersionBar(Activity activity, StatusBarOptions options) {
        mImmersionBar = ImmersionBar.with(activity);

        mImmersionBar.statusBarDarkFont(options.isDarkFont(), options.getStatusAlpha());
        if(options.getStatusBarColor() != 0){
            mImmersionBar.statusBarColor(options.getStatusBarColor());
        }
        if(options.getStatusBarViewId() != 0){
            mImmersionBar.statusBarView(options.getStatusBarViewId());
        }
        if(options.getTitleBarId() != 0){
            mImmersionBar.titleBar(options.getTitleBarId());
        }
        mImmersionBar.init();
    }

    @Override
    public void destroy() {
        if(mImmersionBar != null){
            mImmersionBar.destroy();
        }
    }
}
