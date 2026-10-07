package com.oneotrixwork.sportcrm.di

import com.oneotrixwork.sportcrm.screens.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val uiModule = module {
    viewModelOf(::LoginViewModel)
}