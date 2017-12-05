package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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

	val viewState: MutableLiveData<PublicFeedViewState> = MutableLiveData()

	private val publicFeedRepository: PublicFeedRepository = PublicFeedRepository()
	private val compositeDisposable: CompositeDisposable = CompositeDisposable()

	init {
		viewState.value = PublicFeedViewState()
	}

	private fun currentViewState(): PublicFeedViewState = viewState.value!!

	fun refreshItems() {
		viewState.value = currentViewState().copy(loading = true)
		compositeDisposable.add(
				publicFeedRepository.loadItems()
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe({
							viewState.value = PublicFeedViewState(loading = false, errorLoading = false, feedItems = it)
						},
								{
									viewState.value = PublicFeedViewState(loading = false, errorLoading = true, feedItems = emptyList())
								}))
	}

	override fun onCleared() {
		compositeDisposable.clear()
		super.onCleared()
	}
}
