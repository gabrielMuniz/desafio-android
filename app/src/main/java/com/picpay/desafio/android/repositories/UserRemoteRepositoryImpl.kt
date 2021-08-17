package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.data.services.requestmaker.RequestMaker
import kotlinx.coroutines.flow.flow

class UserRemoteRepositoryImpl(
    private val requestMaker: RequestMaker,
    private val picPayService: PicPayService
) : UserRepository {
    override fun getUsers() = flow { emit(requestMaker.getResult(picPayService.getUsers())) }
}