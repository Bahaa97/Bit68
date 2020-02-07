package com.bahaa.mvvm.ui.fragment.intro;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.IntroModel;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;
import com.squareup.picasso.Picasso;

public class ItemIntroViewModel extends ViewModel {
    private IntroModel introModel;
    public ObservableField<String> imageObservable;
    public ObservableField<String> nameObservable;
    public ObservableField<String> descriptionObservable;
    public MutableLiveData<String> action;

    public ItemIntroViewModel(IntroModel introModel) {
        this.introModel = introModel;
        imageObservable = new ObservableField<>();
        nameObservable = new ObservableField<>();
        descriptionObservable = new ObservableField<>();
        action = new MutableLiveData<>();
        updateUI();
    }

    private void updateUI() {
        imageObservable.set(introModel.getImage());
        nameObservable.set(introModel.getTitle());
        descriptionObservable.set(introModel.getDescription());
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

}
