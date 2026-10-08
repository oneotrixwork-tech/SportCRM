package com.oneotrixwork.sportcrm.feature.auth.data.remote

import com.oneotrix.sportcrm.core.model.DomainResult
import com.oneotrixwork.sportcrm.core.network.safeApiCall
import com.oneotrixwork.sportcrm.feature.auth.domain.models.LoginRequest
import com.oneotrixwork.sportcrm.feature.auth.domain.models.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal interface AuthApi {
    suspend fun login(login: String, password: String): DomainResult<LoginResponse>
}

internal class AuthApiImpl(private val client: HttpClient): AuthApi {
    override suspend fun login(login: String, password: String): DomainResult<LoginResponse> =
        client.safeApiCall {
            post("/login") {
                setBody(LoginRequest(login = login, password = password))
            }
        }

}