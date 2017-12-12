package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
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

	var viewState: ObservableField<PublicFeedViewState> = ObservableField(PublicFeedViewState())

	private val publicFeedRepository: PublicFeedRepository = PublicFeedRepository()
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	fun refreshItems() {
		viewState.set(viewState.get()!!.copy(loading = true))
		compositeDisposable.add(
				publicFeedRepository.loadItems()
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe({
							viewState.set(PublicFeedViewState(loading = false, errorLoading = false, feedItems = it))
						},
								{
									viewState.set(PublicFeedViewState(loading = false, errorLoading = true, feedItems = emptyList()))
								}))
	}

	override fun onCleared() {
		compositeDisposable.clear()
		super.onCleared()
	}
}
