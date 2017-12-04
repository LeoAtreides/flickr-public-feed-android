package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem

/**
 * Created by david.tiago on 12/4/17.
 */
data class PublicFeedViewState(
		val loading: Boolean = false,
		val errorLoading: Boolean = false,
		val feedItems: List<FeedItem> = emptyList())