package com.katrenich.testapp.data.datasource

import androidx.paging.ItemKeyedDataSource
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.model.UserDto
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.MutableLiveData
import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.LoadState
import com.katrenich.testapp.data.Loading
import com.katrenich.testapp.data.Success
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem

class UsersDataSource(
	private val api: GitHubApi,
	private val usersMapper: Mapper<UserDto, UserListItem>
) : ItemKeyedDataSource<Long, UserListItem>() {

	private val compositeDisposable = CompositeDisposable()

	val paginatedNetworkStateLiveData: MutableLiveData<LoadState> = MutableLiveData()
	val initialLoadStateLiveData: MutableLiveData<LoadState> = MutableLiveData()

	override fun loadInitial(
		params: LoadInitialParams<Long>,
		callback: LoadInitialCallback<UserListItem>
	) {
		initialLoadStateLiveData.postValue(Loading)
		compositeDisposable.add(
			api.getUsers(1, params.requestedLoadSize)
				.map(usersMapper::mapFromObjects)
				.map { it.toList() }
				.retry()
				.subscribe { users ->
					initialLoadStateLiveData.postValue(Success)
					callback.onResult(users)
				}
		)
	}

	override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {
		paginatedNetworkStateLiveData.postValue(Loading)
		compositeDisposable.add(
			api.getUsers(params.key, params.requestedLoadSize)
			.map(usersMapper::mapFromObjects)
			.map { it.toList() }
			.retry()
			.subscribe { users ->
				paginatedNetworkStateLiveData.postValue(Success)
				callback.onResult(users)
			}
		)
	}

	override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {
		// ignored, since we only ever append to our initial load
	}

	override fun getKey(item: UserListItem): Long =
		item.id.toLong()
}