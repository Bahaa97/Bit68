package com.bahaa.mvvm.ui.fragment.intro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.ItemIntroBinding;
import com.bahaa.mvvm.databinding.ItemProductBinding;
import com.bahaa.mvvm.models.IntroModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class IntroAdapter extends SliderViewAdapter<IntroAdapter.SliderAdapterVH> {

    private List<IntroModel> items;
    private OnNext onNext;

    public IntroAdapter(List<IntroModel> images,OnNext onNext) {
        this.items = images;
        this.onNext = onNext;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        ItemIntroBinding viewModel = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_intro, parent, false);

        return new SliderAdapterVH(viewModel);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.pindData(items.get(position));
        if (position == items.size()-1){
            onNext.onGoToNext();
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ItemIntroBinding itemIntroBinding;

        SliderAdapterVH(ItemIntroBinding itemView) {
            super(itemView.getRoot());
            itemIntroBinding = itemView;
        }

        void pindData(IntroModel introModel) {
            ItemIntroViewModel viewModel = new ItemIntroViewModel(introModel);
            itemIntroBinding.setIntroItem(viewModel);
        }

    }
    public interface OnNext{
        void onGoToNext();
    }


}