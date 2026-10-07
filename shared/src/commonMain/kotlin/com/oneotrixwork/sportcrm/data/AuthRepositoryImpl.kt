package com.oneotrixwork.sportcrm.data

import com.oneotrixwork.sportcrm.LoginResponse
import com.oneotrixwork.sportcrm.data.api.AuthApi
import com.oneotrixwork.sportcrm.domain.AuthRepository
import com.oneotrixwork.sportcrm.domain.DomainResult

internal class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun login (login: String, password: String): DomainResult<LoginResponse> {
        val result = authApi.login(login = login, password = password)
        return result
    }
}