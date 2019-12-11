package com.katrenich.testapp.presentation.features.details.model

class User (
	val id: Long,
	val name: String?,
	val login: String,
	val avatarUrl: String?,
	val email: String?,
	val reposCount: Int,
	val gistsCount: Int,
	val followersCount: Int
)