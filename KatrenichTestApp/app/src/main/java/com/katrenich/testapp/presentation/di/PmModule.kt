package com.katrenich.testapp.presentation.di

import com.katrenich.testapp.common.di.qualifires.PmKey
import com.katrenich.testapp.presentation.core.factory.GeneralPmFactory
import com.katrenich.testapp.presentation.core.factory.PmFactory
import com.katrenich.testapp.presentation.features.details.di.UserDetailModule
import com.katrenich.testapp.presentation.features.details.pm.UserDetailPm
import com.katrenich.testapp.presentation.features.main.pm.MainPm
import com.katrenich.testapp.presentation.features.users_list.di.UserListModule
import com.katrenich.testapp.presentation.features.users_list.pm.UsersListPm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.dmdev.rxpm.PresentationModel

@Module(includes = [
	UserListModule::class,
	UserDetailModule::class
])
abstract class PmModule {

	@Binds
	abstract fun bindPmFactory(factory: GeneralPmFactory): PmFactory

	@Binds
	@IntoMap
	@PmKey(MainPm::class)
	abstract fun bindMainPm(pm: MainPm): PresentationModel

	@Binds
	@IntoMap
	@PmKey(UsersListPm::class)
	abstract fun bindUsersListPm(pm: UsersListPm): PresentationModel

	@Binds
	@IntoMap
	@PmKey(UserDetailPm::class)
	abstract fun bindUserDetailPm(pm: UserDetailPm): PresentationModel
}