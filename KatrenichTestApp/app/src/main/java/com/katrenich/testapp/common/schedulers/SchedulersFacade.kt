package com.katrenich.testapp.common.schedulers

import io.reactivex.Scheduler

interface SchedulersFacade {

	val subscribeOn: Scheduler

	val observeOn: Scheduler
}