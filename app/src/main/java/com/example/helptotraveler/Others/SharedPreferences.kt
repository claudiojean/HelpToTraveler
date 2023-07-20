package com.example.helptotraveler.Others

import android.content.Context
import android.content.SharedPreferences

//Classe sharedpreferences criada somente para salvar o ID da notification

class SharedPreferences(context: Context) {
    private val PREFERENCE_NAME = "AULAANDROID"
    val shared_preference: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun saveInt (name:String,valor:Int){
        val editor: SharedPreferences.Editor = shared_preference.edit()
        editor.putInt(name,valor)
        editor.commit()
    }
    fun getInt(name:String):Int?{
        return shared_preference.getInt(name,0)
    }

}