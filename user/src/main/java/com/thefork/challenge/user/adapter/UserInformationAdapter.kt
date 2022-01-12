package com.thefork.challenge.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thefork.challenge.user.viewholder.*

class UserInformationAdapter(private val rows: List<UserInformationRowType>) :
    RecyclerView.Adapter<BaseUserInformationRowViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseUserInformationRowViewHolder {
        return UserInformationRowFactory.generateViewHolder(
            UserInformationRowType.getRowByViewType(viewType),
            LayoutInflater.from(parent.context),
            parent
        )
    }

    override fun onBindViewHolder(holder: BaseUserInformationRowViewHolder, position: Int) {
        when (holder) {
            is UserEmailViewHolder -> (rows[position] as UserInformationRowType.EmailRow).let {
                holder.bind(it.label, it.information)
            }
            is UserAlternativeEmailViewHolder -> (rows[position] as UserInformationRowType.AlternativeEmailRow).let {
                holder.bind(it.label, it.information)
            }
            is UserWorkNumberViewHolder -> (rows[position] as UserInformationRowType.WorkNumberRow).let {
                holder.bind(it.label, it.information)
            }
            is UserMobileNumberViewHolder   -> (rows[position] as UserInformationRowType.MobileNumberRow).let {
                holder.bind(it.label, it.information)
            }
        }
    }

    override fun getItemCount(): Int = rows.size

    override fun getItemViewType(position: Int): Int = rows[position].viewType()
}