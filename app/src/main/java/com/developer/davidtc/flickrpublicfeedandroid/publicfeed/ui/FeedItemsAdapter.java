package com.developer.davidtc.flickrpublicfeedandroid.publicfeed.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.developer.davidtc.flickrpublicfeedandroid.R;
import com.developer.davidtc.flickrpublicfeedandroid.databinding.ItemPublicFeedBinding;
import com.developer.davidtc.flickrpublicfeedandroid.publicfeed.data.FeedItem;

import java.util.List;

/**
 * Adapter for feed items.
 * <p>
 * Created by david on 12/10/17.
 */

final class FeedItemsAdapter extends RecyclerView.Adapter<FeedItemsAdapter.ViewHolder> {
    private final List<FeedItem> items;
    private final LayoutInflater layoutInflater;

    FeedItemsAdapter(@NonNull List<FeedItem> items, @NonNull Context context) {
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPublicFeedBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_public_feed, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItem feedItem = items.get(position);
        holder.bind(feedItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemPublicFeedBinding binding;
        private final PublicFeedItemViewModel publicFeedItemViewModel;

        ViewHolder(ItemPublicFeedBinding binding) {
            super(binding.getRoot());
            publicFeedItemViewModel = new PublicFeedItemViewModel();
            this.binding = binding;
            this.binding.setItemViewModel(publicFeedItemViewModel);
        }

        void bind(FeedItem item) {
            publicFeedItemViewModel.setItem(item);
            binding.executePendingBindings();
        }
    }
}
