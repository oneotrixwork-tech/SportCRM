import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        // The entire dependency graph is already assembled in shared; Swift only starts Koin.
        // doInitKoin: Kotlin/Native renames methods with the init prefix
        // because of the ObjC initializer family
        KoinInitializer.shared.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            LoginView()
        }
    }
}
