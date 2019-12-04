package com.katrenich.testapp.common.di.qualifires

import dagger.MapKey
import me.dmdev.rxpm.PresentationModel
import kotlin.reflect.KClass

@Target(
	AnnotationTarget.FUNCTION,
	AnnotationTarget.PROPERTY_GETTER,
	AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class PmKey(val value: KClass<out PresentationModel>)