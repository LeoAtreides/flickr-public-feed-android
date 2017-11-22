package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.repository.PublicFeedRepository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * ViewModel for public feed data.
 *
 *
 * Created by david on 12/10/17.
 */

class PublicFeedViewModel : ViewModel() {
	//Required for data binding
	val items: ObservableList<FeedItem> = ObservableArrayList()
	//Required for data binding
	val errorState = ObservableField<Throwable>()
	//Required for data binding
	val loadingState = ObservableField(false)

	private val publicFeedRepository: PublicFeedRepository = PublicFeedRepository()
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	fun refreshItems() {
		loadingState.set(true)
		compositeDisposable.add(
				publicFeedRepository.items
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe { feedItems, throwable ->
							errorState.set(throwable)
							items.clear()
							if (feedItems != null) {
								items.addAll(feedItems)
							}
							loadingState.set(false)
						})
	}

	override fun onCleared() {
		compositeDisposable.clear()
		super.onCleared()
	}
}
