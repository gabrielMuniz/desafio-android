package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.domain.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<List<UserEntity>>

}