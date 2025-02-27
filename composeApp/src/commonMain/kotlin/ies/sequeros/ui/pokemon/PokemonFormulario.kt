package ies.sequeros.ui.pokemon
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import ies.sequeros.model.Pokemon
import ies.sequeros.viewmodels.PokemonViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PokemonFormulario(expandido: Boolean,

                      atras: () -> Unit,
                      vm: PokemonViewModel = koinViewModel()
) {

    val pokemon by vm.selected.collectAsState()
    // Estados para los campos
    var id by mutableStateOf(pokemon.id)

    var name by  mutableStateOf(pokemon.name)
    var power by  mutableStateOf(pokemon.power)
    var href by  mutableStateOf(pokemon.href)
    var image by  mutableStateOf(pokemon.image)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de correo electrónico
        OutlinedTextField(
            value = name,
            onValueChange = {

                name=it;
            },
            enabled = false, //viewModel.selected.value == null,
            label = { Text("Nombre") },
            //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de nombre
        OutlinedTextField(
            value = href,
            onValueChange = {

                href = it;

            },
            enabled = false,
            label = { Text("Href") },
            modifier = Modifier.fillMaxWidth()
        )
        // Campo de correo electrónico
        OutlinedTextField(
            value = power.toString(),
            onValueChange = {

                power=it.toInt();
            },
            enabled = false, //viewModel.selected.value == null,
            label = { Text("Power") },
            //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
    if(image!="")
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight(0.30f)
        )
Column {
    // Botón de envío
    Button(
        onClick = {
vm.decreasePower()
        },

        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Remove,
            contentDescription = "Decrementar",
        )
    }
    // Botón de envío
    Button(
        onClick = {
           vm.increasePower()
        },

        modifier = Modifier.fillMaxWidth()
    ) {
       Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Incrementar",
        )
    }
}

       // if(!expandido){
            Button(
                onClick = {
                    atras()
                },

                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        //}
    }
}
