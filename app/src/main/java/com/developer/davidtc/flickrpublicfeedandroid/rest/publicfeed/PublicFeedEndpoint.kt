package com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed

import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response.PublicFeedResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Interface for public feed service.
 *
 *
 * Created by david on 09/10/17.
 */

interface PublicFeedEndpoint {
	@get:Headers("format: json", "uselessContent: jsonFlickrFeed(, ;}), }")
	@get:GET("feeds/photos_public.gne")
	val publicFeed: Single<PublicFeedResponse>
}
