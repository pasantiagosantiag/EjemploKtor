package ies.sequeros.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LoginForm(navController:NavHostController, modifier: Modifier = Modifier, loginFunction: ((String,String) -> Boolean)? =null) {

    var username by remember { mutableStateOf("Cyclomon") }
    var password by remember { mutableStateOf("26348760Y") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo de usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                placeholder = { Text("Introduce tu usuario") },
                singleLine = true,

                )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                placeholder = { Text("Introduce tu contraseña") },
                singleLine = true,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        val icon = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        Icon(icon, contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                    }
                },

                )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de inicio de sesión
            Button(
                onClick = {
                    if(loginFunction!=null){
                        loginFunction(username,password)
                    }

                },
                // modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Iniciar Sesión")
            }

            // Mensaje de error
            if (showError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Por favor, completa todos los campos",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}