package ies.sequeros.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.model.Pokemon
import ies.sequeros.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(var repository: PokemonRepository) : ViewModel() {
    private var _selected = MutableStateFlow(Pokemon())
    private var _items = MutableStateFlow<MutableList<Pokemon>>(mutableListOf())
    val selected: StateFlow<Pokemon> = _selected
    val items: StateFlow<MutableList<Pokemon>> = _items

    init {
        this.getAll()
    }

    fun getAll() {
        //se ejecuta en una corrutina de entrada-salida
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = repository.getAll().toMutableList()
        }
    }

    fun remove(name: String) {
        //por nombre
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = _items.value.apply {
                removeIf { it.name.equals(name) }
            }
        }
    }

    fun remove(item: Pokemon) {
        //se llamaría al servicio para que lo borre en el servidor
        viewModelScope.launch(Dispatchers.IO) {
            //se tiene que hacer así para que funcione
            _items.value = _items.value.toMutableList().apply {
                remove(item)
            }
        }
    }

    fun increasePower(value: Int = 1) {
        //se ejecuta en esa corrutina, realmente ha de enviar peticion al servidor
        viewModelScope.launch(Dispatchers.IO) {
            if (_selected.value.power < 100) {
                //se tiene que "avisar" a listado
                _items.value = _items.value.toMutableList().apply {
                    replaceAll {
                        if (it.id == _selected.value.id) {
                            _selected.value = _selected.value.copy(power = _selected.value.power +value)
                            _selected.value
                        } else it
                    }
                }
            }
        }


    }

    fun decreasePower(value: Int = 1) {
        if(_selected.value.power >0) {
        //se ejecuta en esa corrutina, realmente ha de enviar peticion al servidor
        viewModelScope.launch(Dispatchers.IO) {
            //se tiene que "avisar" a listado
            _items.value = _items.value.toMutableList().apply {
                replaceAll {
                    if (it.id == _selected.value.id) {
                        _selected.value = _selected.value.copy(power = _selected.value.power - value)
                        _selected.value
                    } else it
                }
            }
        }
        }
    }

    fun unSelect() {
        _selected.value = Pokemon()
    }

    fun setSelected(item: Pokemon) {
        _selected.value = item//.copy()
    }


}