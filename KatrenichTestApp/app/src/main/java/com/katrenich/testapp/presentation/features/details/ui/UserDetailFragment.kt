package com.katrenich.testapp.presentation.features.details.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

		presentationModel.setUserId(requireArguments()[USER_ID] as Long)
	}

	override fun onBindPresentationModel(pm: UserDetailPm) {
		super.onBindPresentationModel(pm)
		toolbarBackView.clicks().bindTo(pm.backAction)
		retryView.clicks().bindTo(pm.retryAction)
		pm.dataState.bindTo { user ->
			toolbarTitleView.text = user.login
			userNameView.text = user.name
			emailView.text = user.email
			reposCountView.text = user.reposCount.toString()
			gistsCountView.text = user.gistsCount.toString()
			followersCountView.text = user.followersCount.toString()
			val context = context
			context?.let {
				Glide.with(it)
				.load(user.avatarUrl)
				.apply(RequestOptions.circleCropTransform())
				.placeholder(R.drawable.ic_no_image)
				.into(avatarView)
			}
		}
	}

	companion object {
		private const val USER_ID = "USER_ID"

		fun newInstance(id: Long): UserDetailFragment = UserDetailFragment().apply {
			arguments = bundleOf(USER_ID to id)
		}
	}
}