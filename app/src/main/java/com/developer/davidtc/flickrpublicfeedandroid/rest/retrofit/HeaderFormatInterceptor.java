package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by david on 11/10/17.
 */
class HeaderFormatInterceptor implements Interceptor {
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
