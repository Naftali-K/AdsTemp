package com.nk.adstemp.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nk.adstemp.R;
import com.nk.adstemp.models.ModelProduct;

import java.util.ArrayList;
import java.util.List;

public class NativeAdsViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<List<ModelProduct>> productMutableLiveData = new MutableLiveData<>();

    public void init(Context context) {
        this.context = context;
    }

    public LiveData<List<ModelProduct>> getProductList() {
        return productMutableLiveData;
    }

    public void createProductList() {
        String[] titles = {
                "Android 1.0",
                "Android 1.1",
                "Android 1.2",
                "Android 1.3",
                "Android 1.4",
                "Android 1.5",
                "Android 1.6",
                "Android 1.7",
                "Android 1.8",
                "Android 1.9",
                "Android 2.0",
                "Android 2.1",
                "Android 2.2",
                "Android 2.3",
                "Android 2.4",
                "Android 2.5",
                "Android 2.6",
                "Android 2.7",
                "Android 2.8",
                "Android 2.9",
                "Android 3.0",
        };

        String[] description = {
                "Android Alpha",
                "Android Beta",
                "Android Cupcake",
                "Android Donut",
                "Android Eclair",
                "Android Froyo",
                "Android Gingerbread",
                "Android Honeycomb",
                "Android Ice Cream Sandwich",
                "Android Jelly Bean",
                "Android KitKat",
                "Android Lollipop",
                "Android Marshmallow",
                "Android Nougat",
                "Android Oreo",
                "Android Pie",
                "Android Q",
                "Android R",
                "Android S",
                "Android Tiramisu",
                "Android UpsideDownCake",
        };

        List<ModelProduct> productList = new ArrayList<>();

        for (int i=0; i < titles.length; i++) {
            ModelProduct modelProduct = new ModelProduct(R.drawable.ic_android_black, titles[i], description[i], 3.5f);
            productList.add(modelProduct);
        }

        productMutableLiveData.setValue(productList);
    }
}
