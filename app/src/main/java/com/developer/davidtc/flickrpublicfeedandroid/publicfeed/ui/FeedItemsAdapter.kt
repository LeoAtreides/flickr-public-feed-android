package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.developer.davidtc.flickrpublicfeedandroid.R
import com.developer.davidtc.flickrpublicfeedandroid.databinding.ItemPublicFeedBinding
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem

/**
 * Adapter for feed items.
 *
 *
 * Created by david on 12/10/17.
 */

internal class FeedItemsAdapter(private val items: List<FeedItem>, context: Context) : RecyclerView.Adapter<FeedItemsAdapter.ViewHolder>() {
	private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = DataBindingUtil.inflate<ItemPublicFeedBinding>(
				layoutInflater, R.layout.item_public_feed, parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val feedItem = items[position]
		holder.bind(feedItem)
	}

	override fun getItemCount(): Int {
		return items.size
	}

	internal class ViewHolder(private val binding: ItemPublicFeedBinding) : RecyclerView.ViewHolder(binding.root) {
		private val publicFeedItemViewModel: PublicFeedItemViewModel = PublicFeedItemViewModel()

		init {
			this.binding.itemViewModel = publicFeedItemViewModel
		}

		fun bind(item: FeedItem) {
			publicFeedItemViewModel.setItem(item)
			binding.executePendingBindings()
		}
	}
}
