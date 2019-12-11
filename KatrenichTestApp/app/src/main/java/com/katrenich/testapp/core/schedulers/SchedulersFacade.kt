package com.katrenich.testapp.core.schedulers

import io.reactivex.Scheduler

interface SchedulersFacade {

	val subscribeOn: Scheduler

	val observeOn: Scheduler
}