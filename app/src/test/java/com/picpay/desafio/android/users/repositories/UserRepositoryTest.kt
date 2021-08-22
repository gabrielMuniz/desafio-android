package com.picpay.desafio.android.users.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.repositories.UserRemoteRepositoryImpl
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.data.services.requestmaker.RequestMaker
import com.picpay.desafio.android.data.users.UserResponse
import com.picpay.desafio.android.shared.CoroutineTestRule
import com.picpay.desafio.android.users.UsersStub
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val picPayService = mock<PicPayService>()
    private val requestMaker = mock<RequestMaker>()

    private val repository =
        UserRemoteRepositoryImpl(requestMaker = requestMaker, picPayService = picPayService)

    @Test
    fun `Given a users list When the getUsers is called Then the users returned should be right`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // given
            val call = mock<Call<List<UserResponse>>>()
            val expectedResponseUsers = UsersStub.userResponsesStub
            val expectedEntityUsers = UsersStub.userEntitiesStub

            whenever(picPayService.getUsers()).thenReturn(call)
            whenever(requestMaker.getResult(call)).thenReturn(expectedResponseUsers)

            // when
            repository.getUsers().collect {
                assertEquals(expectedEntityUsers, it)
            }
        }
    }
}