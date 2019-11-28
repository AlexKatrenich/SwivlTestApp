package com.katrenich.testapp.data.di

import com.katrenich.testapp.data.features.api.UsersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesUsersApi(retrofit: Retrofit): UsersApi =
        retrofit.create(UsersApi::class.java)
}