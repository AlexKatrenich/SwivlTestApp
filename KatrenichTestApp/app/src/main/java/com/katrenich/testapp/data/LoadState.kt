package com.katrenich.testapp.data

sealed class LoadState
object Loading : LoadState()
object Success : LoadState()
object LoadingException : LoadState()