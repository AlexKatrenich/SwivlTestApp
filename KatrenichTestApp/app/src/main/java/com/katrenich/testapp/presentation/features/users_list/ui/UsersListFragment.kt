package com.katrenich.testapp.presentation.features.users_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.core.factory.PmFactory
import com.katrenich.testapp.presentation.features.users_list.pm.UsersListPm
import dagger.android.support.AndroidSupportInjection
import me.dmdev.rxpm.base.PmSupportFragment
import javax.inject.Inject

class UsersListFragment : PmSupportFragment<UsersListPm>() {

	@Inject
	lateinit var factory: PmFactory

	private val screenLayout = R.layout.fragment_users_list
	private val classToken = UsersListPm::class.java

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? =
		inflater.inflate(screenLayout, container, false)

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onCreate(savedInstanceState)
	}

	override fun onBindPresentationModel(pm: UsersListPm) {

	}

	override fun providePresentationModel(): UsersListPm =
		factory.createPm(classToken)

	companion object {
		fun newInstance() = UsersListFragment()
	}
}