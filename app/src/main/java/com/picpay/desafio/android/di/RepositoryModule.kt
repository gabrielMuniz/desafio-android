package com.picpay.desafio.android.di

import com.picpay.desafio.android.repositories.UserLocalRepositoryImpl
import com.picpay.desafio.android.repositories.UserRemoteRepositoryImpl
import com.picpay.desafio.android.repositories.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { UserLocalRepositoryImpl() as UserRepository }
    factory { UserRemoteRepositoryImpl(get(), get()) as UserRepository }

}