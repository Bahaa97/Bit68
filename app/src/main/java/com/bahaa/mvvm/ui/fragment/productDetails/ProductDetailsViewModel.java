package com.bahaa.mvvm.ui.fragment.productDetails;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailsViewModel extends ViewModel {
    private Product product;
    public ObservableField<String> imageObservable;
    public ObservableField<String> nameObservable;
    public ObservableField<String> weightObservable;
    public ObservableField<String> priceObservable;
    public MutableLiveData<String> action;
    public ProductDetailsViewModel(Product product) {
        this.product = product;
        imageObservable = new ObservableField<>();
        nameObservable = new ObservableField<>();
        weightObservable = new ObservableField<>();
        priceObservable = new ObservableField<>();
        action = new MutableLiveData<>();
        updataUI();
    }


    private void updataUI() {
        imageObservable.set(product.getProductImg());
        nameObservable.set(product.getName());
        weightObservable.set(product.getWeight());
        priceObservable.set(product.getPrice());

    }

    public void onBackPressed(){
        action.setValue(AppTools.BACK);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load( url).into(imageView);
    }

    public void onItemClick() {
        action.setValue(AppTools.OPEN_PRODUCT);
    }
}