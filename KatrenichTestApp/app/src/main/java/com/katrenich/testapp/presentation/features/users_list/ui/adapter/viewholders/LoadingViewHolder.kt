package com.katrenich.testapp.presentation.features.users_list.ui.adapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.LoadingListItem
import kotlinx.android.synthetic.main.item_loading.view.*

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	fun bindTo(loading: LoadingListItem) {
		itemView.progressView.isIndeterminate = true
	}

	companion object {
		fun create(parent: ViewGroup): LoadingViewHolder {
			val layoutInflater = LayoutInflater.from(parent.context)
			val view = layoutInflater.inflate(R.layout.item_loading, parent, false)
			return LoadingViewHolder(view)
		}
	}
}