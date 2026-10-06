package com.oneotrixwork.sportcrm

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AuthRepository {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true }
            )
        }
    }

    suspend fun login (login: String, password: String): LoginResponse {
        val url = "${getBaseUrl()}/login"
        return try {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(login = login, password = password))
            }

            response.body()
        } catch (e: Exception) {
            LoginResponse(success = false, message = "Ошибка: ${e.message}")
        }
    }
}