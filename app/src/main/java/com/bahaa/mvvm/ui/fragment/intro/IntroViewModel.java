package com.bahaa.mvvm.ui.fragment.intro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.models.IntroModel;

import java.util.ArrayList;
import java.util.List;

public class IntroViewModel extends ViewModel {
    public MutableLiveData<List<IntroModel>> listMutableLiveData;

    public IntroViewModel() {
        this.listMutableLiveData = new MutableLiveData<>();
        updateData();
    }

    private void updateData() {
        List<IntroModel> introModels = new ArrayList<>();
        IntroModel introModel = new IntroModel();
        introModel.setTitle("Title 1");
        introModel.setDescription("Description 1");
        introModel.setImage("https://gazef.s3.us-west-2.amazonaws.com/task-assets/Fresh-fruit-pretty.jpg.653x0_q80_crop-smart.jpg");

        IntroModel introModel2 = new IntroModel();
        introModel2.setTitle("Title 2");
        introModel2.setDescription("Description 2");
        introModel2.setImage("https://gazef.s3.us-west-2.amazonaws.com/task-assets/Mixed-Meat-Small.jpg");

        IntroModel introModel3 = new IntroModel();
        introModel3.setTitle("Title 3");
        introModel3.setDescription("Description 3");
        introModel3.setImage("https://gazef.s3.us-west-2.amazonaws.com/task-assets/Catalan-fish-stew-Suquet-de-peix-recipe2.jpg");

        introModels.add(introModel);
        introModels.add(introModel2);
        introModels.add(introModel3);

        listMutableLiveData.setValue(introModels);
    }

}
