package com.example.hackatonapplication.feature.dashboard.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hackatonapplication.data.repository.UsersRepository;
import com.example.hackatonapplication.domain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<DashboardStatus> _status = new MutableLiveData<>();
    public LiveData<DashboardStatus> status = _status;

    private final MutableLiveData<List<User>> _users = new MutableLiveData<>();
    public LiveData<List<User>> users = _users;

    public void load() {
        _status.setValue(DashboardStatus.LOADING);
        UsersRepository.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                _status.setValue(DashboardStatus.LOADED);
                _users.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable throwable) {
                _status.setValue(DashboardStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
    }
}
