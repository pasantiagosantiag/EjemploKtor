package ies.sequeros.ui.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ies.sequeros.model.Pokemon
import kotlin.io.encoding.ExperimentalEncodingApi


@OptIn(ExperimentalEncodingApi::class)
@Composable
fun PokemonItem(
    item: Pokemon,
    //context: Context,
    ver: () -> Unit,

    borrar: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text("${item.name}(${item.id}) Power ${item.power}", modifier = Modifier.clickable { ver() }.weight(0.70f))


        Button(onClick = { borrar() }, modifier = Modifier.weight(0.20f)) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Borrar",
            )
        }
    }
}