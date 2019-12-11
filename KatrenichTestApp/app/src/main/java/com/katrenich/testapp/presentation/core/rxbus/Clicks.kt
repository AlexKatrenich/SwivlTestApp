package com.katrenich.testapp.presentation.core.rxbus

sealed class Clicks {
	data class UserClicked(val id: Long): Clicks()
	object BackPressed : Clicks()
}