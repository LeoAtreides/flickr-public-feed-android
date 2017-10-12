package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.davidtc.flickrpublicfeedandroid.R;
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @BindingAdapter("itemDate")
    public static void bindDate(TextView textView, Date date) {
        if (date == null) {
            textView.setText("");
            return;
        }
        textView.setText(
                SimpleDateFormat.getDateTimeInstance()
                        .format(date));
    }

    @BindingAdapter("launchExternal")
    public static void bindLaunchExternal(ImageView imageView, String link) {
        imageView.setOnClickListener(v -> {
            Context context = v.getContext();
            Uri webPage = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        });
    }

    @BindingAdapter("errorFeedback")
    public static void bindErrorFeedback(View view, Throwable errorState) {
        if (errorState != null) {
            Snackbar.make(view, R.string.error_loading, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @BindingAdapter("refreshAction")
    public static void bindRefreshAction(SwipeRefreshLayout view, PublicFeedViewModel viewModel) {
        view.setOnRefreshListener(viewModel::refreshItems);
    }

    @BindingAdapter("loadingState")
    public static void bindLoadingState(SwipeRefreshLayout view, boolean loadingState) {
        view.setRefreshing(loadingState);
    }
}
