package com.katrenich.testapp.presentation.features.users_list.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.katrenich.testapp.R
import com.katrenich.testapp.data.LoadState
import com.katrenich.testapp.data.Loading
import com.katrenich.testapp.data.Success
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.LoadingListItem
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.viewholders.LoadingViewHolder
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.viewholders.UserViewHolder

class UsersListAdapter(
	diffCallback: DiffUtil.ItemCallback<UserListItem>
) : PagedListAdapter<UserListItem, RecyclerView.ViewHolder>(diffCallback) {

	private var loadingState: LoadState = Loading

	private val extraRow: Int
		get() = if (hasExtraRow())
			1
		else
			0

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
		when(viewType) {
			R.layout.item_user -> UserViewHolder.create(parent)
			R.layout.item_loading -> LoadingViewHolder.create(parent)
			else -> throw NoSuchElementException()
		}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when(getItemViewType(position)) {
			R.layout.item_user -> (holder as UserViewHolder).bindTo(getItem(position))
			R.layout.item_loading -> (holder as LoadingViewHolder).bindTo(LoadingListItem)
		}
	}

	override fun getItemCount(): Int {
		return super.getItemCount() + extraRow
	}

	fun setLoadState(state: LoadState) {
		val previousLoadState = loadingState
		val hadExtraRow = hasExtraRow()
		loadingState = state
		val hasExtraRow = hasExtraRow()

		if (hadExtraRow != hasExtraRow) {
			if (hadExtraRow) {
				notifyItemRemoved(super.getItemCount())
			} else {
				notifyItemInserted(super.getItemCount())
			}
		} else if (hasExtraRow && previousLoadState != loadingState) {
			notifyItemChanged(itemCount - 1)
		}
	}

	override fun getItemViewType(position: Int): Int =
		if (hasExtraRow() && position == itemCount - 1) {
			R.layout.item_loading
		} else {
			R.layout.item_user
		}

	private fun hasExtraRow(): Boolean =
		loadingState == Success
}