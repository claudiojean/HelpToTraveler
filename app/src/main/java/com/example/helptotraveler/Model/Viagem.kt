package com.example.helptotraveler.Model

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "viagem_table")
data class Viagem(
    @PrimaryKey(autoGenerate = true) var viagemId: Long,
    var origem: String ="",
    var destino: String ="",
    var tipoViagem: String ="",
    var transporte: String ="",
    var moeda: String ="",
    var dataI: String ="",
    var dataF: String ="",
    var high: Double=0.0
): Serializable{
    //Função realizada para fazer a requisição da cotação caso a viagem seja internacional
    fun calcularMoedaExterna(context: Context){
        if(moeda.equals("Dólar")){
            val queue = Volley.newRequestQueue(context)
            val url = "https://economia.awesomeapi.com.br/json/USD-BRL"

            val stringRequest = StringRequest(Request.Method.GET,url,
                {response ->
                    val gson = GsonBuilder().setLenient().create()
                    val result = gson.fromJson(response,Array<Moeda>::class.java)
                    this.high = result[0].high.toDouble()
                }, { error -> error.printStackTrace() })
            queue.add(stringRequest)
        }else if(moeda.equals("Euro")){
            val queue = Volley.newRequestQueue(context)
            val url = "https://economia.awesomeapi.com.br/json/EUR-BRL"

            val stringRequest = StringRequest(Request.Method.GET,url,
                {response ->
                    val gson = GsonBuilder().setLenient().create()
                    val result = gson.fromJson(response,Array<Moeda>::class.java)
                    this.high = result[0].high.toDouble()
                }, { error -> error.printStackTrace() })
            queue.add(stringRequest)
        }else if(moeda.equals("Real")){
            this.high = 1.0
        }
    }


}