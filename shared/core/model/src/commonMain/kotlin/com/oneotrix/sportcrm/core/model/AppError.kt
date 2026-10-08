package com.oneotrix.sportcrm.core.model

sealed class AppError(message: String) : Throwable(message) {

    /** No connectivity at all: host cannot be resolved, interface is down. */
    data object NetworkUnavailableError : AppError("Network is unavailable. Check your connection.")

    /** Generic network failure with connectivity present. */
    data object NetworkError : AppError("Network error. Please try again.")

    data object TimeoutError : AppError("The request timed out.")

    /** 401, wrong credentials or an expired/revoked session. */
    data object UnauthorizedError : AppError("Wrong email or password.")

    /** 403 — the server refused the action for this user. */
    data object AccessDeniedError : AppError("Access denied.")

    /** 404 */
    data object NotFoundError : AppError("Not found.")

    /** 429 */
    data object TooManyRequestsError : AppError("Too many requests. Try again later.")

    /** 5xx */
    data object ServerError : AppError("Server error.")

    /** 400/422 — the request payload failed validation. */
    data object ValidationError : AppError("Validation failed.")

    data class UnknownError(val details: String) : AppError(details)
}