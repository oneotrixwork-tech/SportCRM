package com.oneotrixwork.sportcrm.feature.auth.di

import org.koin.dsl.module

val authModule = module {
    includes(authDataModules, authDomainModules)
}