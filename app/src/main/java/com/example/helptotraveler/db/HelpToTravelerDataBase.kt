package com.example.helptotraveler.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.Model.Viagem

//Classe que cria o banco de dados

@Database(entities = [Viagem::class,Evento::class], version = 4)
abstract class HelpToTravelerDataBase :RoomDatabase() {
    abstract fun viagemDao(): viagemDao
    abstract fun eventoDao(): eventoDao
    companion object {
        @Volatile
        private var INSTANCE: HelpToTravelerDataBase? = null
        fun getDatabase(
            context: Context
        ): HelpToTravelerDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HelpToTravelerDataBase::class.java,
                    "helptotraveler_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}