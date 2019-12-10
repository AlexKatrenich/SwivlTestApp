package com.katrenich.testapp.common.mapper

interface Mapper<S, D> {

	fun mapFromObject(source: S): D

	fun mapFromObjects(sources: Collection<S>): Collection<D> = sources.map { mapFromObject(it) }
}