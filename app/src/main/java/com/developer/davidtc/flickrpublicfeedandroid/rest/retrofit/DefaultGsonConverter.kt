package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit

import com.google.gson.GsonBuilder

import retrofit2.converter.gson.GsonConverterFactory

/**
 * Factory for integration of GSON and Retrofit.
 *
 *
 * Created by david on 21/02/17.
 */

internal object DefaultGsonConverter {

	private var gsonConverter: GsonConverterFactory =
			GsonConverterFactory.create(GsonBuilder().create())

	val converter: GsonConverterFactory
		get() {
			return gsonConverter
		}
}