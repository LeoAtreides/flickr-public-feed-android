package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.repository.PublicFeedRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * ViewModel for public feed data.
 * <p>
 * Created by david on 12/10/17.
 */

public final class PublicFeedViewModel extends ViewModel {
    public final ObservableField<List<FeedItem>> items = new ObservableField<>();
    public final ObservableField<Throwable> errorState = new ObservableField<>();

    private final PublicFeedRepository publicFeedRepository;
    private final CompositeDisposable compositeDisposable;

    public PublicFeedViewModel() {
        publicFeedRepository = new PublicFeedRepository();
        compositeDisposable = new CompositeDisposable();
    }

    void refreshItems() {
        compositeDisposable.add(
                publicFeedRepository.getItems()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((feedItems, throwable) -> {
                            items.set(feedItems);
                            errorState.set(throwable);
                        }));
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
