package com.developer.davidtc.flickrpublicfeedandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.PublicFeedEndpoint;
import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response.PublicFeedResponse;
import com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit.RetrofitServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PublicFeedEndpoint publicFeedEndpoint =
				RetrofitServiceGenerator.generateService(PublicFeedEndpoint.class);

		publicFeedEndpoint.getPublicFeed()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new DisposableSingleObserver<PublicFeedResponse>() {
					@Override
					public void onSuccess(PublicFeedResponse value) {
						Log.d("David", "onSuccess");
					}

					@Override
					public void onError(Throwable e) {
						Log.d("David", "onError");
					}
				});
	}
}
