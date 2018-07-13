package com.chinajey.sdk.wrappers;

import android.app.Activity;

import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by lml on 2018/6/7.
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
        mImmersionBar.init();
    }

    @Override
    public void destroy() {
        if(mImmersionBar != null){
            mImmersionBar.destroy();
        }
    }
}
