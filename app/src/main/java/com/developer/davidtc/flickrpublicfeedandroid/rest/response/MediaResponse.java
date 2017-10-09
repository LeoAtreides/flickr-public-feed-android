package com.developer.davidtc.flickrpublicfeedandroid.rest.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Class for media url from response.
 * <p>
 * Created by david on 09/10/17.
 */
@AutoValue
public abstract class MediaResponse {
    @SerializedName("m")
    public abstract String mediaUrl();

    public static TypeAdapter<MediaResponse> typeAdapter(Gson gson) {
        return new AutoValue_MediaResponse.GsonTypeAdapter(gson);
    }
}
