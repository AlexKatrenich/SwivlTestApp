package com.katrenich.testapp.core.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IoSchedulersFacade : SchedulersFacade {

	override val subscribeOn: Scheduler
		get() = Schedulers.io()

	override val observeOn: Scheduler
		get() = AndroidSchedulers.mainThread()
}