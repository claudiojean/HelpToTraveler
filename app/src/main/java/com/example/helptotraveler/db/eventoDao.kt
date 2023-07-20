package com.example.helptotraveler.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.Model.ViagemWithEventos

//Classe para acessar o banco de dados DAO (Data access object)

@Dao
interface eventoDao{
    @Transaction
    @Query("SELECT * FROM evento_table")
    fun getAllEventos(): LiveData<List<Evento>>

    @Transaction
    @Query("SELECT * FROM evento_table WHERE fkviagemId = :viagemId")
    fun getEventosByViagemId(viagemId: Long): LiveData<List<Evento>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(evento: Evento)

    @Delete
    suspend fun deleteEvento(evento: Evento)

    @Query("DELETE FROM evento_table")
    suspend fun deleteAll()

}

