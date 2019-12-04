package com.katrenich.testapp.presentation.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.Screen
import com.katrenich.testapp.presentation.core.factory.PmFactory
import com.katrenich.testapp.presentation.core.pm.MainPm
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import me.dmdev.rxpm.base.PmSupportActivity
import javax.inject.Inject

class MainActivity : PmSupportActivity<MainPm>(), HasSupportFragmentInjector {

    @Inject
    lateinit var factory: PmFactory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val classToken = MainPm::class.java

    private val screenLayout = R.layout.activity_main

    private var container: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(screenLayout)
        container = fragmentContainer.id
    }

    override fun onBindPresentationModel(pm: MainPm) {
        pm.navigateCommand.bindTo { navigateToFragment(it) }
    }

    override fun providePresentationModel(): MainPm =
        factory.createPm(classToken)

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        dispatchingAndroidInjector

    private fun navigateToFragment(screen: Screen) {
        supportFragmentManager
            .beginTransaction()
            .add(container, screen.getFragment())
            .addToBackStack(null)
            .commit()
    }
}
