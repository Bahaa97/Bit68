package com.bahaa.mvvm.ui.fragment.home.categoryAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.ItemCategoryBinding;
import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.util.AppTools;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeSliderAdapter extends SliderViewAdapter<HomeSliderAdapter.SliderAdapterVH> {

    List<CategoryResponse> images;
    OnCategoryClick onCategoryClick;
    Context context ;
    public HomeSliderAdapter(OnCategoryClick onCategoryClick, Context context) {
        this.images = new ArrayList<>();
        this.onCategoryClick = onCategoryClick;
        this.context = context;
    }

    public void setList(List<CategoryResponse> items) {
        this.images = new ArrayList<>();
        this.images = items;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        ItemCategoryBinding itemCartBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_category,
                        parent, false);
        return new HomeSliderAdapter.SliderAdapterVH(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.bindCategory(images.get(position));
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return images.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private ItemCategoryBinding itemCategoryBinding;
        public SliderAdapterVH(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
        }
        void bindCategory(final CategoryResponse category) {
            CategoryViewModel productViewModel = new CategoryViewModel(category);

            itemCategoryBinding.setProductItem(productViewModel);

            productViewModel.action.observe((LifecycleOwner)context , action ->

            {
                switch (action) {
                    case AppTools.OPEN_PRODUCT:
                        onCategoryClick.onCategoryListener(category);
                        break;
                }
            });

        }
    }

    public interface OnCategoryClick {
        void onCategoryListener(CategoryResponse category);
    }
}