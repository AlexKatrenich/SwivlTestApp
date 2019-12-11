package com.katrenich.testapp.data.datasource

import com.katrenich.testapp.core.schedulers.SchedulersFacade
import com.katrenich.testapp.data.api.GitHubApi
import com.katrenich.testapp.data.model.UserDto
import io.reactivex.Single
import javax.inject.Inject

class UserDataSource @Inject constructor(
	private val api: GitHubApi,
	private val schedulers: SchedulersFacade
) {

	fun loadInitial(params: Params): Single<UserDto> =
		api.getUserById(params.id)
			.subscribeOn(schedulers.subscribeOn)
			.observeOn(schedulers.observeOn)

	data class Params(val id: Long)
}