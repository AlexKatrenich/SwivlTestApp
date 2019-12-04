package com.katrenich.testapp.presentation.features.users_list.ui

import android.os.Bundle
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.core.ui.BaseFragment
import com.katrenich.testapp.presentation.features.users_list.pm.UsersListPm
import dagger.android.support.AndroidSupportInjection

class UsersListFragment : BaseFragment<UsersListPm>() {

	override val screenLayout: Int = R.layout.fragment_users_list
	override val classToken: Class<UsersListPm> = UsersListPm::class.java

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onCreate(savedInstanceState)
	}

	companion object {
		fun newInstance() = UsersListFragment()
	}
}