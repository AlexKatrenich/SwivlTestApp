package com.katrenich.testapp.presentation.features.details.di

import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.common.schedulers.SchedulersFacade
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.datasource.UserDataSource
import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.presentation.features.details.mapper.UserDtoToUserMapper
import com.katrenich.testapp.presentation.features.details.model.User
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDetailModule {

	@Provides
	fun provideUserDataSource(api: GitHubApi, schedulersFacade: SchedulersFacade): UserDataSource
		= UserDataSource(api, schedulersFacade)

	@Provides
	@Singleton
	fun provideMapper(): Mapper<UserDto, User> = UserDtoToUserMapper()
}