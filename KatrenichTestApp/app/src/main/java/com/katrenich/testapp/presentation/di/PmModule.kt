package com.katrenich.testapp.presentation.di

import com.katrenich.testapp.common.di.qualifires.PmKey
import com.katrenich.testapp.presentation.core.factory.GeneralPmFactory
import com.katrenich.testapp.presentation.core.factory.PmFactory
import com.katrenich.testapp.presentation.core.pm.MainPm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.dmdev.rxpm.PresentationModel

@Module
abstract class PmModule {

	@Binds
	abstract fun bindPmFactory(factory: GeneralPmFactory): PmFactory

	@Binds
	@IntoMap
	@PmKey(MainPm::class)
	abstract fun bindMainPm(mainPm: MainPm): PresentationModel
}