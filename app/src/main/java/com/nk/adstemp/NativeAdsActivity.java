package com.nk.adstemp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nk.adstemp.adapters.ProductRecyclerViewAdapter;
import com.nk.adstemp.models.ModelProduct;
import com.nk.adstemp.viewModel.NativeAdsViewModel;

import java.util.Arrays;
import java.util.List;

/**
 * https://developers.google.com/admob/android/test-ads#demo_ad_units - test IDs
 * https://firebase.google.com/docs/admob/android/quick-start - quick start with select type of Ad
 *
 *
 * https://youtu.be/gzqJFah43pc?si=_b1BEbLGV0PqP-Ei - part 1
 * https://developers.google.com/admob/android/native - ID
 * https://developers.google.com/admob/android/native/advanced
 * https://developers.google.com/admob/android/ad-load-errors - Errors info
 * https://developers.google.com/android/reference/com/google/android/gms/ads/AdRequest#constant-summary - error info by code
 *
 * https://support.google.com/admob/answer/6240809?sjid=9301765169000092149-AP&hl=ru - rules which views values in Native Ad
 * https://support.google.com/admob/answer/6240814 - views rules, sizes and etc.
 * https://youtu.be/AfCvQ4p_1y8?si=a-I0rtx40ZBBsqto - video rules for create Native Ad
 */

public class NativeAdsActivity extends AppCompatActivity {

    private static final String TAG = "Test_code";
    private NativeAdsViewModel viewModel;

    private RecyclerView productsRv;
    private ProductRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ads);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Native Ad's");
        setReferences();
        setViewModel();

        MobileAds.initialize(getBaseContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                Log.d(TAG, "onInitializationComplete: " + initializationStatus);
            }
        });
        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder()
//                .setTestDeviceIds(Arrays.asList("ADD_TEST_DEVICE_ID_HERE", "ADD_TEST_DEVICE_ID_HERE"))
                .setTestDeviceIds(Arrays.asList("", ""))
                .build());

        adapter = new ProductRecyclerViewAdapter(getBaseContext());
        productsRv.setAdapter(adapter);
        viewModel.createProductList();
    }

    private void setReferences() {
        productsRv = findViewById(R.id.productsRv);
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this).get(NativeAdsViewModel.class);
        viewModel.init(getBaseContext());

        viewModel.getProductList().observe(this, new Observer<List<ModelProduct>>() {
            @Override
            public void onChanged(List<ModelProduct> modelProducts) {
                adapter.setProductList(modelProducts);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}