package ies.sequeros.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.*


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import ies.sequeros.ui.pokemon.PokemonMain

import kotlin.random.Random


/*import ies.sequeros.components.carrito.Carrito
import ies.sequeros.components.carrito.CarritoViewModel*/


enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("Home", Icons.Default.Home, "Home"),
    POKEMONS("Pokemons", Icons.Filled.Person, "Pokemos"),
     SALIR("Salir", Icons.Filled.Logout, "Salir")


}

@Composable
fun Principal(modifier: Modifier = Modifier, salir: () -> Unit) {
    var seleted = remember { mutableStateOf(AppDestinations.HOME) }

    Column(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.error)) {
        NavigationSuiteScaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.error),
            navigationSuiteItems = {

                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            androidx.compose.material3.Icon(
                                imageVector = it.icon,
                                contentDescription = it.contentDescription,
                                // tint = MaterialTheme.colorScheme.inversePrimary, // Cambia el color
                                //modifier = Modifier.size(96.dp)// Cambia el tamaño
                            )
                        },
                        label = { Text(it.label) },

                        selected = seleted.value == it,
                        onClick = {
                            if (it == AppDestinations.SALIR)
                                salir()
                            else
                                seleted.value = it
                        }
                    )
                }
            }
        ) {

            when (seleted.value) {
                AppDestinations.HOME -> Bienvenida()
                AppDestinations.POKEMONS -> PokemonMain()

                AppDestinations.SALIR -> {}
            }

        }
    }
}

@Composable
fun StaggeredGridItem(item: String, height: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = item,
            style = androidx.compose.ui.text.TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}