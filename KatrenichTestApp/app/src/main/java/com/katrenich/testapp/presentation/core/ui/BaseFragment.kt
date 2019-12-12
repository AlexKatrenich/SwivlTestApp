package com.katrenich.testapp.presentation.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.jakewharton.rxbinding2.view.visibility
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.core.factory.PmFactory
import com.katrenich.testapp.presentation.core.pm.BasePm
import dagger.android.support.AndroidSupportInjection
import me.dmdev.rxpm.base.PmSupportFragment
import javax.inject.Inject

abstract class BaseFragment<T : BasePm> : PmSupportFragment<T>() {

	@Inject
	lateinit var factory: PmFactory

	protected abstract val screenLayout: Int
	protected abstract val classToken: Class<T>

	private var progressView: View? = null
	private var throwableView: View? = null

	override fun onAttach(context: Context) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	@CallSuper
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
		inflater.inflate(screenLayout, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		progressView = view.findViewById(R.id.progressView)
		throwableView = view.findViewById(R.id.throwableView)
	}

	@CallSuper
	override fun onBindPresentationModel(pm: T) {
		progressView?.let { view -> pm.progressState.bindTo(view.visibility()) }
		throwableView?.let { view -> pm.throwableState.bindTo(view.visibility()) }
	}

	override fun providePresentationModel(): T = factory.createPm(classToken)
}