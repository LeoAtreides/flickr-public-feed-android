package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.developer.davidtc.flickrpublicfeedandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val publicFeedViewModel = ViewModelProviders.of(this).get(PublicFeedViewModel::class.java)
		setSupportActionBar(main_toolbar)
		publicFeedViewModel.refreshItems()

		main_swipe_refresh.setOnRefreshListener({ publicFeedViewModel.refreshItems() })

		publicFeedViewModel.viewState.observe(this, Observer<PublicFeedViewState> {
			it?.let {
				when {
					it.errorLoading -> Snackbar.make(main_coordinator, R.string.error_loading, Snackbar.LENGTH_LONG).show()
				}

				it.feedItems.let {
					main_recycler.adapter = FeedItemsAdapter(it, main_recycler.context)
				}

				main_swipe_refresh.isRefreshing = it.loading
			}
		})
	}
}
