package com.bahaa.mvvm.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentHomeBinding;
import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.ui.fragment.home.categoryAdapter.CategoryAdapter;
import com.bahaa.mvvm.ui.fragment.home.categoryAdapter.HomeSliderAdapter;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;
import com.bahaa.mvvm.util.AppUtils;

public class HomeFragment extends BaseFragment implements CategoryAdapter.OnCategoryClick, HomeSliderAdapter.OnCategoryClick {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding fragmentHomeBinding;
    private CategoryAdapter categoryAdapter;
    private HomeSliderAdapter sliderAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        fragmentHomeBinding.setHomeViewModle(homeViewModel);
        AppUtils.initVerticalRV(getContext(),fragmentHomeBinding.recyclerMovies, 2, 5);
        categoryAdapter = new CategoryAdapter(this);
        sliderAdapter = new HomeSliderAdapter(this,getContext());
        fragmentHomeBinding.recyclerMovies.setAdapter(categoryAdapter);
        fragmentHomeBinding.slider.setSliderAdapter(sliderAdapter);
        homeViewModel.categoriesResponseMutableLiveData.observe(getViewLifecycleOwner(), categoryResponses -> {
            categoryAdapter.setList(categoryResponses);
            sliderAdapter.setList(categoryResponses);
        });
        return fragmentHomeBinding.getRoot();

    }

    @Override
    public void onCategoryListener(CategoryResponse category) {
        Bundle bundle = new Bundle();
        bundle.putString("data",gson.toJson(category));
        navigateWithBundle(fragmentHomeBinding.getRoot(),bundle,R.id.action_navHome_to_categoryFragment);
    }
}