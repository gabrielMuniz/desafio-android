package com.picpay.desafio.android.domain.interactor

import com.picpay.desafio.android.data.repositories.UserRepository

class UserUseCaseImpl(private val userRepository: UserRepository) : UserUseCase {
    override suspend fun getUsers() = userRepository.getUsers()
}