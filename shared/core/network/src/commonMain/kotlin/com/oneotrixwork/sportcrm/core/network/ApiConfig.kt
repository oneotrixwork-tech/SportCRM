package com.oneotrixwork.sportcrm.core.network

/**
 * Base URL of the Digest backend for local development.
 * Android emulator reaches the host machine via 10.0.2.2; a physical device
 * needs the host's LAN IP — pass it to `initKoin(baseUrl = ...)`.
 */
expect fun defaultBaseUrl(): String