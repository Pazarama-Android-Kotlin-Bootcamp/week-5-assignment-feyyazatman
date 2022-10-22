package com.merttoptas.retrofittutorial.data.di

import com.merttoptas.retrofittutorial.data.local.database.PostsDatabase
import com.merttoptas.retrofittutorial.data.repository.Favorite.FavoriteRepository
import com.merttoptas.retrofittutorial.data.repository.Favorite.FavoriteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class FavoritesModule {


    @Provides
    fun provideFavoriteRepository(postsDatabase: PostsDatabase) : FavoriteRepository {
        return FavoriteRepositoryImpl(postsDatabase)
    }
}