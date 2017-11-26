package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data

import java.util.*

/**
 * Created by david.tiago on 11/26/17.
 */
data class FeedItem(
		val title: String,
		val link: String,
		val imageUrl: String,
		val publishedDate: Date?,
		val author: String,
		val tags: List<String>)