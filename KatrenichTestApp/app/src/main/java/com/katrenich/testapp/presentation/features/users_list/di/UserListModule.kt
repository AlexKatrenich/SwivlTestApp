package com.katrenich.testapp.presentation.features.users_list.di

import androidx.paging.DataSource
import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.datasource.UsersDataSourceFactory
import com.katrenich.testapp.presentation.features.main.mapper.UserDtoToListItemMapper
import com.katrenich.testapp.data.model.UserListDto
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserListModule {

	@Provides
	@Singleton
	fun provideMapper(): Mapper<UserListDto, UserListItem> = UserDtoToListItemMapper()

	@Provides
	@Singleton
	fun provideDataSourceFactory(
		api: GitHubApi,
		mapper: Mapper<UserListDto, UserListItem>
	): DataSource.Factory<Long, UserListItem> = UsersDataSourceFactory(api, mapper)
}