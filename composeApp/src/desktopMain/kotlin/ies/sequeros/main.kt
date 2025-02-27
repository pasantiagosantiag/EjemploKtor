package ies.sequeros

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.compose.KoinApplication

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "EjemploKtor",
    ) {
        KoinApplication(application = {
            modules(appModule)

        }) {
            App()
        }
    }
}