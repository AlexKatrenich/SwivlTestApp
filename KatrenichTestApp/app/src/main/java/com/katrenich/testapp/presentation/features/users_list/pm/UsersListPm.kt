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
import javax.inject.Inject

class UsersListPm @Inject constructor(
	private val resources: ResourceProvider,
	private val sourceFactory: UsersDataSourceFactory
) : BasePm() {

	private val pageSize = 15

	lateinit var usersList: LiveData<PagedList<UserListItem>>

	override fun onCreate() {
		super.onCreate()

		val config = PagedList.Config.Builder()
			.setPageSize(pageSize)
			.setEnablePlaceholders(false)
			.setInitialLoadSizeHint(40)
			.build()
		usersList = LivePagedListBuilder(sourceFactory, config).build()

		lifecycleObservable
			.filter { it == Lifecycle.CREATED }
			.map { resources.getString(R.string.users_list_screen_toolbar_title) }
			.subscribe(toolbarTitleState.consumer)
			.untilDestroy()
	}

	fun getUsers(): LiveData<PagedList<UserListItem>> = usersList

	fun initialLoadState(): LiveData<LoadState> =
		sourceFactory.dataSource.initialLoadStateLiveData

	fun paginatedLoadState(): LiveData<LoadState> =
		sourceFactory.dataSource.paginatedNetworkStateLiveData
}