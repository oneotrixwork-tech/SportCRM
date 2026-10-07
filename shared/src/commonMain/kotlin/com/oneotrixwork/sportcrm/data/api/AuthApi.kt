package com.oneotrixwork.sportcrm.data.api


import com.oneotrixwork.sportcrm.LoginRequest
import com.oneotrixwork.sportcrm.LoginResponse
import com.oneotrixwork.sportcrm.data.network.safeApiCall
import com.oneotrixwork.sportcrm.domain.DomainResult
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