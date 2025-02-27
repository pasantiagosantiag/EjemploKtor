package ies.sequeros

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import ejemploktor.composeapp.generated.resources.Res
import ejemploktor.composeapp.generated.resources.compose_multiplatform
import ies.sequeros.ui.LoginForm
import ies.sequeros.ui.Principal
import ies.sequeros.viewmodels.AppViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(vm: AppViewModel=  koinViewModel<AppViewModel>()   ){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login" // Pantalla inicial
    ) {
        composable("login") {
            LoginForm(navController) { email, password ->
                val resultado = vm.validate(email, password)
                if (resultado) {
                    navController.navigate("principal")
                    true
                }
                else
                    false
               // true //print("hola")

            }
        }
        composable("principal") {
            Principal( salir = {

                navController.navigate("login")
            })//navController)
        }
        /*composable("administracion") {
            Administracion() //(navController)
        }*/
    }
}