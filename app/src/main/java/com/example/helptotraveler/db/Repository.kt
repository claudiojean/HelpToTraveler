package com.example.helptotraveler.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.Model.Viagem

//Repositório utilizado de conexão entre as DAO's e a view model

class Repository(private val viagemDao: viagemDao, private val eventoDao: eventoDao) {
    val allViagens: LiveData<List<Viagem>> = viagemDao.getAllViagens()
    val allEventos: LiveData<List<Evento>> = eventoDao.getAllEventos()

    @Suppress
    @WorkerThread
    suspend fun addViagem(viagem: Viagem){
        viagemDao.insert(viagem)
    }

    @Suppress
    @WorkerThread
    fun getEventosById(viagemId: Long):LiveData<List<Evento>>{
        return eventoDao.getEventosByViagemId(viagemId)
    }

    @Suppress
    @WorkerThread
    suspend fun deleteViagem(viagem: Viagem){
        viagemDao.deleteViagem(viagem)
    }

    @Suppress
    @WorkerThread
    suspend fun deleteEvento(evento: Evento){
        eventoDao.deleteEvento(evento)
    }

    @Suppress
    @WorkerThread
    suspend fun addEvento(evento: Evento){
        eventoDao.insert(evento)
    }

}