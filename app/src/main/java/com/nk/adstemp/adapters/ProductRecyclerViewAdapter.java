package com.nk.adstemp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.nk.adstemp.R;
import com.nk.adstemp.models.ModelProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final String TAG = "Test_code";

    private Context context;
    private List<ModelProduct> productList = new ArrayList<>();

    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_AD = 1;
    private final int AD_REPLAY = 5;

    public ProductRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public ProductRecyclerViewAdapter(Context context, List<ModelProduct> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CONTENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row_product, parent, false);
            return new ProductRecyclerViewHolder(view);
        }

        if (viewType == VIEW_TYPE_AD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row_native_ad, parent, false);
            return new NativeRecyclerViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int newPosition = position - (position / AD_REPLAY);
        if (getItemViewType(position) == VIEW_TYPE_CONTENT) {
//            ((ProductRecyclerViewHolder)holder).bind(productList.get(position), position);
            ((ProductRecyclerViewHolder)holder).bind(productList.get(newPosition), newPosition);
        }

        if (getItemViewType(position) == VIEW_TYPE_AD) {
            ((NativeRecyclerViewHolder)holder).bind();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % AD_REPLAY == 0 && position != 0) {
            return VIEW_TYPE_AD;
        } else {
            return VIEW_TYPE_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
//        return productList.size();
        return productList.size() + (productList.size()/AD_REPLAY);
    }

    class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {

        private View container;
        private ImageView iconIv;
        private TextView titleTv, descriptionTv;
        private RatingBar ratingBar;

        public ProductRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView;
            iconIv = itemView.findViewById(R.id.iconIv);
            titleTv = itemView.findViewById(R.id.titleTv);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
        }

        void bind(ModelProduct product, int position) {
            titleTv.setText(product.getTitle());
            ratingBar.setRating(product.getRating());
            descriptionTv.setText(product.getDescription());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + product.getTitle() + " position: " + position);
                }
            });
        }
    }


    class NativeRecyclerViewHolder extends RecyclerView.ViewHolder {

        private NativeAdView nativeAdView;
        private ImageView ad_app_icon;
        private TextView ad_headline, ad_advertiser, ad_body, ad_price, ad_store;
        private RatingBar ad_stars;
        private MediaView media_view;
        private Button ad_call_to_action;

        public NativeRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            nativeAdView = itemView.findViewById(R.id.nativeAdView);
            ad_app_icon = itemView.findViewById(R.id.ad_app_icon);
            ad_headline = itemView.findViewById(R.id.ad_headline);
            ad_advertiser = itemView.findViewById(R.id.ad_advertiser);
            ad_stars = itemView.findViewById(R.id.ad_stars);
            ad_body = itemView.findViewById(R.id.ad_body);
            media_view = itemView.findViewById(R.id.media_view);
            ad_price = itemView.findViewById(R.id.ad_price);
            ad_store = itemView.findViewById(R.id.ad_store);
            ad_call_to_action = itemView.findViewById(R.id.ad_call_to_action);
        }

        void bind() {

            AdLoader adLoader = new AdLoader.Builder(context, context.getString(R.string.native_ad_unit_id))
//            AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
//            AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/1044960115")
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                    Log.d(TAG, "onNativeAdLoaded: Ad loaded success ");

                    if (nativeAd.getIcon() != null) {
                        ad_app_icon.setVisibility(View.VISIBLE);
                        ad_app_icon.setImageDrawable(nativeAd.getIcon().getDrawable());
                    } else {
                        ad_app_icon.setVisibility(View.GONE);
                    }

                    if (nativeAd.getHeadline() != null) {
                        ad_headline.setVisibility(View.VISIBLE);
                        ad_headline.setText(nativeAd.getHeadline());
                    } else {
                        ad_headline.setVisibility(View.GONE);
                    }

                    if (nativeAd.getAdvertiser() != null) {
                        ad_advertiser.setVisibility(View.VISIBLE);
                        ad_advertiser.setText(nativeAd.getAdvertiser());
                    } else {
                        ad_advertiser.setVisibility(View.GONE);
                    }

                    if (nativeAd.getStarRating() != null) {
                        ad_stars.setVisibility(View.VISIBLE);
//                        ad_stars.setRating(Float.valueOf(nativeAd.getStarRating().toString()));
                        ad_stars.setRating(nativeAd.getStarRating().floatValue());
                    } else {
                        ad_stars.setVisibility(View.GONE);
                    }

                    if (nativeAd.getBody() != null) {
                        ad_body.setVisibility(View.VISIBLE);
                        ad_body.setText(nativeAd.getBody());
                    } else {
                        ad_body.setVisibility(View.GONE);
                    }

                    if (nativeAd.getMediaContent() != null) {
                        media_view.setVisibility(View.VISIBLE);
                        media_view.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                        media_view.setMediaContent(nativeAd.getMediaContent());
                    } else {
                        media_view.setVisibility(View.GONE);
                    }

                    if (nativeAd.getPrice() != null) {
                        ad_price.setVisibility(View.VISIBLE);
                        ad_price.setText(nativeAd.getPrice());
                    } else {
                        ad_price.setVisibility(View.GONE);
                    }

                    if (nativeAd.getStore() != null) {
                        ad_store.setVisibility(View.VISIBLE);
                        ad_store.setText(nativeAd.getStore());
                    } else {
                        ad_store.setVisibility(View.GONE);
                    }

                    if (nativeAd.getCallToAction() != null) {
                        ad_call_to_action.setVisibility(View.VISIBLE);
                        ad_call_to_action.setText(nativeAd.getCallToAction());
                        //native ad button click
                        nativeAdView.setCallToActionView(ad_call_to_action);
                    } else {
                        ad_call_to_action.setVisibility(View.GONE);
                    }

                    nativeAdView.setNativeAd(nativeAd);
                }
            })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                            Log.d(TAG, "onAdClicked: ");
                        }

                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                            Log.d(TAG, "onAdClosed: ");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
//                            Log.d(TAG, "onAdFailedToLoad: Error: " + loadAdError.getMessage()
//                                    + "\nError code: " + loadAdError.getCode() + "\nAll: " + loadAdError.toString());

                            Log.d(TAG, "onAdFailedToLoad: Error: " + loadAdError.getMessage() + "\nError code: " + loadAdError.getCode());
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                            Log.d(TAG, "onAdImpression: ");
                        }

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            Log.d(TAG, "onAdLoaded: ");
                        }

                        @Override
                        public void onAdOpened() {
                            super.onAdOpened();
                            Log.d(TAG, "onAdOpened: ");
                        }

                        @Override
                        public void onAdSwipeGestureClicked() {
                            super.onAdSwipeGestureClicked();
                            Log.d(TAG, "onAdSwipeGestureClicked: ");
                        }
            })
                    .withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    public void setProductList(List<ModelProduct> productList) {
        if (productList != null) {
            this.productList = productList;
            notifyDataSetChanged();
        }
    }
}
