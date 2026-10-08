package com.oneotrixwork.sportcrm.feature.auth.di

import com.oneotrixwork.sportcrm.feature.auth.data.AuthRepositoryImpl
import com.oneotrixwork.sportcrm.feature.auth.data.remote.AuthApi
import com.oneotrixwork.sportcrm.feature.auth.data.remote.AuthApiImpl
import com.oneotrixwork.sportcrm.feature.auth.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val authDataModules = module {
    singleOf(::AuthApiImpl) bind AuthApi::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}