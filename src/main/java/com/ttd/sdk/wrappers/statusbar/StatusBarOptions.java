package com.chinajey.sdk.wrappers;

import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;

/**
 * Created by wt on 2018/6/7.
 */

public class StatusBarOptions {
    private final boolean isDarkFont;
    private final float statusAlpha;
    private final int statusBarColor;
    private final int statusBarViewId;

    public StatusBarOptions(Builder builder) {
        this.isDarkFont = builder.isDarkFont;
        this.statusAlpha = builder.statusAlpha;
        this.statusBarColor = builder.statusBarColor;
        this.statusBarViewId = builder.statusBarViewId;
    }

    public boolean isDarkFont() {
        return isDarkFont;
    }

    public float getStatusAlpha() {
        return statusAlpha;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    public int getStatusBarViewId() {
        return statusBarViewId;
    }

    public static class Builder {
        private boolean isDarkFont;
        private float statusAlpha;
        private int statusBarColor;
        private int statusBarViewId;

        public Builder setDarkFont(boolean darkFont) {
            isDarkFont = darkFont;
            return this;
        }

        public Builder setStatusAlpha(float statusAlpha) {
            this.statusAlpha = statusAlpha;
            return this;
        }

        public Builder setStatusBarColor(@ColorRes int statusBarColor) {
            this.statusBarColor = statusBarColor;
            return this;
        }

        public Builder setStatusBarViewId(@IdRes int statusBarViewId) {
            this.statusBarViewId = statusBarViewId;
            return this;
        }

        public StatusBarOptions build(){
            return  new StatusBarOptions(this);
        }
    }
}
