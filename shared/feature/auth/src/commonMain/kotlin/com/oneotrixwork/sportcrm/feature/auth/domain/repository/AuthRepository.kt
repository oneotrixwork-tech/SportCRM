package com.oneotrixwork.sportcrm.feature.auth.domain.repository

import com.oneotrix.sportcrm.core.model.DomainResult
import com.oneotrixwork.sportcrm.feature.auth.domain.models.LoginResponse

interface AuthRepository {

    suspend fun login (login: String, password: String): DomainResult<LoginResponse>

}