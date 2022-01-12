package com.thefork.challenge.user.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.thefork.challenge.user.R

class UserEmailViewHolder(private val view: View) : BaseUserInformationRowViewHolder(view) {

    override fun bind(labelText: Int, information: String) {
        view.findViewById<TextView>(R.id.information_label).setText(labelText)
        view.findViewById<TextView>(R.id.information_label_value).text = information
    }

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup): UserEmailViewHolder {
            val inflatedView = layoutInflater.inflate(
                R.layout.user_information_item,
                parent,
                false
            )
            return UserEmailViewHolder(
                inflatedView
            )
        }
    }
}