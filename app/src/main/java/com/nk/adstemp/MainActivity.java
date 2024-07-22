package com.nk.adstemp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * https://www.youtube.com/playlist?list=PLs1bCj3TvmWnYVrEoaaprIwleBt5AhB6N - Ad's play list
 *
 * https://developers.google.com/admob/android/test-ads#demo_ad_units - test IDs
 * https://firebase.google.com/docs/admob/android/quick-start
 *
 * https://youtu.be/PfD6pCbTeac?si=JJg0x0L_d63Fjhmw
 * https://youtu.be/93vQGG5wN6c?si=dMxAeqMKwSUYrr_Z
 */

public class MainActivity extends AppCompatActivity {

    private Button bannerBtn, nativeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();

        bannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), BannerAdsActivity.class));
            }
        });

        nativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), NativeAdsActivity.class));
            }
        });

    }

    private void setReferences() {
        bannerBtn = findViewById(R.id.banner_btn);
        nativeBtn = findViewById(R.id.native_btn);
    }
}