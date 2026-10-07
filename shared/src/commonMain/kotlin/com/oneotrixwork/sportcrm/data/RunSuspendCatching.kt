package com.oneotrixwork.sportcrm.data

import kotlinx.coroutines.CancellationException

/** `runCatching` for suspend work that preserves structured cancellation. */
internal suspend inline fun <T> runSuspendCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    Result.failure(e)
}