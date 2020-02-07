package com.bahaa.mvvm.ui.fragment.category;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.FragmentCategoryBinding;
import com.bahaa.mvvm.databinding.FragmentProductDetailsBinding;
import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;
import com.bahaa.mvvm.ui.adapter.productAdapter.ProductAdapter;
import com.bahaa.mvvm.util.AppTools;
import com.bahaa.mvvm.util.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment implements ProductAdapter.OnProductClick {
    private CategoryViewModel productDetailsViewModel;
    private FragmentCategoryBinding fragmentCategoryBinding;
    private ProductAdapter productAdapter;
    private CategoryResponse categoryResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        categoryResponse = gson.fromJson(getArguments().getString("data"), CategoryResponse.class);

        productDetailsViewModel = new CategoryViewModel(categoryResponse);
        fragmentCategoryBinding.setCategoryViewModel(productDetailsViewModel);
        initRecycler();
        productDetailsViewModel.action.observe(getViewLifecycleOwner(), action -> {
            switch (action) {
                case AppTools.BACK:
                    customBackClick();
                    break;
            }
        });

        return fragmentCategoryBinding.getRoot();
    }

    private void initRecycler() {
        AppUtils.initVerticalRV(getContext(),fragmentCategoryBinding.recyclerMovies,2,0);
        productAdapter = new ProductAdapter(this);
        fragmentCategoryBinding.recyclerMovies.setAdapter(productAdapter);
        productAdapter.setList(categoryResponse.getProducts());
    }


    @Override
    public void onProductClickListener(Product product) {
        Bundle bundle = new Bundle();
        bundle.putString("data",gson.toJson(product));
        navigateWithBundle(fragmentCategoryBinding.getRoot(),bundle,R.id.action_categoryFragment_to_movieDetailsFragment);

    }
}
