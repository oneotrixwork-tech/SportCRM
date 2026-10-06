package com.oneotrixwork.sportcrm

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val login: String,
    val password: String,
)

@Serializable
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val role: String? = null,
    val token: String? = null
)