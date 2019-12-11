package com.katrenich.testapp.presentation.features.users_list.pm

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.katrenich.testapp.R
import com.katrenich.testapp.core.resources.ResourceProvider
import com.katrenich.testapp.data.LoadState
import com.katrenich.testapp.data.datasource.UsersDataSourceFactory
import com.katrenich.testapp.presentation.core.pm.BasePm
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import me.dmdev.rxpm.asObservable
import javax.inject.Inject

class UsersListPm @Inject constructor(
	private val resources: ResourceProvider,
	private val sourceFactory: UsersDataSourceFactory
) : BasePm() {

	val retryAction = Action<Unit>()
	val refreshAction = Action<Unit>()

	private val pageSize = 15

	lateinit var usersList: LiveData<PagedList<UserListItem>>

	override fun onCreate() {
		super.onCreate()

		// configure paged list
		val config = PagedList.Config.Builder()
			.setPageSize(pageSize)
			.setEnablePlaceholders(false)
			.setInitialLoadSizeHint(pageSize*3)
			.build()
		usersList = LivePagedListBuilder(sourceFactory, config).build()

		lifecycleObservable
			.filter { it == Lifecycle.CREATED }
			.map { resources.getString(R.string.users_list_screen_toolbar_title) }
			.subscribe(toolbarTitleState.consumer)
			.untilDestroy()

		retryAction.observable
			.doOnNext { throwableState.consumer.accept(false) }
			.doOnNext { sourceFactory.dataSource.retry() }
			.subscribe()
			.untilDestroy()

		refreshAction.observable
			.doOnNext { /*sourceFactory.dataSource.value?.invalidate()*/ }
			.subscribe()
			.untilDestroy()

		lifecycleObservable
			.filter { it == Lifecycle.BINDED }
			.flatMap { sourceFactory.dataSource.throwableHandler }
			.subscribe { throwableState.consumer.accept(true) }
			.untilDestroy()
	}

	fun initialLoadState(): LiveData<LoadState>? =
		sourceFactory.dataSource.initialLoadStateLiveData

	fun paginatedLoadState(): LiveData<LoadState>? =
		sourceFactory.dataSource.initialLoadStateLiveData
}