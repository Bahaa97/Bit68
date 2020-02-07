package com.bahaa.mvvm.ui.fragment.productDetails;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentProductDetailsBinding;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.ui.adapter.productAdapter.ProductAdapter;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;
import com.bahaa.mvvm.util.AppTools;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends BaseFragment {
    private ProductDetailsViewModel productDetailsViewModel;
    private FragmentProductDetailsBinding fragmentProductDetailsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProductDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false);
        productDetailsViewModel = new ProductDetailsViewModel(gson.fromJson(getArguments().getString("data"),Product.class));
        fragmentProductDetailsBinding.setDetailsViewModel(productDetailsViewModel);
        productDetailsViewModel.action.observe(getViewLifecycleOwner(), action -> {
            switch (action) {
                case AppTools.BACK:
                    customBackClick();
                    break;
            }
        });

        return fragmentProductDetailsBinding.getRoot();
    }


}
