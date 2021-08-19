package com.picpay.desafio.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.UserEntity
import com.picpay.desafio.android.domain.interactor.UserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    val state = MutableLiveData<State>()

    fun getUsers() {
        state.value = State.OnLoading
        try {
            viewModelScope.launch {
                userUseCase.getUsers().collect {
                    state.value = State.OnSuccess(it)
                }
            }
        } catch (e: Exception) {
            state.value = State.OnError
        }
    }

    sealed class State {
        object OnLoading : State()
        data class OnSuccess(val userEntities: List<UserEntity>) : State()
        object OnError : State()
    }

}