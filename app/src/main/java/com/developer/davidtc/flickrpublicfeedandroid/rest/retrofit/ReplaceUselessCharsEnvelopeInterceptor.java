package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Remove useless characters from the response before propagating it.
 * Must be used very carefully, since the response might be ruined
 * by a poorly configured replacement.
 *
 * To use, add an header to the request with this configuration:
 * uselessContent: "originalValue1, replacementValue1; originalValue2, replacementValue2"
 *
 * Request without the key will be ignored.
 *
 * This interceptor may also affect the operation performance, since multiple strings may be
 * created during the process.
 *
 * Created by david on 11/10/17.
 */
class ReplaceUselessCharsEnvelopeInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        byte[] responseBytes = response.body().bytes();

        String uselessContentHeader = request.header("uselessContent");
        if (uselessContentHeader == null) {
            return response;
        }

        String responseString = new String(responseBytes);
        String[] replacePairs = uselessContentHeader.split(";");
        for (String replacePair : replacePairs) {
            String[] pair = replacePair.split(",");
            responseString = responseString.replace(pair[0], pair[1]);
        }

        MediaType contentType = response.body().contentType();
        ResponseBody body = ResponseBody.create(contentType, responseString.getBytes());
        return response.newBuilder().body(body).build();
    }
}
