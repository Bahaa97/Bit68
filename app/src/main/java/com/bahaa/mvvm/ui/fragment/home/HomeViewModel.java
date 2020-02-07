package com.bahaa.mvvm.ui.fragment.home;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.data.network.RetrofitClient;
import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    MutableLiveData<List<CategoryResponse>> categoriesResponseMutableLiveData;

    public HomeViewModel() {
        categoriesResponseMutableLiveData = new MutableLiveData<>();
        getCategory();
    }

    @SuppressLint("CheckResult")
    void getCategory() {
        RetrofitClient.webService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    categoriesResponseMutableLiveData.postValue(result);
                }, throwable -> {
                    Log.e("NETWORK ERROR", throwable.getMessage());
                });
    }



    protected void onCleared() {
        super.onCleared();
    }


}