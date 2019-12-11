package com.katrenich.testapp.presentation.core.pm

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Consumer
import me.dmdev.rxpm.PresentationModel

abstract class BasePm : PresentationModel() {

	val progressState = State(false)
	val toolbarTitleState = State<String>()
	val throwableState = State<Boolean>()

	/**
	 * Extension functions to add progress visibility handling.
	 * */
	protected fun <T> Observable<T>.bindProgress(): Observable<T> =
		this.bindProgress(progressState.consumer)

	protected fun <T> Single<T>.bindProgress(): Single<T> =
		this.bindProgress(progressState.consumer)

	protected fun Completable.bindProgress(): Completable =
		this.bindProgress(progressState.consumer)

	private fun <T> Observable<T>.bindProgress(progressConsumer: Consumer<Boolean>): Observable<T> {
		return this
			.doOnSubscribe { progressConsumer.accept(true) }
			.doFinally { progressConsumer.accept(false) }
	}

	private fun <T> Single<T>.bindProgress(progressConsumer: Consumer<Boolean>): Single<T> {
		return this
			.doOnSubscribe { progressConsumer.accept(true) }
			.doFinally { progressConsumer.accept(false) }
	}

	private fun Completable.bindProgress(progressConsumer: Consumer<Boolean>): Completable {
		return this
			.doOnSubscribe { progressConsumer.accept(true) }
			.doFinally { progressConsumer.accept(false) }
	}
}


