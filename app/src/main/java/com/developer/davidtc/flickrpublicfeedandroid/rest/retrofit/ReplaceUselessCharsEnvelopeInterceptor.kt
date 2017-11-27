package com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * Remove useless characters from the response before propagating it.
 * Must be used very carefully, since the response might be ruined
 * by a poorly configured replacement.
 *
 * To use, add an header to the request with this configuration:
 * uselessContent: "originalValue1, replacementValue1; originalValue2, replacementValue2"
 *
 * Request without the key will be ignored.
 *
 * This interceptor may also affect the operation performance, since multiple strings may be
 * created during the process.
 *
 * Created by david on 11/10/17.
 */
internal class ReplaceUselessCharsEnvelopeInterceptor : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val response = chain.proceed(request)
		val uselessContentHeader = request.header("uselessContent") ?: return response
		val responseBytes = response.body()!!.bytes()
		var responseString = String(responseBytes)
		val replacePairs = uselessContentHeader
				.split(";".toRegex())
				.dropLastWhile { it.isEmpty() }
				.toTypedArray()
		replacePairs.forEach { replacePair ->
			val pair = replacePair
					.split(",".toRegex())
					.dropLastWhile { it.isEmpty() }
					.toTypedArray()
			responseString = responseString.replace(pair[0], pair[1])
		}

		val contentType = response.body()!!.contentType()
		val body = ResponseBody.create(contentType, responseString.toByteArray())
		return response.newBuilder().body(body).build()
	}
}
