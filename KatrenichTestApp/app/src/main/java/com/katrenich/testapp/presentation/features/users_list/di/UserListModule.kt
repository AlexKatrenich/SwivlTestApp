package com.katrenich.testapp.presentation.features.users_list.di

import androidx.paging.DataSource
import com.katrenich.testapp.common.di.scopes.FragmentScope
import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.datasource.UsersDataSourceFactory
import com.katrenich.testapp.data.mapper.UserDtoToListItemMapper
import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserListModule {

	@Provides
	@Singleton
	fun provideMapper(): Mapper<UserDto, UserListItem> = UserDtoToListItemMapper()

	@Provides
	@Singleton
	fun provideDataSourceFactory(
		api: GitHubApi,
		mapper: Mapper<UserDto, UserListItem>
	): DataSource.Factory<Long, UserListItem> = UsersDataSourceFactory(api, mapper)
}