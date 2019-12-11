package com.katrenich.testapp.core.di

import android.content.Context
import com.katrenich.testapp.core.rxbus.RxBus
import com.katrenich.testapp.core.schedulers.IoSchedulersFacade
import com.katrenich.testapp.core.schedulers.SchedulersFacade
import com.katrenich.testapp.core.resources.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

	@Provides
	@Singleton
	fun provideSchedulersFacade(): SchedulersFacade = IoSchedulersFacade()

	@Singleton
	@Provides
	fun provideResourceProvider(context: Context): ResourceProvider = ResourceProvider(context)

	@Singleton
	@Provides
	fun provideRxBus(): RxBus = RxBus
}