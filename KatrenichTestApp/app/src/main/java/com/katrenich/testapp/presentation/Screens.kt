package com.katrenich.testapp.presentation

import androidx.fragment.app.Fragment
import com.katrenich.testapp.presentation.features.users_list.ui.UsersListFragment

object Screens {

	object UsersListScreen : Screen {
		override fun getFragment(): Fragment = UsersListFragment.newInstance()
	}
}