package com.katrenich.testapp.presentation.core.factory


import me.dmdev.rxpm.PresentationModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * General factory for presenters
 * */
@Suppress("UNCHECKED_CAST")
@Singleton
class GeneralPmFactory @Inject constructor(
    private val providers: Map<Class<out PresentationModel>, @JvmSuppressWildcards Provider<PresentationModel>>
) : PmFactory {

    override fun <T : PresentationModel> createPm(modelClass: Class<T>): T {
        val provider = providers[modelClass]
        return when {
            provider != null -> provider.get() as T
            else -> throw NoSuchElementException()
        }
    }
}