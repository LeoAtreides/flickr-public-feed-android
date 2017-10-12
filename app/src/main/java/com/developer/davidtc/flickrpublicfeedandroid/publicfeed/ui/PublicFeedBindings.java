package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;

import java.util.List;

/** Data binding custom adapters.
 *
 * Created by david on 12/10/17.
 */

public final class PublicFeedBindings {

    @BindingAdapter("publicFeedList")
    public static void bindList(RecyclerView recyclerView, List<FeedItem> items) {
        if (items == null || items.size() == 0) {
            recyclerView.setAdapter(null);
            return;
        }
        recyclerView.setAdapter(
                new FeedItemsAdapter(items, recyclerView.getContext()));
    }
}
