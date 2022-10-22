package com.merttoptas.retrofittutorial.data.repository.Post

import com.merttoptas.retrofittutorial.data.local.database.PostsDatabase
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import com.merttoptas.retrofittutorial.data.model.Post
import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.data.repository.Post.PostRepository
import retrofit2.Call

/**
 * Created by merttoptas on 16.10.2022.
 */

class PostRepositoryImpl constructor(
    private val apiService: ApiService,
    private val postsDatabase: PostsDatabase
) : PostRepository {
    override fun getPosts(): Call<List<Post>> {
        return apiService.getPosts()
    }

    override fun getPostById(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id)
    }

    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(id: String) {
        return postsDatabase.postDao().deleteFavoriteById(id)
    }
}