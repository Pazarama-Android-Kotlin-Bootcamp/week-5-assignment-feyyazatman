package com.merttoptas.retrofittutorial.data.repository.Favorite

import com.merttoptas.retrofittutorial.data.local.database.PostsDatabase
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity

class FavoriteRepositoryImpl constructor(
    private val postsDatabase: PostsDatabase
) : FavoriteRepository {
    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().delete(post)
    }

    override fun getPostById(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id)
    }

    override fun getAllFavoritePost(): List<PostEntity> {
        return postsDatabase.postDao().getAllPosts()
    }
}