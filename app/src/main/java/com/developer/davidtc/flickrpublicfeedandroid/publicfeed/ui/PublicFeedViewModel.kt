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
	val items: ObservableList<FeedItem> = ObservableArrayList()
	val errorState = ObservableField<Throwable>()
	val loadingState = ObservableField(false)

	private val publicFeedRepository: PublicFeedRepository = PublicFeedRepository()
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	fun refreshItems() {
		loadingState.set(true)
		compositeDisposable.add(
				publicFeedRepository.loadItems()
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe({
							items.clear()
							if (it != null) {
								items.addAll(it)
							}
							loadingState.set(false)
						},
						{
							errorState.set(it)
							loadingState.set(false)
						}))
	}

	override fun onCleared() {
		compositeDisposable.clear()
		super.onCleared()
	}
}
