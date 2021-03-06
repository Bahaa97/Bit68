package com.bahaa.mvvm.ui.fragment.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bahaa.mvvm.ui.activities.base.BaseActivity;
import com.bahaa.mvvm.util.MyPreference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class BaseFragment extends Fragment {

    public BaseActivity activity;
    public Gson gson;
    public MyPreference preference;
    public View rootView;
    @Override
    public void onStart() {
        super.onStart();
        activity = getBaseActivity();
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getBaseActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getBaseActivity();
        gson = new GsonBuilder().create();
        preference = new MyPreference(gson);
    }


    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    public BaseActivity getBaseActivity() {
        return BaseActivity.getInstance();
    }

    protected boolean isHasOptionsMenu() {
        return false;
    }



    public void showDialog(String title, String message, String buttonOK, String buttonNO, int image, View.OnClickListener onOkClickListener) {
        activity.showDialog(title, message, buttonOK, buttonNO, image, onOkClickListener);
    }

    public void showDialog(String title, String message) {
        activity.showDialog(title, message);
    }

    public void hideToolbar() {
        activity.hideToolbar();
    }


    public ProgressDialog showProgressDialog() {
        return activity.showProgressDialog();
    }

    public void hideProgressDialog() {
        activity.hideProgressDialog();
    }

    protected void navigate(int layoutId) {
        BaseActivity.getInstance().navigate(layoutId, this);
    }

    protected void navigateWithBundle(View view, Bundle bundle, int actionId) {
        BaseActivity.getInstance().navigateWithBundle(view, bundle, actionId);
    }
    @Override
    public void onResume() {
        super.onResume();
        activity = getBaseActivity();
    }


    public void customBackClick(){
        getBaseActivity().customBackClick();
    }
}
