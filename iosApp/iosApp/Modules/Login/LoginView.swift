//
//  LoginView.swift
//  iosApp
//
//  Created by Shutov MS on 07.10.2026.
//

import SwiftUI

struct LoginView: View {
    @StateObject private var viewModel = LoginViewModel()
    @State private var showContent = false
    @State private var message = "Click me!"
    var body: some View {
        VStack {
            Button(message) {
                withAnimation {
                    showContent = !showContent
                }
                Task {
                    viewModel.submit()
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI")
                }
                .transition(.move(edge: .bottom).combined(with: .blurReplace))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

#Preview {
    LoginView()
}
