package com.katrenich.testapp.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersDataSourceFactory @Inject constructor (
	private val api: GitHubApi,
	private val usersMapper: Mapper<UserDto, UserListItem>
) : DataSource.Factory<Long, UserListItem>() {

	val dataSource = UsersDataSource(api, usersMapper)

	override fun create(): DataSource<Long, UserListItem> =
		dataSource
}