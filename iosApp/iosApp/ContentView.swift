import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    @State private var isLoading = false
    @State private var message = "Click me!"
    private let repository = AuthRepository()
    
    var body: some View {
        VStack {
            Button(message) {
                withAnimation {
                    showContent = !showContent
                    isLoading = true
                }
                Task {
                    do {
                        let response = try await repository.login(
                            login: "test", 
                            password: "test"
                        )
                        
                        await MainActor.run {
                            isLoading = false
                            
                            if response.success {
                                message = response.role ?? "нет роли"
                            } else {
                                message = response.message
                            }
                        }
                    } catch {
                        await MainActor.run {
                            isLoading = false
                            message = "Ошибка: \(error.localizedDescription)"
                        }
                    }
                }
            }
            .disabled(isLoading)

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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
