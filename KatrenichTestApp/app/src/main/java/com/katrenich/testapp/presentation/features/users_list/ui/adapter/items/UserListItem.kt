package com.katrenich.testapp.presentation.features.users_list.ui.adapter.items

import com.katrenich.testapp.presentation.core.ui.adapter.item.ListItem

data class UserListItem(
	val id: String,
	val name: String,
	val pictureRef: String
) : ListItem