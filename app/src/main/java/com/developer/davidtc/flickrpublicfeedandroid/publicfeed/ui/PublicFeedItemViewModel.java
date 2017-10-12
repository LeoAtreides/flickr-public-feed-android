package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.databinding.ObservableField;

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;

/**
 * ViewModel for a list item.
 * Does not extend ViewModel because does not need to be lifecycle aware.
 * <p>
 * Created by david on 12/10/17.
 */

public final class PublicFeedItemViewModel {
    @SuppressWarnings("WeakerAccess") // Required by data binding
    public final ObservableField<FeedItem> item = new ObservableField<>();

    public void setItem(FeedItem item) {
        this.item.set(item);
    }
}
