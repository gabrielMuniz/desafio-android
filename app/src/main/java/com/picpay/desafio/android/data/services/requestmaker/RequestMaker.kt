package com.picpay.desafio.android.data.services.requestmaker

import retrofit2.Call

interface RequestMaker {
    suspend fun <T : Any> getResult(call: Call<T>) : T
}