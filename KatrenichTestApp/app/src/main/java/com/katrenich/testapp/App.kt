package com.katrenich.testapp

import android.app.Activity
import android.app.Application
import com.katrenich.testapp.core.di.AppModule
import com.katrenich.testapp.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>? =
        dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        DaggerAppComponent.builder()
            .context(this)
            .appModule(AppModule())
            .build()
            .inject(this)
    }
}