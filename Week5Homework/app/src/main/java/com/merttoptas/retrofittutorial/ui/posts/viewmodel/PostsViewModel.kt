package com.merttoptas.retrofittutorial.ui.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.model.DataState
import com.merttoptas.retrofittutorial.data.model.Post
import com.merttoptas.retrofittutorial.data.model.PostDTO
import com.merttoptas.retrofittutorial.data.repository.Post.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private var _postLiveData = MutableLiveData<DataState<List<PostDTO>?>>()
    val postLiveData: LiveData<DataState<List<PostDTO>?>>
        get() = _postLiveData


   /* private var _postUptadedData = MutableLiveData<List<PostDTO>>()
    val postUptadedData : LiveData<List<PostDTO>>
    get() = _postUptadedData*/


    private val _eventStateLiveData = MutableLiveData<PostViewEvent>()
    val eventStateLiveData: LiveData<PostViewEvent>
        get() = _eventStateLiveData


    init {
        getPosts()
    }


    private fun getPosts() {
        _postLiveData.postValue(DataState.Loading())
        postRepository.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _postLiveData.postValue(DataState.Success(it.map { safePost ->
                            PostDTO(
                                id = safePost.id,
                                title = safePost.title,
                                body = safePost.body,
                                userId = safePost.userId,
                                isFavorite = isExists(safePost.id ?: 0)
                            )
                        }))

                    } ?: kotlin.run {
                        _postLiveData.postValue(DataState.Error("Data Empty"))
                    }
                } else {
                    _postLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                _postLiveData.postValue(DataState.Error(t.message.toString()))
                _eventStateLiveData.postValue(PostViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }

    fun onFavoritePost(post: PostDTO) {
        post.id?.let {
            postRepository.getPostById(it)?.let { PostEntity ->
                PostEntity.postId?.let { postId ->
                    postRepository.deleteFavoritePost(
                        postId
                    )
                }
                updateFavoriteState(post.id,false)
            }
        } ?: kotlin.run {
            postRepository.insertFavoritePost(
                PostEntity(
                    postId = post.id,
                    postTitle = post.title,
                    postBody = post.body,
                )
            )
            updateFavoriteState(post.id, true)
        }
    }

    private fun updateFavoriteState(id:Int?, isFavorite:Boolean) {
        when(val current = _postLiveData.value) {
            is DataState.Success -> {
                val currentList = current.data?.map {
                    if (it.id == id) {
                        it.copy(isFavorite = isFavorite)
                    }
                        else it
                }
                _postLiveData.value = DataState.Success(currentList)
            }
            is DataState.Error -> {}
            is DataState.Loading -> {}

            else -> {}
        }
    }

     fun isFavoriteItem() {
        when(val current = _postLiveData.value) {
            is DataState.Success -> {
                val currentList = current.data?.map {
                    it.copy(isFavorite = isExists(it.id))
                }
               _postLiveData.value = DataState.Success(currentList)
            }
            else -> {}
        }
    }

    private fun isExists(id: Int?): Boolean {
        id.let {
            if (it != null) {
                postRepository.getPostById(it)?.let {
                    return true
                }
            }
        }
        return false
    }


}

sealed class PostViewEvent {
    object NavigateToDetail : PostViewEvent()
    class ShowMessage(val message: String?) : PostViewEvent()
}