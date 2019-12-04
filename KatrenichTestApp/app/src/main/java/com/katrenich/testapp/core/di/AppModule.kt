package com.katrenich.testapp.core.di

import com.katrenich.testapp.common.schedulers.IoSchedulersFacade
import com.katrenich.testapp.common.schedulers.SchedulersFacade
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

	@Provides
	@Singleton
	fun provideSchedulersFacade(): SchedulersFacade = IoSchedulersFacade()
}