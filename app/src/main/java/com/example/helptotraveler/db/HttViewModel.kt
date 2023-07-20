package com.example.helptotraveler.db

import androidx.lifecycle.*
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.Model.Viagem
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//View Model que utiliza acessa o repositório para mostrar na activity

class HttViewModel(private val repository: Repository) : ViewModel() {

    val allViagens: LiveData<List<Viagem>> = repository.allViagens
    val allEventos: LiveData<List<Evento>> = repository.allEventos

    fun insertViagem(viagem: Viagem) = viewModelScope.launch {
        repository.addViagem(viagem)
    }
    //Função criada para listar todos os eventos de uma viagem específica, runBlocking foi adcionado no lugar do viewModelScope.launch pois com ele é possível retornar a lista de eventos
    fun getEventoById(viagemId: Long):LiveData<List<Evento>> = runBlocking {
        repository.getEventosById(viagemId)
    }

    fun deleteViagem(viagem: Viagem) = viewModelScope.launch{
        repository.deleteViagem(viagem)
    }

    fun deleteEvento(evento: Evento) = viewModelScope.launch{
        repository.deleteEvento(evento)
    }

    fun insertEvento(evento: Evento) = viewModelScope.launch {
        repository.addEvento(evento)
    }
}

class HttViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HttViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HttViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}