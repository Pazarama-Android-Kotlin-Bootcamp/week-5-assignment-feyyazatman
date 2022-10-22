package com.merttoptas.retrofittutorial.data.di


import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import com.merttoptas.retrofittutorial.data.repository.User.UserRepository
import com.merttoptas.retrofittutorial.data.repository.User.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {

    @Provides
    fun provideUserRepository(apiService: ApiService) : UserRepository {
        return UserRepositoryImpl(apiService)
    }
}