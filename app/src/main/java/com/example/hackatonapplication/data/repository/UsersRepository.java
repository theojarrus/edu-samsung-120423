package com.example.hackatonapplication.data.repository;

import com.example.hackatonapplication.data.api.users.UsersApiService;
import com.example.hackatonapplication.domain.model.User;

import java.util.List;

import retrofit2.Call;

public class UsersRepository {

    public static Call<List<User>> getUsers() {
        return UsersApiService.getInstance().getUsers();
    }

    public static Call<User> getUser(long id) {
        return UsersApiService.getInstance().getUser(id);
    }

    public static Call<Void> deleteUser(long id) {
        return UsersApiService.getInstance().deleteUser(id);
    }
}
