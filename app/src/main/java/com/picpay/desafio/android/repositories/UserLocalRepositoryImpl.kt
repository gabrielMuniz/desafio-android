package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.flow.Flow

class UserLocalRepositoryImpl : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }
}