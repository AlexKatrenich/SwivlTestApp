package com.katrenich.testapp.core.di

import android.app.Application
import android.content.Context
import com.katrenich.testapp.App
import com.katrenich.testapp.presentation.di.ActivityBuilder
import com.katrenich.testapp.presentation.di.PmModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PmModule::class,
    AppModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}