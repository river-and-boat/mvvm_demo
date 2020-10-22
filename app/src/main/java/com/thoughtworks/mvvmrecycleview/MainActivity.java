package com.thoughtworks.mvvmrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thoughtworks.mvvmrecycleview.adapters.RecycleViewAdapter;
import com.thoughtworks.mvvmrecycleview.models.NicePlace;
import com.thoughtworks.mvvmrecycleview.viewmodels.MainActivityViewModel;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NicePlace> nicePlaceList = new ArrayList<>();
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private MainActivityViewModel mainActivityViewModel;
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        floatingActionButton = findViewById(R.id.fab);
        initViewModel();
        initRecyclerView();
        initAddNewItem();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new RecycleViewAdapter(this, mainActivityViewModel
                .getNicePlaces().getValue());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getNicePlaces().observe(this, (Observer<List<NicePlace>>)
                nicePlaces -> adapter.notifyDataSetChanged());
        mainActivityViewModel.getIsUpdating().observe(this, aBoolean -> {
            if (aBoolean) {
                showProgressBar();
            } else {
                hideProgressBar();
                recyclerView.smoothScrollToPosition(mainActivityViewModel
                        .getNicePlaces().getValue().size() - 1);
            }
        });
    }

    private void initAddNewItem() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            mainActivityViewModel.addNewItem(
                    new NicePlace("Trondheim",
                            "https://i.redd.it/tpsnoz5bzo501.jpg"));
        });
    }

    private void showProgressBar() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        this.progressBar.setVisibility(View.GONE);
    }
}