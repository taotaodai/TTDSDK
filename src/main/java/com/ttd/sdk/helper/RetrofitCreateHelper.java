package com.ttd.sdk.helper;


import com.ttd.sdk.helper.okhttp.AddCookiesInterceptor;
import com.ttd.sdk.helper.okhttp.CacheInterceptor;
import com.ttd.sdk.helper.okhttp.HttpCache;
import com.ttd.sdk.helper.okhttp.ReceivedCookiesInterceptor;
import com.ttd.sdk.helper.okhttp.TrustManager;
import com.ttd.sdk.utils.AppUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wt on 2017/9/7.
 * <p>
 */

public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static RetrofitCreateHelper instance = new RetrofitCreateHelper();
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private static ReceivedCookiesInterceptor receivedCookiesInterceptor = new ReceivedCookiesInterceptor(AppUtils.getContext());
    private static OkHttpClient okHttpClient;

    public static RetrofitCreateHelper init(boolean saveCookie) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //SSL证书
                .sslSocketFactory(TrustManager.getUnsafeOkHttpClient())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                //打印日志
                .addInterceptor(interceptor)
                //设置Cache拦截器
//                .addNetworkInterceptor(cacheInterceptor)
//                .addInterceptor(cacheInterceptor)
                .addInterceptor(new AddCookiesInterceptor(AppUtils.getContext()))
//                .cache(HttpCache.getCache())
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true);

        if(saveCookie){
            builder.addInterceptor(receivedCookiesInterceptor);
        }
        okHttpClient = builder.build();
        return instance;
    }

    public <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }


}

