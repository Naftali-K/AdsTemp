package com.nk.adstemp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;

/**
 * https://developers.google.com/admob/android/test-ads#demo_ad_units - test IDs
 * https://firebase.google.com/docs/admob/android/quick-start
 *
 * https://youtu.be/PfD6pCbTeac?si=JJg0x0L_d63Fjhmw - part 1
 * https://firebase.google.com/docs/admob/android/quick-start - app_id_test
 *
 * https://youtu.be/_U-_6BEqxHo?si=QlBQ3v-07IX_DMt7 - part 2
 * https://developers.google.com/admob/android/banner#add_adview - banner_ad_unit_id
 * https://developers.google.com/admob/android/banner/fixed-size - banner sizes
 *
 * https://youtu.be/93vQGG5wN6c?si=dMxAeqMKwSUYrr_Z
 */

public class BannerAdsActivity extends AppCompatActivity {

    private static final String TAG = "Test_code";

    private AdView mAdView;
    private RelativeLayout bannerAds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ads);
        setReferences();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getBaseContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                Log.d(TAG, "onInitializationComplete: SDK Init");
            }
        });
        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("", "")).build());

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        
        mAdView.setAdListener(new AdListener() {
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
                Log.d(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
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
        });
    }

    private void setReferences() {
        mAdView = findViewById(R.id.adView);
        bannerAds = findViewById(R.id.bannerAds);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }
    }
}