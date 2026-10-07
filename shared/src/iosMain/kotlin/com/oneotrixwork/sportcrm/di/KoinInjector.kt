package com.oneotrixwork.sportcrm.di

import com.oneotrixwork.sportcrm.domain.usecase.LoginUseCase
import org.koin.mp.KoinPlatform

object KoinInjector {
    private val koin get() = KoinPlatform.getKoin()

    val loginUseCase: LoginUseCase get() = koin.get()
}