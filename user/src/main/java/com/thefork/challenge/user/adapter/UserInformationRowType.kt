package com.thefork.challenge.user.adapter

import android.os.Parcelable
import androidx.annotation.StringRes
import com.thefork.challenge.user.R
import kotlinx.parcelize.Parcelize
import java.lang.IllegalStateException

sealed class UserInformationRowType(@StringRes val label: Int) : Parcelable {
    abstract fun viewType(): Int

    @Parcelize
    data class EmailRow(val information: String) :
        UserInformationRowType(R.string.email_contact_label) {
        override fun viewType() = 0
    }

    @Parcelize
    data class AlternativeEmailRow(val information: String) :
        UserInformationRowType(R.string.alternative_email_contact_label) {
        override fun viewType() = 1
    }

    @Parcelize
    data class WorkNumberRow(val information: String) :
        UserInformationRowType(R.string.work_number_contact_label) {
        override fun viewType() = 2
    }

    @Parcelize
    data class MobileNumberRow(val information: String) :
        UserInformationRowType(R.string.mobile_number_contact_label) {
        override fun viewType() = 3
    }

    companion object {
        fun getRowByViewType(viewType: Int): UserInformationRowType {
            for (rowViewType in getAllViewTypes()) {
                if (rowViewType.viewType() == viewType) {
                    return rowViewType
                }
            }

            throw IllegalStateException("Unknown View Type to map to UserInformationScreenRowType: viewType=$viewType")
        }

        private fun getAllViewTypes() = listOf(
            EmailRow(""),
            AlternativeEmailRow(""),
            WorkNumberRow(""),
            MobileNumberRow("")
        )
    }
}