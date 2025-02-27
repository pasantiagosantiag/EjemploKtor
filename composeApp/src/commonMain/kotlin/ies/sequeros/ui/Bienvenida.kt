package ies.sequeros.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
@Composable
fun Bienvenida(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            // verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(
                text = "Pokemon API",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Icon(
                imageVector = Icons.Default.AirlineSeatFlat,
                contentDescription = "Pokemon",
                tint = MaterialTheme.colorScheme.primary, // Cambia el color
                modifier = Modifier.size(96.dp)// Cambia el tamaño
            )
        }
    }
}