package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.repository

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem
import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.PublicFeedEndpoint
import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response.FeedItemResponse
import com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit.RetrofitServiceGenerator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Class for obtaining a public feed data.
 *
 *
 * Created by david on 11/10/17.
 */

class PublicFeedRepository {
	private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
	private val tagsSeparator = " "

	fun loadItems(): Single<List<FeedItem>> {
		return RetrofitServiceGenerator.generateService(PublicFeedEndpoint::class.java)
				.publicFeed
				.toObservable()
				.flatMapIterable<FeedItemResponse>({ it.items })
				.map { feedItemResponse ->
					FeedItem(
							feedItemResponse.title,
							feedItemResponse.link,
							feedItemResponse.media.m,
							parseDate(feedItemResponse.published),
							buildAuthor(feedItemResponse.author),
							buildTags(feedItemResponse))
				}
				.toList()
				.subscribeOn(Schedulers.io())
	}

	private fun parseDate(publishedDate: String?): Date? {
		publishedDate?.let {
			return try {
				dateFormat.parse(it)
			} catch (e: ParseException) {
				e.printStackTrace()
				null
			}
		}
		return null
	}

	private fun buildAuthor(responseAuthor: String): String {
		val startIndex = responseAuthor.indexOf("(\"")
		val endIndex = responseAuthor.lastIndexOf("\")")
		return responseAuthor.substring(startIndex + 2, endIndex)
	}

	private fun buildTags(feedItemResponse: FeedItemResponse): List<String> {
		val split = feedItemResponse.tags
				.split(tagsSeparator.toRegex())
				.dropLastWhile { it.isEmpty() }
				.toTypedArray()
		val tags = ArrayList<String>(split.size)
		Collections.addAll(tags, *split)
		return tags
	}
}
