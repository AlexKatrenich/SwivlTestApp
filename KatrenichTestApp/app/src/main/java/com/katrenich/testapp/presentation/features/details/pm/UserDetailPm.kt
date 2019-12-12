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
import io.reactivex.Single
import javax.inject.Inject

class UserDetailPm @Inject constructor(
	private val resources: ResourceProvider,
	private val mapper: Mapper<UserDto, User>,
	private val dataSource: UserDataSource
) : BasePm() {

	val backAction = Action<Unit>()
	val dataState = State<User>()
	val retryAction = Action<Unit>()

	private var userId: Long = 0L

	private val loadUserByIdAction = Action<Unit>()

	override fun onCreate() {
		super.onCreate()

		lifecycleObservable
			.filter { it == Lifecycle.CREATED }
			.map { resources.getString(R.string.retry_button_title) }
			.subscribe(toolbarTitleState.consumer)
			.untilDestroy()

		backAction.observable
			.debounceAction()
			.doOnNext { RxBus.post(Clicks.BackPressed) }
			.subscribe()
			.untilDestroy()

		loadUserByIdAction.observable.mergeWith(retryAction.observable)
			.flatMapSingle {
				loadData()
			}
			.subscribe( { }, { handleError(it) })
			.untilDestroy()

		retryAction.observable
			.debounceAction()
			.map { throwableState.consumer.accept(false) }
			.flatMapSingle {
				loadData()
			}
			.subscribe( { }, { handleError(it) })
			.untilDestroy()
	}

	private fun loadData(): Single<User> =
		dataSource.loadInitial(UserDataSource.Params(userId))
			.bindProgress()
			.map(mapper::mapFromObject)
			.doOnSuccess(dataState.consumer)

	private fun handleError(throwable: Throwable) {
		throwableState.consumer.accept(true)
	}

	fun setUserId(userId: Long) {
		this.userId = userId
		loadUserByIdAction.consumer.accept(Unit)
	}
}