package com.picpay.desafio.android.users.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.UserViewModel
import com.picpay.desafio.android.UserViewModel.State
import com.picpay.desafio.android.domain.interactor.UserUseCase
import com.picpay.desafio.android.shared.CoroutineTestRule
import com.picpay.desafio.android.users.UsersStub
import com.picpay.desafio.android.users.fakers.UserUseCaseFaker
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val useCase = mock<UserUseCase>()

    private val viewModel = UserViewModel(useCase)

    @Test
    fun `Given a users list When the getUsers is called Then the getUsers should be executed`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // given
            val expectedEntityUsers = UsersStub.userEntitiesStub

            whenever(useCase.getUsers()).thenReturn(flow { expectedEntityUsers })

            // when
            viewModel.getUsers()

            //then
            verify(useCase).getUsers()
        }
    }

    @Test
    fun `Given a users list When the getUsers is called Then the state should be OnLoading`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // given
            val expectedEntityUsers = UsersStub.userEntitiesStub

            whenever(useCase.getUsers()).thenReturn(flow { expectedEntityUsers })

            // when
            viewModel.getUsers()

            //then
            assertEquals(viewModel.state.value, State.OnLoading)
        }
    }

    @Test
    fun `Given a users list When the getUsers gets success Then the state should be OnSuccess`() {
        // given
        val expectedEntityUsers = UsersStub.userEntitiesStub

        viewModel.state.observeForever {}

        coroutinesTestRule.testDispatcher.runBlockingTest {
            whenever(useCase.getUsers()).thenReturn(flow { emit(expectedEntityUsers) })
        }
        // when
        viewModel.getUsers()

        // then
        assertEquals(State.OnSuccess(expectedEntityUsers), viewModel.state.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Given a users list When the getUsers gets error Then the state should be OnError`() {
        // given
        val userUseCase: UserUseCase = UserUseCaseFaker()
        val userViewModel = UserViewModel(userUseCase)

        userViewModel.state.observeForever {}

        // when
        userViewModel.getUsers()

        // then
        assertEquals(State.OnError, userViewModel.state.value)
    }
}