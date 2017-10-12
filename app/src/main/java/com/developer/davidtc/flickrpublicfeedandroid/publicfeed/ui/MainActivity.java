package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.developer.davidtc.flickrpublicfeedandroid.R;
import com.developer.davidtc.flickrpublicfeedandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PublicFeedViewModel publicFeedViewModel =
                ViewModelProviders.of(this)
                        .get(PublicFeedViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main);
        binding.setViewModel(publicFeedViewModel);

        setSupportActionBar(binding.mainToolbar);

        publicFeedViewModel.refreshItems();
    }
}
