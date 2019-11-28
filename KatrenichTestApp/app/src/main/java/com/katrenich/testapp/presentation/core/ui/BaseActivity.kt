package com.katrenich.testapp.presentation.core.ui

import android.os.Bundle
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.core.pm.BasePm
import dagger.android.AndroidInjection
import me.dmdev.rxpm.base.PmSupportActivity

class BaseActivity<T : BasePm> : PmSupportActivity<T>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBindPresentationModel(pm: T) {

    }

    override fun providePresentationModel(): T {
        val pm = factory.createViewModel(classToken)
        return pm
    }
}
