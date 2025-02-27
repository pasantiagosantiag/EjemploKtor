package ies.sequeros.viewmodels

import androidx.lifecycle.ViewModel
import ies.sequeros.model.User
import ies.sequeros.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

class AppViewModel (var repository: PokemonRepository): ViewModel( ) {
    fun validate(name: String, password: String): Boolean {
        //se ejecuta en corutina bloqueante
          var resultado= runBlocking {
               repository.login(name,password)
           }
        return resultado
    }

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

}