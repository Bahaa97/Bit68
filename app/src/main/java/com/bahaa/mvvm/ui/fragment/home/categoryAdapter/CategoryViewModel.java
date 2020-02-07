package com.bahaa.mvvm.ui.fragment.home.categoryAdapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

public class CategoryViewModel extends ViewModel {
    private CategoryResponse category;
    public ObservableField<String> imageObservable;
    public ObservableField<String> productNameObservable;
    public MutableLiveData<String> action;

    public CategoryViewModel(CategoryResponse category) {
        this.category = category;
        imageObservable = new ObservableField<>();
        productNameObservable = new ObservableField<>();
        action = new MutableLiveData<>();
        updateUI();
    }

    private void updateUI() {
        imageObservable.set(category.getCategoryImg());
        productNameObservable.set(category.getName());
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick() {
        action.setValue(AppTools.OPEN_PRODUCT);
    }
}
