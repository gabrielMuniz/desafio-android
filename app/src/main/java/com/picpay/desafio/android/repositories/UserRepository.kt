package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

}