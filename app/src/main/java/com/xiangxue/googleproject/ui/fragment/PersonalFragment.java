package com.xiangxue.googleproject.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiangxue.googleproject.R;
import com.xiangxue.googleproject.base.BaseFragment;
import com.xiangxue.googleproject.databinding.FragmentPersonalBinding;
import com.xiangxue.googleproject.status.PersonalViewModel;

// 个人中心
public class PersonalFragment extends BaseFragment {

    private PersonalViewModel personalViewModel; // 自身的 ViewModel
    private FragmentPersonalBinding binding;
    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personal, container, false);

        binding = FragmentPersonalBinding.bind(root);
        binding.setVm(personalViewModel);
        binding.setLifecycleOwner(this);

        mContext = getActivity();

        return root;
    }

    private void showNormalMoreButtonDialog(){
        AlertDialog.Builder normalMoreButtonDialog = new AlertDialog.Builder(getActivity());
        // normalMoreButtonDialog.setTitle(getString(R.string.dialog_normal_more_button_text));
        normalMoreButtonDialog.setIcon(R.mipmap.ic_launcher_round);
        normalMoreButtonDialog.setMessage("您确定要退出账号吗？");

        //设置按钮
        normalMoreButtonDialog.setPositiveButton("确定"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 没有登录
                        binding.userLoginTv.setVisibility(View.VISIBLE);
                        binding.userLoginedLl.setVisibility(View.GONE);

                        // 判断用户是否登录，如果登录了显示登录的中心头部，否则显示未登录的中心头部
                        SharedPreferences sp = mContext.getSharedPreferences("info",Context.MODE_PRIVATE);
                        sp.edit().putBoolean("is_login", false).commit();

                        showActionBar();
                        binding.tvExitLogin.setVisibility(View.GONE);

                        dialog.dismiss();
                    }
                });
        normalMoreButtonDialog.setNegativeButton("取消"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalMoreButtonDialog.create().show();
    }

    public PersonalFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.personal_menu, menu);
    }
}
