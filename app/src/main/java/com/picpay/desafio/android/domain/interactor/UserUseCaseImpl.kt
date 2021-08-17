package com.picpay.desafio.android.domain.interactor

import com.picpay.desafio.android.repositories.UserRepository

class UserUseCaseImpl(private val userRepository: UserRepository) : UserUseCase {
    override fun getUsers() = userRepository.getUsers()
}