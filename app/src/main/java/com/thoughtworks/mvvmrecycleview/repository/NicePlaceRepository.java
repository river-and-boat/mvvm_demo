package com.thoughtworks.mvvmrecycleview.repository;

import androidx.lifecycle.MutableLiveData;

import com.thoughtworks.mvvmrecycleview.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlaceRepository {

    private static NicePlaceRepository nicePlaceRepository;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepository getInstance() {
        if (nicePlaceRepository == null) {
            nicePlaceRepository = new NicePlaceRepository();
        }
        return nicePlaceRepository;
    }

    public MutableLiveData<ArrayList<NicePlace>> getNicePlaces() {
        setNicePlaces();
        MutableLiveData<ArrayList<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces() {
        dataSet.add(new NicePlace("Trondheim", "https://i.redd.it/tpsnoz5bzo501.jpg"));
    }
}
