package com.oneotrix.sportcrm.core.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: String,
    val message: String,
) {
    companion object {
        const val CODE_INVALID_CREDENTIALS = "invalid_credentials"
        const val CODE_INVALID_REFRESH_TOKEN = "invalid_refresh_token"
        const val CODE_UNAUTHORIZED = "unauthorized"
        const val CODE_NOT_FOUND = "not_found"
        const val CODE_BAD_REQUEST = "bad_request"
        const val CODE_INTERNAL = "internal_error"
    }
}