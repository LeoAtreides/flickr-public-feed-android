package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;

import java.util.List;

/**
 * Created by david on 12/10/17.
 */

public final class PublicFeedBindings {

    @BindingAdapter("publicFeedList")
    public static void bindList(RecyclerView recyclerView, List<FeedItem> items) {
        Log.d("David", "bindList");
        //TODO
    }
}
