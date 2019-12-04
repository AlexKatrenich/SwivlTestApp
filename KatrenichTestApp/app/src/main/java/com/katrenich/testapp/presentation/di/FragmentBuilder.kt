package com.katrenich.testapp.presentation.di

import com.katrenich.testapp.common.di.scopes.FragmentScope
import com.katrenich.testapp.presentation.features.users_list.ui.UsersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun bindUsersListFragment(): UsersListFragment
}