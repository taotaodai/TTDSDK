package com.ttd.sdk.wrappers.statusbar;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;

/**
 * Created by wt on 2018/6/7.
 */

public class StatusBarOptions {
    private final boolean isDarkFont;
    private final float statusAlpha;
    private final int statusBarColor;
    private final int statusBarViewId;
    private final int titleBarId;

    public StatusBarOptions(Builder builder) {
        this.isDarkFont = builder.isDarkFont;
        this.statusAlpha = builder.statusAlpha;
        this.statusBarColor = builder.statusBarColor;
        this.statusBarViewId = builder.statusBarViewId;
        this.titleBarId = builder.titleBarId;
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

    public int getTitleBarId() {
        return titleBarId;
    }

    public int getStatusBarViewId() {
        return statusBarViewId;
    }

    public static class Builder {
        private boolean isDarkFont;
        private float statusAlpha;
        private int statusBarColor;
        private int statusBarViewId;
        private int titleBarId;

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

        public Builder setTitleBarId(int titleBarId) {
            this.titleBarId = titleBarId;
            return this;
        }

        public StatusBarOptions build(){
            return  new StatusBarOptions(this);
        }
    }
}
