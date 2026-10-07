package com.oneotrixwork.sportcrm.di

object KoinInitializer {
    /**
     * iOS entry point: `KoinInitializer.shared.doInitKoin()` from iOSApp.swift.
     * The whole graph lives in shared; Swift takes ready-made UseCases from [KoinInjector].
     */
    fun initKoin() {
        initKoin(config = null)
    }

    /**
     * Overload with a runtime [baseUrl], so a real device can point to the backend
     * LAN address without changing Kotlin code (the simulator uses `localhost`).
     */
    fun initKoin(baseUrl: String) {
        initKoin(baseUrl = baseUrl, config = null)
    }
}