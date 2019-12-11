package com.katrenich.testapp.presentation.features.details.pm

import com.katrenich.testapp.R
import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.core.rxbus.RxBus
import com.katrenich.testapp.core.resources.ResourceProvider
import com.katrenich.testapp.data.datasource.UserDataSource
import com.katrenich.testapp.data.model.UserDto
import com.katrenich.testapp.presentation.core.pm.BasePm
import com.katrenich.testapp.presentation.core.rxbus.Clicks
import com.katrenich.testapp.presentation.features.details.model.User
import javax.inject.Inject

class UserDetailPm @Inject constructor(
	private val resources: ResourceProvider,
	private val mapper: Mapper<UserDto, User>,
	private val dataSource: UserDataSource
) : BasePm() {

	val backAction = Action<Unit>()
	val dataState = State<User>()
	val retryAction = Action<Unit>()

	private var userId = 0L

	private val loadUserByIdAction = Action<Unit>()

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

		loadUserByIdAction.observable.mergeWith(retryAction.observable)
			.map { UserDataSource.Params(userId) }
			.flatMapSingle {
				dataSource.loadInitial(it)
					.bindProgress()
					.map(mapper::mapFromObject)
			}
			.subscribe( { dataState.consumer.accept(it) }, { handleError(it) })
			.untilDestroy()

		retryAction.observable
			.map { Unit }
			.doOnNext(loadUserByIdAction.consumer)
			.subscribe()
			.untilDestroy()
	}

	private fun handleError(throwable: Throwable) {
		throwableState.consumer.accept(true)
	}

	fun setUserId(userId: Long) {
		this.userId = userId
		loadUserByIdAction.consumer.accept(Unit)
	}
}