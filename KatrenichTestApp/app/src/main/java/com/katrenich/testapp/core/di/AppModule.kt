package com.katrenich.testapp.core.di

import android.content.Context
import com.katrenich.testapp.common.rxbus.RxBus
import com.katrenich.testapp.common.schedulers.IoSchedulersFacade
import com.katrenich.testapp.common.schedulers.SchedulersFacade
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