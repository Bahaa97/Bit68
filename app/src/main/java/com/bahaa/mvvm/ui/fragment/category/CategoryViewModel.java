package com.bahaa.mvvm.ui.fragment.category;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private CategoryResponse category;
    private String id;
    public ObservableField<String> imageObservable;
    public ObservableField<String> nameObservable;
    public MutableLiveData<List<Product>> productMutableLiveData;
    public MutableLiveData<String> action;
    public CategoryViewModel(CategoryResponse category) {
        this.category = category;
        imageObservable = new ObservableField<>();
        nameObservable = new ObservableField<>();
        productMutableLiveData = new MutableLiveData<>();
        action = new MutableLiveData<>();
        updataUI();
    }


    private void updataUI() {
        imageObservable.set(category.getCategoryImg());
        nameObservable.set(category.getName());

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