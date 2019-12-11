package com.katrenich.testapp.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
	@SerializedName("login")
	val login: String,

	@SerializedName("id")
	val id: Long,

	@SerializedName("avatar_url")
	val avatarUrl: String?,

	@SerializedName("name")
	val name: String?,

	@SerializedName("email")
	val email: String?,

	@SerializedName("public_repos")
	val repos: Int,

	@SerializedName("public_gists")
	val gists: Int,

	@SerializedName("followers")
	val followers: Int
)