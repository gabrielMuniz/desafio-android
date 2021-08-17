package com.picpay.desafio.android.domain.interactor

import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getUsers(): Flow<List<User>>
}