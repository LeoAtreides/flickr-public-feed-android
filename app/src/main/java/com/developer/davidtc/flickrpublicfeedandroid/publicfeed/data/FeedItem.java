package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

/**
 * Item of a public feed.
 * <p>
 * Created by david on 11/10/17.
 */
@AutoValue
public abstract class FeedItem {
    public abstract String title();

    public abstract String link();

    @Nullable
    public abstract Date publishedDate();

    public abstract String author();

    public abstract List<String> tags();

    public static Builder builder() {
        return new AutoValue_FeedItem.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder title(String value);

        public abstract Builder link(String value);

        public abstract Builder publishedDate(Date value);

        public abstract Builder author(String value);

        public abstract Builder tags(List<String> value);

        public abstract FeedItem build();
    }
}
