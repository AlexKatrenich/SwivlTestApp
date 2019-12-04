package com.katrenich.testapp.presentation.di


import com.katrenich.testapp.common.di.scopes.ActivityScope
import com.katrenich.testapp.presentation.features.main.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

	@ActivityScope
	@ContributesAndroidInjector
	abstract fun bindActivity(): MainActivity
}