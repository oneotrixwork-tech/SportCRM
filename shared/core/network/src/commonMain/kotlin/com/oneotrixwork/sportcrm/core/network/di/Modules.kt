package com.oneotrixwork.sportcrm.core.network.di

import com.oneotrixwork.sportcrm.core.network.createHttpClient
import com.oneotrixwork.sportcrm.core.network.defaultBaseUrl
import org.koin.dsl.module

fun networkModule(baseUrl: String = defaultBaseUrl()) = module {
    single { createHttpClient(baseUrl = baseUrl) }
}