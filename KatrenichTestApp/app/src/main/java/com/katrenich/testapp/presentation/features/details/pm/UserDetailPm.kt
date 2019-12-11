package com.katrenich.testapp.presentation.features.details.pm

import com.katrenich.testapp.R
import com.katrenich.testapp.common.rxbus.RxBus
import com.katrenich.testapp.core.resources.ResourceProvider
import com.katrenich.testapp.presentation.core.pm.BasePm
import com.katrenich.testapp.presentation.core.rxbus.Clicks
import javax.inject.Inject

class UserDetailPm @Inject constructor(
	private val resources: ResourceProvider,
	private val rxBus: RxBus
) : BasePm() {

	val backAction = Action<Unit>()

	override fun onCreate() {
		super.onCreate()

		lifecycleObservable
			.filter { it == Lifecycle.CREATED }
			.map { resources.getString(R.string.retry_button_title) }
			.subscribe(toolbarTitleState.consumer)
			.untilDestroy()

		backAction.observable
			.doOnNext { RxBus.post(Clicks.BackPressed) }
			.subscribe()
			.untilDestroy()
	}
}