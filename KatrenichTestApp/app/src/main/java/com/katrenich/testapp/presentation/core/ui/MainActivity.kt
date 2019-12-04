package com.katrenich.testapp.presentation.core.ui

import android.os.Bundle
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.core.factory.PmFactory
import com.katrenich.testapp.presentation.core.pm.MainPm
import dagger.android.AndroidInjection
import me.dmdev.rxpm.base.PmSupportActivity
import javax.inject.Inject

class MainActivity : PmSupportActivity<MainPm>() {

    @Inject
    lateinit var factory: PmFactory

    private val classToken = MainPm::class.java

    private val screenLayout = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(screenLayout)
    }

    override fun onBindPresentationModel(pm: MainPm) {

    }

    override fun providePresentationModel(): MainPm =
        factory.createPm(classToken)
}
