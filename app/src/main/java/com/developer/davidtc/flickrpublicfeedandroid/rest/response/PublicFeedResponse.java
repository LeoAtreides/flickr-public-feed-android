package com.developer.davidtc.flickrpublicfeedandroid.rest.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Reponse from a public feed request.
 * <p>
 * Created by david on 09/10/17.
 */
@AutoValue
public abstract class PublicFeedResponse {
    public abstract List<FeedItemResponse> items();

    public static TypeAdapter<PublicFeedResponse> typeAdapter(Gson gson) {
        return new AutoValue_PublicFeedResponse.GsonTypeAdapter(gson);
    }
}
