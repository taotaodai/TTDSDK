package com.ttd.sdk.helper.okhttp;

import android.content.Context;
import android.content.SharedPreferences;

import com.ttd.sdk.utils.SpUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wt on 2018/7/13.
 */

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        String cookie = SpUtils.getString(context,"cookie","");
        Observable.just(cookie)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //添加cookie
                        builder.addHeader("Cookie", s);
                    }
                });
        return chain.proceed(builder.build());
    }
}

