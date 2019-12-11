package com.katrenich.testapp.data.datasource

import androidx.paging.ItemKeyedDataSource
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.model.UserDto
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.MutableLiveData
import com.jakewharton.rxrelay2.PublishRelay
import com.katrenich.testapp.common.mapper.Mapper
import com.katrenich.testapp.data.LoadState
import com.katrenich.testapp.data.Loading
import com.katrenich.testapp.data.LoadingException
import com.katrenich.testapp.data.Success
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class UsersDataSource(
	private val api: GitHubApi,
	private val usersMapper: Mapper<UserDto, UserListItem>
) : ItemKeyedDataSource<Long, UserListItem>() {

	val throwableHandler = PublishRelay.create<LoadingException>()

	val paginatedNetworkStateLiveData: MutableLiveData<LoadState> = MutableLiveData()
	val initialLoadStateLiveData: MutableLiveData<LoadState> = MutableLiveData()

	private var retryCompletable: Completable? = null

	private lateinit var params: LoadParams<Long>
	private lateinit var callback: LoadCallback<UserListItem>

	private val compositeDisposable = CompositeDisposable()

	override fun loadInitial(
		params: LoadInitialParams<Long>,
		callback: LoadInitialCallback<UserListItem>
	) {
		initialLoadStateLiveData.postValue(Loading)
		compositeDisposable.add(
			api.getUsers(1, params.requestedLoadSize)
				.map(usersMapper::mapFromObjects)
				.map { it.toList() }
				.subscribe( { users ->
					initialLoadStateLiveData.postValue(Success)
					callback.onResult(users)
					setRetry(null)
				}, {
					throwableHandler.accept(LoadingException)
					setRetry(Action { loadInitial(params, callback) })
				})
		)
	}

	override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {
		this.params = params
		this.callback = callback
		paginatedNetworkStateLiveData.postValue(Loading)
		compositeDisposable.add(
			api.getUsers(params.key, params.requestedLoadSize)
			.map(usersMapper::mapFromObjects)
			.map { it.toList() }
			.subscribe ( { users ->
				paginatedNetworkStateLiveData.postValue(Success)
				callback.onResult(users)
			}, {
				paginatedNetworkStateLiveData.postValue(LoadingException)
			})
		)
	}


	override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserListItem>) {
		// ignored, since we only ever append to our initial load
	}

	override fun getKey(item: UserListItem): Long =
		item.id

	fun retry() {
		if (retryCompletable != null) {
			compositeDisposable.add(retryCompletable!!
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({ }, { }))
		}
	}

	private fun setRetry(action: Action?) {
		if (action == null) {
			this.retryCompletable = null
		} else {
			this.retryCompletable = Completable.fromAction(action)
		}
	}
}