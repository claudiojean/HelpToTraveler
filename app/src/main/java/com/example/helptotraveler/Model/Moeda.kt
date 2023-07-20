package com.example.helptotraveler.Model

import com.google.gson.annotations.SerializedName

data class Moeda (
    @SerializedName("code") val code: String,
    @SerializedName("codein") val codein: String,
    @SerializedName("name") val name: String,
    @SerializedName("high") val high: String,
    @SerializedName("varBid") val varBid: String,
    @SerializedName("bid") val bid: String,
    @SerializedName("ask") val ask: String,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("create_date") val create_date: String
)
//Classe utilizado para receber a requisição de moeda que é realizada na classe Viagem


