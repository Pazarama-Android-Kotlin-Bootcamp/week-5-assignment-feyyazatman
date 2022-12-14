package com.merttoptas.retrofittutorial.ui.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.model.PostDTO
import com.merttoptas.retrofittutorial.data.repository.Favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private var _favoritePostLiveData = MutableLiveData<List<PostDTO>>()
    val favoritePostLiveData : LiveData<List<PostDTO>>
    get() = _favoritePostLiveData


     fun getAllFavoritePost()  {
       _favoritePostLiveData.postValue(favoriteRepository.getAllFavoritePost().map { PostEntity ->
           PostDTO(
               id = PostEntity.postId,
               userId = null,
               isFavorite = false,
               body = PostEntity.postBody ,
               title = PostEntity.postTitle
           )
       })
    }

    fun onDeletePost(post: PostDTO) {
        post.id?.let {
            favoriteRepository.getPostById(it)?.let { PostEntity ->
                favoriteRepository.deleteFavoritePost(
                    PostEntity
                )
            }
            getAllFavoritePost()
        }
    }




}