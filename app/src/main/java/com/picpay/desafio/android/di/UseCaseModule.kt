package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.interactor.UserUseCase
import com.picpay.desafio.android.domain.interactor.UserUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory { UserUseCaseImpl(get()) as UserUseCase }
}