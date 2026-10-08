package com.oneotrixwork.sportcrm.feature.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val role: String? = null,
    val token: String? = null
)