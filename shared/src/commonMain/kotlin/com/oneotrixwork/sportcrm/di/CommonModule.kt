package com.oneotrixwork.sportcrm.di

import com.oneotrixwork.sportcrm.data.AuthRepositoryImpl
import com.oneotrixwork.sportcrm.data.api.AuthApi
import com.oneotrixwork.sportcrm.data.api.AuthApiImpl
import com.oneotrixwork.sportcrm.data.network.createHttpClient
import com.oneotrixwork.sportcrm.data.network.defaultBaseUrl
import com.oneotrixwork.sportcrm.domain.AuthRepository
import com.oneotrixwork.sportcrm.domain.usecase.LoginUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal fun commonModule(baseUrl: String = defaultBaseUrl()) = module {

    // Network
    single { createHttpClient(baseUrl = baseUrl) }

    // Data APIs
    singleOf(::AuthApiImpl) bind AuthApi::class

    // Data: repository implementations for domain contracts
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class

    //Domain: UseCases
    factoryOf(::LoginUseCase)
}