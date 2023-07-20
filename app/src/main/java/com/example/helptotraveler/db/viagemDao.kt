package com.example.helptotraveler.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.helptotraveler.Model.Viagem

@Dao
interface viagemDao {
    @Query("SELECT * FROM viagem_table")
    fun getAllViagens(): LiveData<List<Viagem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(viagem: Viagem)

    @Query("DELETE FROM viagem_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteViagem(viagem: Viagem)

}
