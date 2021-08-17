package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.configuration.ApiClientBuilder
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.data.services.requestmaker.RequestMaker
import com.picpay.desafio.android.data.services.requestmaker.RequestMakerImpl
import org.koin.dsl.module

val networkServiceModule = module {

    single {
        RequestMakerImpl() as RequestMaker
    }

    single {
        ApiClientBuilder.createService(
            serviceClass = PicPayService::class.java,
            baseUrl = BuildConfig.BASE_URL
        )
    }


}