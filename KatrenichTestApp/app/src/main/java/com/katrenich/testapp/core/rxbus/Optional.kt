package com.katrenich.testapp.core.rxbus

/**
 * Copy from <a href="https://github.com/adiliqbl/RxBus" >
 */
class Optional<T> {

	private var value: T? = null

	val isPresent: Boolean
		get() = value != null

	private constructor() {
		this.value = null
	}

	private constructor(value: T) {
		this.value = value
	}

	fun get(): T? {
		return value
	}

	companion object {

		fun <T> empty(): Optional<T> {
			return Optional()
		}

		fun <T> of(value: T): Optional<T> {
			return Optional(value)
		}
	}
}