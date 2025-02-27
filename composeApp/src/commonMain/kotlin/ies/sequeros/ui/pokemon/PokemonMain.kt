package ies.sequeros.ui.pokemon


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.model.Pokemon
import ies.sequeros.viewmodels.PokemonViewModel

import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun PokemonMain(modifier: Modifier = Modifier, vm: PokemonViewModel = koinViewModel()) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Pokemon>()
    val elementos = vm.items.collectAsState()
    val formularioEditable = remember { mutableStateOf(false) }
    val selected=vm.selected.collectAsState()
    val isListAndDetailVisible =
        navigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail] == PaneAdaptedValue.Expanded && navigator.scaffoldValue[ListDetailPaneScaffoldRole.List] == PaneAdaptedValue.Expanded
    val searchview =
        navigator.scaffoldValue[ListDetailPaneScaffoldRole.List] == PaneAdaptedValue.Expanded

    /*BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }*/
    Scaffold(

    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(horizontal = 2.dp)) {
                    Text("Editor de usuarios")
            }

            ListDetailPaneScaffold(modifier = Modifier,
                directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    Box {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 32.dp)
                        ) {

                            items(elementos.value.size) {
                                PokemonItem(
                                    elementos.value[it],

                                    ver = {
                                        run {
                                            vm.setSelected(elementos.value.get(it))
                                            formularioEditable.value = false

                                            navigator.navigateTo(
                                                ListDetailPaneScaffoldRole.Detail,
                                                elementos.value.get(it)
                                            )
                                        }
                                    },  borrar = {
                                        run {
                                            var item=elementos.value.get(it)
                                            vm.remove(item)
                                        }
                                    })



                            }
                        }
                    }
                },
                detailPane = {

                   PokemonFormulario(expandido = true, atras = {
                        //run {
                        if (navigator.canNavigateBack()) navigator.navigateBack()
                        //}
                    })

                })
        }
    }
}