package com.oneotrixwork.sportcrm.feature.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val login: String,
    val password: String,
)