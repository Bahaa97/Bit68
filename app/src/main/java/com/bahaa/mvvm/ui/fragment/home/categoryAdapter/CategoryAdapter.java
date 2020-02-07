package com.bahaa.mvvm.ui.fragment.home.categoryAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.ItemCategoryBinding;
import com.bahaa.mvvm.models.CategoryResponse;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryResponse> items;
    private OnCategoryClick onCategoryClick;

    public CategoryAdapter(OnCategoryClick onCategoryClick) {
        this.items = new ArrayList<>();
        this.onCategoryClick = onCategoryClick;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCartBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_category,
                        parent, false);
        return new ViewHolder(itemCartBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindCategory(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setList(List<CategoryResponse> items) {
        this.items = new ArrayList<>();
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCategoryBinding itemCategoryBinding;

        public ViewHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
        }


        void bindCategory(final CategoryResponse category) {
            CategoryViewModel productViewModel = new CategoryViewModel(category);

            itemCategoryBinding.setProductItem(productViewModel);

            productViewModel.action.observe((LifecycleOwner) itemView.getContext(), action ->

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
