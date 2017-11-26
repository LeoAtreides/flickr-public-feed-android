package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.databinding.ObservableField

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem

/**
 * ViewModel for a list item.
 * Does not extend ViewModel because does not need to be lifecycle aware.
 *
 * Created by david on 12/10/17.
 */

class PublicFeedItemViewModel {
	val item = ObservableField<FeedItem>()

	fun setItem(item: FeedItem) {
		this.item.set(item)
	}
}
