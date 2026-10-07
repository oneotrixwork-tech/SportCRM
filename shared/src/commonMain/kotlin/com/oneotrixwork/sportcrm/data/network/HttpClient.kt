package com.oneotrixwork.sportcrm.data.network

import co.touchlab.kermit.Logger as Kermit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


internal fun createHttpClient(
    baseUrl: String
): HttpClient = HttpClient {
    val kermitLogger: Kermit = Kermit.withTag("HttpClient")

    expectSuccess = false

    defaultRequest {
        url(baseUrl)
        contentType(ContentType.Application.Json)
    }

    install(ContentNegotiation) {
        json(
            Json { ignoreUnknownKeys = true }
        )
    }

    install(Logging) {
        level = LogLevel.INFO
        logger = object : Logger {
            override fun log(message: String) {
                kermitLogger.i { message }
            }
        }
    }
}