package com.thefork.challenge.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thefork.challenge.user.viewholder.*

object UserInformationRowFactory {

    fun generateViewHolder(
        rowType: UserInformationRowType,
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ) : BaseUserInformationRowViewHolder {
        return when(rowType) {
            is UserInformationRowType.EmailRow -> UserEmailViewHolder.create(
                layoutInflater,
                parent
            )
            is UserInformationRowType.AlternativeEmailRow -> UserAlternativeEmailViewHolder.create(
                layoutInflater,
                parent
            )
            is UserInformationRowType.WorkNumberRow -> UserWorkNumberViewHolder.create(
                layoutInflater,
                parent
            )
            is UserInformationRowType.MobileNumberRow -> UserMobileNumberViewHolder.create(
                layoutInflater,
                parent
            )
        }
    }
}