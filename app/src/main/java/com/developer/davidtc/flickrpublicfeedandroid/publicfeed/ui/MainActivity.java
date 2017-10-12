package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.developer.davidtc.flickrpublicfeedandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PublicFeedViewModel publicFeedViewModel =
                ViewModelProviders.of(this).get(PublicFeedViewModel.class);
        publicFeedViewModel.refreshItems();
    }
}
