package com.katrenich.testapp.data.features.api

import io.reactivex.Completable
import retrofit2.http.GET

interface UsersApi {

    @GET
    fun getUsers(): Completable

    @GET
    fun getUserDetails(): Completable
}