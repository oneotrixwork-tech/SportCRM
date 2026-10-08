package com.oneotrixwork.sportcrm.feature.auth.domain.usecases

import com.oneotrix.sportcrm.core.model.DomainResult
import com.oneotrixwork.sportcrm.feature.auth.domain.models.LoginResponse
import com.oneotrixwork.sportcrm.feature.auth.domain.repository.AuthRepository

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