package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;
import com.squareup.picasso.Picasso;

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

    @BindingAdapter("imageBind")
    public static void bindImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    @BindingAdapter("itemTags")
    public static void bindTags(TextView textView, List<String> tags) {
        StringBuilder tagsBuilder = new StringBuilder();
        for (String tag : tags) {
            tagsBuilder.append(tag);
            tagsBuilder.append(" ");
        }
        textView.setText(tagsBuilder.toString());
    }
}
