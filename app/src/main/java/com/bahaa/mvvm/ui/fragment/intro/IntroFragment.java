package com.bahaa.mvvm.ui.fragment.intro;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bahaa.mvvm.R;
import com.bahaa.mvvm.databinding.IntroFragmentBinding;
import com.bahaa.mvvm.databinding.ItemIntroBinding;
import com.bahaa.mvvm.ui.fragment.base.BaseFragment;

import me.relex.circleindicator.CircleIndicator;

public class IntroFragment extends BaseFragment implements IntroAdapter.OnNext {

    private IntroViewModel mViewModel;
    private IntroFragmentBinding introFragmentBinding;
    private IntroAdapter introAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        introFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.intro_fragment, container, false);
        mViewModel = new IntroViewModel();
        CircleIndicator circleIndicator =introFragmentBinding.indicator;

        mViewModel.listMutableLiveData.observe(getViewLifecycleOwner(), introModels -> {
            introAdapter = new IntroAdapter(introModels, this);
            introFragmentBinding.viewPager.setAdapter(introAdapter);
            circleIndicator.setViewPager(introFragmentBinding.viewPager);
        });
        return introFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onGoToNext() {
        new Handler().postDelayed(() -> navigate(R.id.action_introFragment_to_navHome), 1000);
    }
}
