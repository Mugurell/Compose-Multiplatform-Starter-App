import SwiftUI
import Shared

struct ContentView: View {
	var body: some View {
        SharedComposable()
	}
}

struct SharedComposable: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
