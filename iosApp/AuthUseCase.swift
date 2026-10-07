//
//  AuthUseCase.swift
//  iosApp
//
//  Created by Shutov MS on 07.10.2026.
//

import Foundation
import Shared

// Protocols on top of Kotlin UseCases for testability:
// tests can inject a mock with the same signature;

public protocol LoginUseCaseProtocol {
    func login(login: String, password: String) async throws -> DomainResult<LoginResponse>
}

extension LoginUseCase: LoginUseCaseProtocol {
    public func login(login: String, password: String) async throws -> DomainResult<LoginResponse> {
        try await invoke(login: login, password: password)
    }
}

