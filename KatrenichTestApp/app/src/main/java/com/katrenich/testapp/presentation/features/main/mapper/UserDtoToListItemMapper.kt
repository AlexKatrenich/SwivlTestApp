package com.katrenich.testapp.presentation.features.main.mapper

import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.model.UserListDto
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import javax.inject.Inject

class UserDtoToListItemMapper @Inject constructor() : Mapper<UserListDto, UserListItem> {

	override fun mapFromObject(source: UserListDto): UserListItem =
		with(source) {
			UserListItem(
				id = id,
				login = login,
				avatarUrl = avatarUrl
			)
		}
}