package com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response

/**
 * Created by david.tiago on 11/26/17.
 */
data class FeedItemResponse(
		val title: String,
		val link: String,
		val published: String?,
		val author: String,
		val tags: String,
		val media: MediaResponse)