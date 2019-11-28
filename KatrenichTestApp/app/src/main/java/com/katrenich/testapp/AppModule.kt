package com.katrenich.testapp

import android.content.Context
import com.katrenich.testapp.common.core.resources.ResourceProvider
import com.katrenich.testapp.common.core.schedulers.IoToMainSchedulersFacade
import com.katrenich.testapp.common.core.schedulers.SchedulersFacade
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideSchedulersFacade(): SchedulersFacade = IoToMainSchedulersFacade()

    @Singleton
    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider = ResourceProvider(context)

}