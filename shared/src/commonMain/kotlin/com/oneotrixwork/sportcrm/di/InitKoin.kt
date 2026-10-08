package com.oneotrixwork.sportcrm.di

import com.oneotrixwork.sportcrm.core.network.defaultBaseUrl
import com.oneotrixwork.sportcrm.core.network.di.networkModule
import com.oneotrixwork.sportcrm.feature.auth.di.authModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun initKoin(
    baseUrl: String = defaultBaseUrl(),
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(
            networkModule(baseUrl = baseUrl),
            authModule
        )
    }
}