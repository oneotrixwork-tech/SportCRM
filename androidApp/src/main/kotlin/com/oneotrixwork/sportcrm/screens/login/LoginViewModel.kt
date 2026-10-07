package com.oneotrixwork.sportcrm.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneotrixwork.sportcrm.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(login = "coach_tengo", password = "123456")
        }
    }
}