package com.katrenich.testapp.presentation.core.pm

import com.katrenich.testapp.presentation.Screen
import com.katrenich.testapp.presentation.Screens
import me.dmdev.rxpm.PresentationModel
import javax.inject.Inject

class MainPm @Inject constructor() : PresentationModel() {

	val navigateCommand = Command<Screen>()

	override fun onCreate() {
		super.onCreate()

		lifecycleObservable.filter { it == Lifecycle.BINDED }
			.map { Unit }
			.subscribe {
				// open usersListScreen when activity started
				navigateCommand.consumer.accept(Screens.UsersListScreen)
			}
			.untilDestroy()
	}

}