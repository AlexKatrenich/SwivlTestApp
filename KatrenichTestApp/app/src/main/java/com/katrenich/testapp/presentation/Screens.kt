package com.katrenich.testapp.presentation

import androidx.fragment.app.Fragment
import com.katrenich.testapp.presentation.features.details.ui.UserDetailFragment
import com.katrenich.testapp.presentation.features.users_list.ui.UsersListFragment

object Screens {

	object UsersListScreen : Screen {
		override fun getFragment(): Fragment = UsersListFragment.newInstance()
	}

	data class UserDetailScreen(val id: Long) : Screen {
		override fun getFragment(): Fragment = UserDetailFragment.newInstance(id)
	}
}