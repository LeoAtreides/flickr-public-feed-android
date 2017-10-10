package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit;

import android.support.annotation.NonNull;

import com.developer.davidtc.flickrpublicfeedandroid.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Common class for retrofit service generation.
 * <p>
 * Created by david on 21/02/17.
 */

public final class RetrofitServiceGenerator {

    private static OkHttpClient okHttpClient;

    public static <S> S generateService(
            @NonNull Class<S> serviceClass) {

        return new Retrofit.Builder()
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(DefaultGsonConverter.getConverter())
                .baseUrl(BuildConfig.API_URL)
                .build()
                .create(serviceClass);
    }

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(new HeaderFormatInterceptor());
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(
                        new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY));
            }
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    private static class HeaderFormatInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            String format = original.header("format");
            if (format == null) {
                return chain.proceed(original);
            }
            HttpUrl url = original.url()
                    .newBuilder()
                    .addQueryParameter("format", format)
                    .build();
            return chain.proceed(original.newBuilder()
                    .url(url)
                    .build());
        }
    }
}
