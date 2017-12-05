package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.developer.davidtc.flickrpublicfeedandroid.R
import com.developer.davidtc.flickrpublicfeedandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val publicFeedViewModel = ViewModelProviders.of(this).get(PublicFeedViewModel::class.java)
		val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
		binding.viewModel = publicFeedViewModel
		setSupportActionBar(binding.mainToolbar)
		publicFeedViewModel.refreshItems()

		publicFeedViewModel.viewState.observe(this, Observer<PublicFeedViewState> {
			it?.let { binding.viewState = it }
		})
	}
}
