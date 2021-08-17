package com.picpay.desafio.android.data.configuration

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientBuilder {

    companion object {

        fun <S> createService(
            serviceClass: Class<S>,
            baseUrl: String,
            readTimeoutInMills: Long = 100000L,
            gson: Gson = Gson()
        ): S {
            val httpClientBuilder = OkHttpClient.Builder()

            val client = httpClientBuilder.readTimeout(readTimeoutInMills, TimeUnit.MILLISECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()

            val retrofit = getClientBuilder(baseUrl, gson).client(client).build()
            return retrofit.create(serviceClass)

        }

        private fun getClientBuilder(baseUrl: String, gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
        }

    }

}