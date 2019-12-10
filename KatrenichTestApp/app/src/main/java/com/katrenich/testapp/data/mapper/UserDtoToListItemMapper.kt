package com.katrenich.testapp.data.mapper

import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import javax.inject.Inject

class UserDtoToListItemMapper @Inject constructor() : Mapper<UserDto, UserListItem> {

	override fun mapFromObject(source: UserDto): UserListItem =
		with(source) {
			UserListItem(
				id = id.toString(),
				login = login,
				avatarUrl = avatarUrl
			)
		}
}