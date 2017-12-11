package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui

import android.content.Intent
import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/** Data binding custom adapters.
 *
 * Created by david on 12/10/17.
 */

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
