package com.bahaa.mvvm.ui.adapter.productAdapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

public class ProductViewModel extends ViewModel {
    private Product product;
    public ObservableField<String> imageObservable;
    public ObservableField<String> productNameObservable;
    public ObservableField<String> productWeightObservable;
    public ObservableField<String> productPriceObservable;
    public MutableLiveData<String> action;

    public ProductViewModel(Product product) {
        this.product = product;
        imageObservable = new ObservableField<>();
        productNameObservable = new ObservableField<>();
        productWeightObservable = new ObservableField<>();
        productPriceObservable = new ObservableField<>();
        action = new MutableLiveData<>();
        updateUI();
    }

    private void updateUI() {
        imageObservable.set(product.getProductImg());
        productNameObservable.set(product.getName());
        productWeightObservable.set(product.getWeight());
        productPriceObservable.set(product.getPrice());
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setproduct(Product product) {
        this.product = product;
    }


    public void onItemClick() {
        action.setValue(AppTools.OPEN_PRODUCT);
    }
}
