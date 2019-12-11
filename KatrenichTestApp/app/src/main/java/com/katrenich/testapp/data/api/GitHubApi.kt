package com.katrenich.testapp.data.api

import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.data.model.UserListDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

	@GET("/users")
	fun getUsers(@Query("since") userId: Long, @Query("per_page") perPage: Int): Single<List<UserListDto>>

	@GET("/user/{id}")
	fun getUserById(@Path("id") userId: Long): Single<UserDto>
}