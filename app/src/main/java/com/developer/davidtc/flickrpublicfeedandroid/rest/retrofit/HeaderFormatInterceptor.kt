package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit

import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by david on 11/10/17.
 */
internal class HeaderFormatInterceptor : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val original = chain.request()
		val format = original.header("format") ?: return chain.proceed(original)
		val url = original.url()
				.newBuilder()
				.addQueryParameter("format", format)
				.build()
		return chain.proceed(original.newBuilder()
				.url(url)
				.build())
	}
}
