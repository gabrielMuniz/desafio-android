package com.picpay.desafio.android.users.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.repositories.UserRepository
import com.picpay.desafio.android.domain.interactor.UserUseCaseImpl
import com.picpay.desafio.android.shared.CoroutineTestRule
import com.picpay.desafio.android.users.UsersStub
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class UserUseCaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val repository = mock<UserRepository>()

    private val useCase = UserUseCaseImpl(repository)

    @Test
    fun `Given a users list When the getUsers is called Then the users returned should be right`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // given
            val expectedEntityUsers = UsersStub.userEntitiesStub

            whenever(repository.getUsers()).thenReturn(flow { expectedEntityUsers })

            // when
            useCase.getUsers().collect {
                // then
                assertEquals(expectedEntityUsers, it)
            }
        }
    }

}