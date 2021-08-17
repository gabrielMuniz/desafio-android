package com.picpay.desafio.android.data.services.requestmaker

import retrofit2.Call
import retrofit2.awaitResponse
import java.lang.Exception

class RequestMakerImpl : RequestMaker {
    override suspend fun <T : Any> getResult(call: Call<T>): T {
        call.awaitResponse().let { response ->
            response.body()?.let {
                return it
            }
            throw Exception(response.message())
        }
    }


}