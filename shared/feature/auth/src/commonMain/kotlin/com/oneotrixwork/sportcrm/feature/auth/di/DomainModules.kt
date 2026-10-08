package com.oneotrixwork.sportcrm.feature.auth.di

import com.oneotrixwork.sportcrm.feature.auth.domain.usecases.LoginUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val authDomainModules = module {
    factoryOf(::LoginUseCase)
}