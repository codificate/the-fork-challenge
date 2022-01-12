package com.thefork.challenge.user.viewholder

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseUserInformationRowViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(@StringRes labelText: Int, information: String)

}