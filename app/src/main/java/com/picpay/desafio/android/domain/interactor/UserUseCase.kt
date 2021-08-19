package com.picpay.desafio.android.domain.interactor

import com.picpay.desafio.android.domain.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun getUsers(): Flow<List<UserEntity>>
}