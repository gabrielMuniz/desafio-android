package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repositories.UserRemoteRepositoryImpl
import com.picpay.desafio.android.data.repositories.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { UserRemoteRepositoryImpl(get(), get()) as UserRepository }

}