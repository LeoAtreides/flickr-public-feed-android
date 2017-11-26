package com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed;

import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response.PublicFeedResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Interface for public feed service.
 * <p>
 * Created by david on 09/10/17.
 */

public interface PublicFeedEndpoint {
    @Headers({
            "format: json",
            "uselessContent: jsonFlickrFeed(, ;}), }"
    })
    @GET("feeds/photos_public.gne")
    Single<PublicFeedResponse> getPublicFeed();
}
