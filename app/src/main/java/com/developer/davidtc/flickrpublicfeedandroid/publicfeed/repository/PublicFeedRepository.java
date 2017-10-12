package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.repository;

import com.developer.davidtc.flickrpublicfeedandroid.BuildConfig;
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;
import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.PublicFeedEndpoint;
import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response.FeedItemResponse;
import com.developer.davidtc.flickrpublicfeedandroid.rest.publicfeed.response.PublicFeedResponse;
import com.developer.davidtc.flickrpublicfeedandroid.rest.retrofit.RetrofitServiceGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Class for obtaining a public feed data.
 * <p>
 * Created by david on 11/10/17.
 */

public final class PublicFeedRepository {

    private static final String TAGS_SEPARATOR = " ";
    private final DateFormat dateFormat;

    public PublicFeedRepository() {
        dateFormat = SimpleDateFormat.getDateInstance();
    }

    public Single<List<FeedItem>> getItems() {
        PublicFeedEndpoint publicFeedEndpoint =
                RetrofitServiceGenerator.generateService(PublicFeedEndpoint.class);

        return publicFeedEndpoint.getPublicFeed()
                .toObservable()
                .flatMapIterable(PublicFeedResponse::items)
                .map(feedItemResponse -> {
                    return FeedItem.builder()
                            .title(feedItemResponse.title())
                            .link(feedItemResponse.media().mediaUrl())
                            .publishedDate(parseDate(feedItemResponse.publishedDate()))
                            .author(buildAuthor(feedItemResponse.author()))
                            .tags(buildTags(feedItemResponse))
                            .build();
                })
                .toList()
                .subscribeOn(Schedulers.io());
    }

    private Date parseDate(String publishedDate) {
        if (publishedDate == null) {
            return null;
        }
        try {
            return dateFormat.parse(publishedDate);
        } catch (ParseException e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String buildAuthor(String responseAuthor) {
        //TODO extract the author
        return responseAuthor;
    }

    private List<String> buildTags(FeedItemResponse feedItemResponse) {
        String[] split = feedItemResponse.tags().split(TAGS_SEPARATOR);
        List<String> tags = new ArrayList<>(split.length);
        Collections.addAll(tags, split);
        return tags;
    }

}
