package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit

import com.developer.davidtc.flickrpublicfeedandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Common class for retrofit service generation.
 *
 *
 * Created by david on 21/02/17.
 */

object RetrofitServiceGenerator {

	private val okHttpClient: OkHttpClient = getOkHttpClient()

	fun <S> generateService(
			serviceClass: Class<S>): S {

		return Retrofit.Builder()
				.client(okHttpClient)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(DefaultGsonConverter.converter)
				.baseUrl(BuildConfig.API_URL)
				.build()
				.create(serviceClass)
	}

	private fun getOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
				.connectTimeout(1, TimeUnit.MINUTES)
				.writeTimeout(1, TimeUnit.MINUTES)
				.readTimeout(1, TimeUnit.MINUTES)
				.addInterceptor(HeaderFormatInterceptor())
				.addInterceptor(ReplaceUselessCharsEnvelopeInterceptor())
				.addInterceptor(
						HttpLoggingInterceptor()
								.setLevel(HttpLoggingInterceptor.Level.BODY))
				.build()
	}

}
