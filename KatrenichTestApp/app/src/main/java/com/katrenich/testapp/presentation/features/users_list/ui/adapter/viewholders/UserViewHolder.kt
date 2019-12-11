package com.katrenich.testapp.presentation.features.users_list.ui.adapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.katrenich.testapp.R
import com.katrenich.testapp.presentation.features.users_list.ui.adapter.items.UserListItem
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	fun bindTo(user: UserListItem?) {
		itemView.titleView.text = user?.login
		Glide.with(itemView.context)
			.load(user?.avatarUrl)
			.apply(RequestOptions.circleCropTransform())
			.placeholder(R.drawable.ic_no_image)
			.into(itemView.avatarView)
	}

	companion object {
		fun create(parent: ViewGroup): UserViewHolder {
			val layoutInflater = LayoutInflater.from(parent.context)
			val view = layoutInflater.inflate(R.layout.item_user, parent, false)
			return UserViewHolder(view)
		}
	}
}