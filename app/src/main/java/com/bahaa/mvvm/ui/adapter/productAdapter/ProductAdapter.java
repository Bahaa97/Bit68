package com.bahaa.mvvm.ui.adapter.productAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.ItemProductBinding;
import com.bahaa.mvvm.models.Product;
import com.bahaa.mvvm.util.AppTools;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> cartList;
    private OnProductClick onProductClick;

    public ProductAdapter(OnProductClick onProductClick) {
        this.cartList = new ArrayList<>();
        this.onProductClick = onProductClick;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding itemCartBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product,
                        parent, false);
        return new ViewHolder(itemCartBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindPeople(cartList.get(position));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public void setList(List<Product> cartList) {
        this.cartList = new ArrayList<>();
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProductBinding itemProductBinding;

        public ViewHolder(ItemProductBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemProductBinding = itemMovieBinding;
        }


        void bindPeople(final Product product) {
            ProductViewModel productViewModel = new ProductViewModel(product);

            itemProductBinding.setProductItem(productViewModel);


            productViewModel.action.observe((LifecycleOwner) itemView.getContext(), action -> {
                switch (action) {
                    case AppTools.OPEN_PRODUCT:
                        onProductClick.onProductClickListener(product);
                        break;
                }
            });

        }
    }

    public interface OnProductClick {
        void onProductClickListener(Product product);
    }
}
