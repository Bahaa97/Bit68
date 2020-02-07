package com.bahaa.mvvm.data.network.interfaces;

import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.util.AppTools;
import com.bahaa.mvvm.util.AppUtils;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET(AppTools.Network.CATEGORIES)
    Observable<List<CategoryResponse>> getCategories();



}
