package com.oneotrixwork.sportcrm.domain

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

sealed class DomainResult<out T> {

    @OptIn(ExperimentalObjCName::class)
    @ObjCName("success", swiftName = "success")
    data class Success<T>(
        val value: T,
        /** True when the value comes from a local cache and may be stale. */
        val isOldData: Boolean = false,
    ) : DomainResult<T>()

    @OptIn(ExperimentalObjCName::class)
    @ObjCName("failure", swiftName = "failure")
    data class Failure<T>(val error: AppError) : DomainResult<T>()
}

val <T> DomainResult<T>.valueOrNull: T?
    get() = (this as? DomainResult.Success)?.value

inline fun <T, R> DomainResult<T>.map(transform: (T) -> R): DomainResult<R> = when (this) {
    is DomainResult.Success -> DomainResult.Success(transform(value), isOldData)
    is DomainResult.Failure -> DomainResult.Failure(error)
}