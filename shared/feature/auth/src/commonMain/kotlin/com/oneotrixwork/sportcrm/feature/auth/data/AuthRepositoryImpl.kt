package com.oneotrixwork.sportcrm.feature.auth.data

import com.oneotrix.sportcrm.core.model.DomainResult
import com.oneotrixwork.sportcrm.feature.auth.data.remote.AuthApi
import com.oneotrixwork.sportcrm.feature.auth.domain.models.LoginResponse
import com.oneotrixwork.sportcrm.feature.auth.domain.repository.AuthRepository

internal class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun login (login: String, password: String): DomainResult<LoginResponse> {
        val result = authApi.login(login = login, password = password)
        return result
    }
}