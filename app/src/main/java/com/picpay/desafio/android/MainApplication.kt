package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.networkServiceModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.useCaseModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupInjector()
    }

    private fun setupInjector() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(getModules())
        }
    }

    private fun getModules() = listOf(
        networkServiceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )

}