//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Shutov MS on 07.10.2026.
//

import Foundation
import Shared

@MainActor
final class LoginViewModel: ObservableObject {
    
    @Published var login = "coach_tengo"
    @Published var password = "123456"
    
    private let loginUseCase: LoginUseCaseProtocol
    
    init(loginUseCase: LoginUseCaseProtocol = KoinInjector.shared.loginUseCase) {
        self.loginUseCase = loginUseCase
    }
    
    func submit() {
        Task {
            guard let result = try? await loginUseCase.login(login: login, password: password) else {
                return
            }
        }
    }
}
