package com.example.hackatonapplication.feature.profile.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hackatonapplication.data.repository.UsersRepository;
import com.example.hackatonapplication.domain.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<ProfileStatus> _status = new MutableLiveData<>();
    public LiveData<ProfileStatus> status = _status;

    private final MutableLiveData<User> _user = new MutableLiveData<>();
    public LiveData<User> user = _user;

    public void load(long id) {
        _status.setValue(ProfileStatus.LOADING);
        UsersRepository.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                _status.setValue(ProfileStatus.LOADED);
                _user.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable throwable) {
                _status.setValue(ProfileStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
    }

    public void delete(long id) {
        _status.setValue(ProfileStatus.LOADING);
        UsersRepository.deleteUser(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                _status.setValue(ProfileStatus.DELETED);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable throwable) {
                _status.setValue(ProfileStatus.FAILURE);
                throwable.printStackTrace();
            }
        });
    }
}
