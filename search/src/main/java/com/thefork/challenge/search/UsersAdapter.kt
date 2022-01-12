package com.thefork.challenge.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thefork.challenge.api.UserPreview

class UsersAdapter(
    private val users: List<UserPreview>,
    private val userItemClickListener: UserEventHandler
) : RecyclerView.Adapter<UsersAdapter.UserItemViewHolder>() {

    class UserItemViewHolder(view: View, val goToUserDetail: (userId: String) -> Unit) : RecyclerView.ViewHolder(view) {

        private val lastNameView = view.findViewById<TextView>(R.id.last_name_text_view)
        private val firstNameView = view.findViewById<TextView>(R.id.first_name_text_view)
        private val userContainerView = view.findViewById<LinearLayout>(R.id.user_item_container)

        fun bind(user: UserPreview) {
            lastNameView.text = user.lastName
            firstNameView.text = user.firstName
            userContainerView.setOnClickListener {
                goToUserDetail(user.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    ) { userId: String ->
        userItemClickListener.onUserClicked(userId)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    interface UserEventHandler {
        fun onUserClicked(userId: String)
    }

}
