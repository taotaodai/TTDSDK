package com.ttd.sdk.helper.okhttp;

import android.content.Context;

import com.ttd.sdk.utils.SpUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wt on 2018/7/13.
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.fromIterable(originalResponse.headers("Set-Cookie"))
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Exception {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String cookie) throws Exception {
                            cookieBuffer.append(cookie).append(";");
                        }
                    });
            SpUtils.putString(context,"cookie",cookieBuffer.toString());
        }

        return originalResponse;
    }
}