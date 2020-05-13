package com.xiangxue.googleproject;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xiangxue.googleproject.base.BaseActivity;
import com.xiangxue.googleproject.databinding.ActivityMainBinding;
import com.xiangxue.googleproject.status.MainActivityViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

// 同学们注意：这是 主页 管理者 三个Fragment
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    public void initWorkspaceAction() {
        mainActivityViewModel = getActivityViewModelProvider(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setVm(mainActivityViewModel);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 处理三个  Fragment
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.navigation_home,
                        R.id.navigation_collect,
                        R.id.navigation_personal)
                        .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
