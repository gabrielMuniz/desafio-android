package com.picpay.desafio.android.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntity(
    val img: String, val name: String, val id: Int, val username: String
) : Parcelable