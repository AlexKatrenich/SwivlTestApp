package com.katrenich.testapp.presentation.features.details.mapper

import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.presentation.features.details.model.User
import javax.inject.Inject

class UserDtoToUserMapper @Inject constructor() : Mapper<UserDto, User> {

	override fun mapFromObject(source: UserDto): User =
		with(source) {
			User(
				id = id,
				name = name,
				login = login,
				avatarUrl = avatarUrl,
				email = email,
				reposCount = repos,
				gistsCount = gists,
				followersCount = followers
			)
		}

}