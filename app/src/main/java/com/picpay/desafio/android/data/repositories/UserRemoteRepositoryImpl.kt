package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.data.services.requestmaker.RequestMaker
import com.picpay.desafio.android.data.users.UserResponse
import com.picpay.desafio.android.domain.UserEntity
import kotlinx.coroutines.flow.flow

class UserRemoteRepositoryImpl(
    private val requestMaker: RequestMaker,
    private val picPayService: PicPayService
) : UserRepository {
    override suspend fun getUsers() =
        flow { emit(requestMaker.getResult(picPayService.getUsers()).map { it.toEntity() }) }
}

private fun UserResponse.toEntity() = UserEntity(img, name, id, username)