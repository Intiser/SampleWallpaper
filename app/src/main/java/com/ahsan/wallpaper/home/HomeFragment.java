package com.ahsan.wallpaper.home;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahsan.wallpaper.R;
import com.ahsan.wallpaper.adapter.ImageListAdapter;
import com.ahsan.wallpaper.databinding.FragmentHomeBinding;
import com.ahsan.wallpaper.manager.AppActivityManager;
import com.ahsan.wallpaper.manager.AppDataManager;
import com.ahsan.wallpaper.model.HitsItem;
import com.ahsan.wallpaper.model.ImageInfoResponse;
import com.ahsan.wallpaper.home.viewmodel.HomeViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HomeFragment.
     */

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initRecyclerView();
        initViewModel();
        return binding.getRoot();
    }

    private ImageListAdapter adapter;

    private void initRecyclerView() {
        binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new ImageListAdapter(getContext());
        adapter.setCallback(position -> {
            HitsItem item = viewModel.getImageInfoResponseMutableLiveData().getValue().getHits().get(position);
            AppDataManager.getInstance().setSelectedItem(item);
            AppActivityManager.gotoImageActivity(getActivity());
        });
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setItemViewCacheSize(100);
    }

    private HomeViewModel viewModel;

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setViewmodel(viewModel);
        viewModel.getImageInfoResponseMutableLiveData().observe(this, new Observer<ImageInfoResponse>() {
            @Override
            public void onChanged(ImageInfoResponse imageInfoResponse) {
                adapter.setHitsItemArrayList(new ArrayList<>(imageInfoResponse.getHits()));
            }
        });
        viewModel.getImagesFromApi();
    }

}
