package com.katrenich.testapp.presentation.features.users_list.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.katrenich.testapp.R
import com.katrenich.testapp.data.LoadState
import com.katrenich.testapp.data.Loading
import com.katrenich.testapp.data.Success
import com.katrenich.testapp.presentation.core.ui.BaseFragment
import com.katrenich.testapp.presentation.features.users_list.pm.UsersListPm
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.UsersDiffUtilCallback
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.UsersListAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_users_list.*

class UsersListFragment : BaseFragment<UsersListPm>() {

	override val screenLayout: Int = R.layout.fragment_users_list
	override val classToken: Class<UsersListPm> = UsersListPm::class.java

	lateinit var usersListAdapter: UsersListAdapter
	lateinit var itemsView: RecyclerView

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onCreate(savedInstanceState)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		itemsView = view.findViewById(R.id.itemsView)
		// init adapter
		val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
		usersListAdapter = UsersListAdapter(UsersDiffUtilCallback())
		itemsView.layoutManager = layoutManager
		itemsView.adapter = usersListAdapter
	}

	override fun onBindPresentationModel(pm: UsersListPm) {
		super.onBindPresentationModel(pm)
		pm.usersList.observe(this, Observer { usersListAdapter.submitList(it) })
		pm.paginatedLoadState().observe(this, Observer { usersListAdapter.setLoadState(it) })
		pm.initialLoadState().observe(this, Observer { setProgress(it) })
	}

	private fun setProgress(state: LoadState) {
		when (state) {
			is Success -> {
				progressView.visibility = View.GONE
				itemsView.visibility = View.VISIBLE
			}
			is Loading -> {
				progressView.visibility = View.VISIBLE
				itemsView.visibility = View.INVISIBLE
			}
		}
	}

	companion object {
		fun newInstance() = UsersListFragment()
	}
}