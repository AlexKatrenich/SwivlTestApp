package com.katrenich.testapp.presentation.features.details.ui

import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.core.ui.BaseFragment
import com.katrenich.testapp.presentation.features.details.pm.UserDetailPm
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment : BaseFragment<UserDetailPm>() {

	override val screenLayout: Int = R.layout.fragment_user_detail
	override val classToken: Class<UserDetailPm> = UserDetailPm::class.java

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onCreate(savedInstanceState)

	}

	override fun onBindPresentationModel(pm: UserDetailPm) {
		super.onBindPresentationModel(pm)
		toolbarBackView.clicks().bindTo(pm.backAction)
	}

	companion object {
		fun newInstance(id: Long): UserDetailFragment {

			return UserDetailFragment()
		}
	}
}