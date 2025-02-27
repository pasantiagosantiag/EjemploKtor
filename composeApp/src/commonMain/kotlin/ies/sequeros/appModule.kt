package ies.sequeros

import ies.sequeros.model.User
import ies.sequeros.repository.PokemonRepository
import ies.sequeros.viewmodels.AppViewModel
import ies.sequeros.viewmodels.PokemonViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
   single { User() }
   single{ PokemonRepository()}
   viewModel { AppViewModel(get()) }
   viewModel { PokemonViewModel(get()) }
   }