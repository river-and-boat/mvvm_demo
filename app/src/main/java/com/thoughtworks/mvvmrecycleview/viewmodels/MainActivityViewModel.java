package com.thoughtworks.mvvmrecycleview.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.mvvmrecycleview.models.NicePlace;
import com.thoughtworks.mvvmrecycleview.repository.NicePlaceRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ArrayList<NicePlace>> nicePlaces;
    private NicePlaceRepository nicePlaceRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init() {
        if (nicePlaces != null) {
            return;
        }
        nicePlaceRepository = NicePlaceRepository.getInstance();
        nicePlaces = nicePlaceRepository.getNicePlaces();
    }

    public LiveData<ArrayList<NicePlace>> getNicePlaces() {
        return nicePlaces;
    }

    public LiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public void addNewItem(final NicePlace nicePlace) {
        isUpdating.setValue(true);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ArrayList<NicePlace> currentPlaces = nicePlaces.getValue();
                currentPlaces.add(nicePlace);
                nicePlaces.postValue(currentPlaces);
                isUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
