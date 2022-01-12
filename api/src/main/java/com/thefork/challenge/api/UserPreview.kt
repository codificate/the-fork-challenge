package com.thefork.challenge.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPreview(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val phone: String,
    val email: String
) : Parcelable
