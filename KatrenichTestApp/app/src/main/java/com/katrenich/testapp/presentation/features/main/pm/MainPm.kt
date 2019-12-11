package com.katrenich.testapp.presentation.features.main.pm

import com.katrenich.testapp.core.rxbus.RxBus
import com.katrenich.testapp.presentation.Screen
import com.katrenich.testapp.presentation.Screens
import com.katrenich.testapp.presentation.core.rxbus.Clicks
import me.dmdev.rxpm.PresentationModel
import javax.inject.Inject

class MainPm @Inject constructor(
	private val rxBus: RxBus
) : PresentationModel() {

	val navigateCommand = Command<Screen>()
	val backCommand = Command<Unit>()

	override fun onCreate() {
		super.onCreate()

		lifecycleObservable.filter { it == Lifecycle.BINDED }
			.map { Unit }
			.subscribe {
				// open usersListScreen when activity started
				navigateCommand.consumer.accept(Screens.UsersListScreen)
			}
			.untilDestroy()

		rxBus.observe(Clicks.UserClicked::class.java)
			.map { it.id }
			.doOnNext { navigateCommand.consumer.accept(Screens.UserDetailScreen(it)) }
			.subscribe()
			.untilUnbind()

		rxBus.observe(Clicks.BackPressed::class.java)
			.map { Unit }
			.doOnNext(backCommand.consumer)
			.subscribe()
			.untilDestroy()
	}
}