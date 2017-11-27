package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.content.Intent
import android.databinding.BindingAdapter
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.developer.davidtc.flickrpublicfeedandroid.R
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/** Data binding custom adapters.
 *
 * Created by david on 12/10/17.
 */

@BindingAdapter("publicFeedList")
fun bindList(recyclerView: RecyclerView, items: List<FeedItem>?) {
	if (items == null || items.isEmpty()) {
		recyclerView.adapter = null
		return
	}
	recyclerView.adapter = FeedItemsAdapter(items, recyclerView.context)
}

@BindingAdapter("imageBind")
fun bindImage(imageView: ImageView, imageUrl: String) {
	Picasso.with(imageView.context)
			.load(imageUrl)
			.fit()
			.centerCrop()
			.into(imageView)
}

@BindingAdapter("itemTags")
fun bindTags(textView: TextView, tags: List<String>) {
	val tagsBuilder = StringBuilder()
	tags.forEach { tag ->
		tagsBuilder.append(tag)
		tagsBuilder.append(" ")
	}
	textView.text = tagsBuilder.toString()
}

@BindingAdapter("itemDate")
fun bindDate(textView: TextView, date: Date?) {
	if (date == null) {
		textView.text = ""
		return
	}
	textView.text = SimpleDateFormat.getDateTimeInstance()
			.format(date)
}

@BindingAdapter("launchExternal")
fun bindLaunchExternal(imageView: ImageView, link: String) {
	imageView.setOnClickListener { v ->
		val context = v.context
		val webPage = Uri.parse(link)
		val intent = Intent(Intent.ACTION_VIEW, webPage)
		if (intent.resolveActivity(context.packageManager) != null) {
			context.startActivity(intent)
		}
	}
}

@BindingAdapter("errorFeedback")
fun bindErrorFeedback(view: View, errorState: Throwable?) {
	if (errorState != null) {
		Snackbar.make(view, R.string.error_loading, Snackbar.LENGTH_LONG)
				.show()
	}
}

@BindingAdapter("refreshAction")
fun bindRefreshAction(view: SwipeRefreshLayout, viewModel: PublicFeedViewModel) {
	view.setOnRefreshListener({ viewModel.refreshItems() })
}

@BindingAdapter("loadingState")
fun bindLoadingState(view: SwipeRefreshLayout, loadingState: Boolean) {
	view.isRefreshing = loadingState
}
