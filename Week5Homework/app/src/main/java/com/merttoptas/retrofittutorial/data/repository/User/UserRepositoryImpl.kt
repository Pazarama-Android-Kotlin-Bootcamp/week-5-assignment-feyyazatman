package com.merttoptas.retrofittutorial.data.repository.User

import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import retrofit2.Call

class UserRepositoryImpl constructor(
    private val apiService: ApiService
) : UserRepository {
    override fun getUsers(): Call<List<Users>> {
        return apiService.getUsers()
    }
}