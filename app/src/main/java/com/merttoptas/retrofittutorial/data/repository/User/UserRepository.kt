package com.merttoptas.retrofittutorial.data.repository.User

import com.merttoptas.retrofittutorial.data.model.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>
}