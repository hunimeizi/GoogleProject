package com.xiangxue.googleproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.xiangxue.googleproject.DetailLinkActivity;
import com.xiangxue.googleproject.R;
import com.xiangxue.googleproject.base.BaseFragment;
import com.xiangxue.googleproject.data.bean.HomeDataResult;
import com.xiangxue.googleproject.data.config.AppConfigs;
import com.xiangxue.googleproject.databinding.FragmentHomeBinding;
import com.xiangxue.googleproject.request.RequestHomeViewModel;
import com.xiangxue.googleproject.status.HomeViewModel;
import com.xiangxue.googleproject.ui.adapter.HomeInfoListAdapter;

// 首页
public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel; // 首页专用的 ViewModel  Status
    private FragmentHomeBinding binding;
    private RequestHomeViewModel requestHomeViewModel; // 请求的ViewModel Request

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        requestHomeViewModel = new ViewModelProvider(this).get(RequestHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        showActionBar();
        binding = FragmentHomeBinding.bind(root);
        binding.setVm(homeViewModel);
        binding.setClick(new ProxyClick());
        binding.setLifecycleOwner(this);

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 观察数据的变化 ==== 变化到界面中去
        requestHomeViewModel.getHomeDataResultMutableLiveData().observe(getViewLifecycleOwner(), new Observer<HomeDataResult>() {
            @Override
            public void onChanged(HomeDataResult homeDataResult) {
                // String link = homeDataResult.getData().getCompany_list().get(0).getLink();
                // Log.d(AppConfigs.TAG, "requestHomeViewModel observe link:" + link);

                String imagePath1 = homeDataResult.getData().getCompany_list().get(0).getImage();
                // 发生改变  美女图片
                homeViewModel.getImageURL1().setValue(imagePath1);

                String imagePath2 = homeDataResult.getData().getAd_list().get(0).getImage();
                homeViewModel.getImageURL2().setValue(imagePath2);

                String linkPath1 = homeDataResult.getData().getCompany_list().get(0).getLink();
                homeViewModel.getLinkURL1().setValue(linkPath1);

                String linkPath2 = homeDataResult.getData().getAd_list().get(0).getLink();
                homeViewModel.getLinkURL2().setValue(linkPath2);

                //---------------------显示列表--------------------
                binding.industryInformationLv
                        .setAdapter(new HomeInfoListAdapter(getContext(),
                                homeDataResult.getData().getNews_list()));
            }
        });

        // 触发一次
        requestHomeViewModel.touchOffHomeData();
    }

    // 必须写成public
    public class ProxyClick {

        public void toLinkAction1() {
            Toast.makeText(mActivity, "即将发起进入跳转动作1...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), DetailLinkActivity.class);
            intent.putExtra(AppConfigs.URL_KEY, homeViewModel.getLinkURL1().getValue());
            mActivity.startActivity(intent);
        }

        public void toLinkAction2() {
            Toast.makeText(mActivity, "即将发起进入跳转动作2...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), DetailLinkActivity.class);
            intent.putExtra(AppConfigs.URL_KEY, homeViewModel.getLinkURL2().getValue());
            mActivity.startActivity(intent);
        }

    }

    public HomeFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }
}
