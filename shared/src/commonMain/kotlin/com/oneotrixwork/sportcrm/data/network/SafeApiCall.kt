package com.oneotrixwork.sportcrm.data.network

import com.oneotrixwork.sportcrm.data.ErrorResponse
import com.oneotrixwork.sportcrm.data.runSuspendCatching
import com.oneotrixwork.sportcrm.domain.AppError
import com.oneotrixwork.sportcrm.domain.DomainResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import co.touchlab.kermit.Logger as Kermit


/**
 * The single place where transport meets domain: executes a request and maps
 * the result into one [DomainResult] with a fixed set of [AppError]s.
 *
 * Mapping is driven mostly by the HTTP status code; the backend [ErrorResponse]
 * body is read only as a fallback for unknown statuses (see [errorDetails]).
 * If you need to distinguish backend `code`s (e.g. `invalid_credentials` vs
 * `invalid_refresh_token`), branch on the body at the call site.
 */
internal suspend inline fun <reified T> HttpClient.safeApiCall(
    request: HttpClient.() -> HttpResponse,
): DomainResult<T> = try {
    val response = request(this)
    when {
        response.status.value in 200..299 -> DomainResult.Success(response.body())

        response.status == HttpStatusCode.BadRequest ||
                response.status == HttpStatusCode.UnprocessableEntity -> DomainResult.Failure(
            AppError.ValidationError)

        response.status == HttpStatusCode.Unauthorized -> DomainResult.Failure(AppError.UnauthorizedError)
        response.status == HttpStatusCode.Forbidden -> DomainResult.Failure(AppError.AccessDeniedError)
        response.status == HttpStatusCode.NotFound -> DomainResult.Failure(AppError.NotFoundError)
        response.status == HttpStatusCode.TooManyRequests -> DomainResult.Failure(AppError.TooManyRequestsError)
        response.status.value in 500..599 -> DomainResult.Failure(AppError.ServerError)

        else -> DomainResult.Failure(AppError.UnknownError(response.errorDetails()))
    }
} catch (e: CancellationException) {
    // Never swallow coroutine cancellation — rethrow so structured concurrency keeps working
    throw e
} catch (e: Exception) {
    val kermitLogger: Kermit = Kermit.withTag("Network")
    when (e) {
        is HttpRequestTimeoutException,
        is SocketTimeoutException,
        is ConnectTimeoutException -> DomainResult.Failure(AppError.TimeoutError)

        is UnresolvedAddressException -> DomainResult.Failure(AppError.NetworkUnavailableError)

        // A 2xx with a body we cannot parse is a contract bug, not a network problem
        is JsonConvertException,
        is SerializationException -> DomainResult.Failure(AppError.UnknownError("Malformed response: ${e.message}"))

        else -> {
            kermitLogger.w {"safeApiCall: unexpected ${e::class.simpleName}" + "\nexception: ${e.toString()}"}
            DomainResult.Failure(AppError.UnknownError("Unexpected client error: ${e.message}"))
        }
    }
}

/** Tries to read the unified backend error body, falls back to the raw status line. */
internal suspend fun HttpResponse.errorDetails(): String =
    runSuspendCatching { body<ErrorResponse>() }
        .map { "${it.code}: ${it.message}" }
        .getOrDefault("HTTP ${status.value}")