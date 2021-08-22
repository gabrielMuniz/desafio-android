package com.picpay.desafio.android.users

import com.picpay.desafio.android.data.users.UserResponse
import com.picpay.desafio.android.domain.UserEntity

class UsersStub {

    companion object {
        val userEntitiesStub = listOf(
            UserEntity("imagem", "name", 12, "username"),
            UserEntity("imagem1", "name1", 13, "username1"),
            UserEntity("imagem2", "name2", 14, "username2"),
            UserEntity("imagem3", "name3", 15, "username3")
        )

        val userResponsesStub = listOf(
            UserResponse("imagem", "name", 12, "username"),
            UserResponse("imagem1", "name1", 13, "username1"),
            UserResponse("imagem2", "name2", 14, "username2"),
            UserResponse("imagem3", "name3", 15, "username3")
        )
    }

}