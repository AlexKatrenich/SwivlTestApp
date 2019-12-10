package com.katrenich.testapp.presentation.features.users_list.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem

class UsersDiffUtilCallback : DiffUtil.ItemCallback<UserListItem>() {

	override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
		return oldItem.login == newItem.login
	}

}