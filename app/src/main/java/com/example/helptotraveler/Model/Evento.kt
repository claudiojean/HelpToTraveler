package com.example.helptotraveler.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "evento_table")
class Evento(
    @PrimaryKey(autoGenerate = true) var eventoId: Long,
    var descricao: String ="",
    var local: String ="",
    var data: String ="",
    var horarioI: Int =0,
    var horarioF: Int =0,
    var fkviagemId: Long,
):Serializable


