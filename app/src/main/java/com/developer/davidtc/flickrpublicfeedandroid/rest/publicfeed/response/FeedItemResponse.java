package com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Class for public feed item.
 * <p>
 * Created by david on 09/10/17.
 */
@AutoValue
public abstract class FeedItemResponse {
    public abstract String title();

    public abstract String link();

    @Nullable
    public abstract String publishedDate();

    public abstract String author();

    public abstract String tags();

    public abstract MediaResponse media();

    public static TypeAdapter<FeedItemResponse> typeAdapter(Gson gson) {
        return new AutoValue_FeedItemResponse.GsonTypeAdapter(gson);
    }
}
