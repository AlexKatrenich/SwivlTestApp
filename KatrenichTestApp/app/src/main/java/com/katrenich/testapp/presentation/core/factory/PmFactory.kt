package com.katrenich.testapp.presentation.core.factory

import me.dmdev.rxpm.PresentationModel

interface PmFactory {
    fun <T: PresentationModel> createPm(modelClass: Class<T>): T
}