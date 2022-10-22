package com.merttoptas.retrofittutorial.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.model.DataState
import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.data.repository.Post.PostRepository
import com.merttoptas.retrofittutorial.data.repository.User.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private var _userLiveData = MutableLiveData<DataState<List<Users>>>()
    val userLiveData : LiveData<DataState<List<Users>>>
    get() = _userLiveData


    init {
        getUsers()
    }

    private fun getUsers() {
        userRepository.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _userLiveData.postValue(DataState.Success(it))
                    } ?: kotlin.run {
                        _userLiveData.postValue(DataState.Error("Data is Empty"))
                    }
                } else {
                    _userLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _userLiveData.postValue(DataState.Error(t.message.toString()))
            }

        })
    }
}