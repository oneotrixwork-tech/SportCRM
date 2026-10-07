package com.oneotrixwork.sportcrm.domain

import com.oneotrixwork.sportcrm.LoginResponse

interface AuthRepository {

    suspend fun login (login: String, password: String): DomainResult<LoginResponse>

}