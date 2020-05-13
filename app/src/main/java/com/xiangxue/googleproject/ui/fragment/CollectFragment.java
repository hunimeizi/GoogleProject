package com.xiangxue.googleproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xiangxue.googleproject.R;
import com.xiangxue.googleproject.base.BaseFragment;
import com.xiangxue.googleproject.data.database.Student;
import com.xiangxue.googleproject.databinding.FragmentCollectBinding;
import com.xiangxue.googleproject.request.RequestCollectViewModel;
import com.xiangxue.googleproject.status.CollectViewModel;
import com.xiangxue.googleproject.ui.adapter.CollectAdapter;

import java.util.List;

public class CollectFragment extends BaseFragment {

    private CollectViewModel collectViewModel; // 有自己的 ViewModel  Status
    private FragmentCollectBinding binding;
    private RequestCollectViewModel requestCollectViewModel; // 有请求的  ViewModel 仓库
    private CollectAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        collectViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_collect, container, false);
        showActionBar();
        binding = FragmentCollectBinding.bind(root);
        binding.setVm(collectViewModel);
        binding.setClick(new ProxyClick());
        binding.setLifecycleOwner(this);

        adapter = new CollectAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        // requestCollectViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(RequestCollectViewModel.class);
        requestCollectViewModel = ViewModelProviders.of(CollectFragment.this).get(RequestCollectViewModel.class);
        requestCollectViewModel.getStudentsLive().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setAllStudents(students);
                adapter.notifyDataSetChanged();
            }
        });

        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }

    public CollectFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.collect_menu, menu);
    }

    public class ProxyClick {

        public void installDataAction() {
            String[] names = {
                    "乔峰",
                    "段誉",
                    "虚竹",
                    "慕容复",
                    "张三",
                    "李四",
                    "王五",
                    "赵六",
                    "初七",
                    "杜子腾",
                    "王小二",
                    "李大奇"
            };
            int[] ages = {
                    43,
                    24,
                    19,
                    83,
                    64,
                    21,
                    56,
                    32,
                    17,
                    32,
                    45,
                    14
            };
            for(int i = 0;i< names.length;i++) {
                // 触发一次，数据的刷新，改变，全部 采用 数据驱动起来改变
                requestCollectViewModel.touchOffInsertStudents(new Student(names[i], ages[i]));
            }
        }

        public void clearAllDataAction() {
            requestCollectViewModel.touchOffDeleteAllWords();
        }
    }
}
