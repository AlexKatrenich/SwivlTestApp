package com.katrenich.testapp

import android.content.Context
import com.katrenich.testapp.data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun appModule(module: AppModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)

    fun context(): Context
}