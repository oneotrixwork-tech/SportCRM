package com.oneotrixwork.sportcrm.domain.usecase

import com.oneotrixwork.sportcrm.LoginResponse
import com.oneotrixwork.sportcrm.domain.AuthRepository
import com.oneotrixwork.sportcrm.domain.DomainResult

class LoginUseCase internal constructor(
    private val authRepository: AuthRepository,
){
    suspend operator fun invoke(login: String, password: String): DomainResult<LoginResponse> {
        return when(val loginResult = authRepository.login(login = login, password = password)) {
            is DomainResult.Failure -> loginResult
            is DomainResult.Success -> loginResult
        }
    }
}