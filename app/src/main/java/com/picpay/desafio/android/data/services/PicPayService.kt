package com.picpay.desafio.android.data.services

import com.picpay.desafio.android.data.users.UserResponse
import com.picpay.desafio.android.domain.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserResponse>>
}