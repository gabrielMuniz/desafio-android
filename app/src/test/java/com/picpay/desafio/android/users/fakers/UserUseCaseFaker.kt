package com.picpay.desafio.android.users.fakers

import com.picpay.desafio.android.domain.UserEntity
import com.picpay.desafio.android.domain.interactor.UserUseCase
import kotlinx.coroutines.flow.Flow

class UserUseCaseFaker : UserUseCase {
    override suspend fun getUsers(): Flow<List<UserEntity>> {
        throw Exception()
    }
}