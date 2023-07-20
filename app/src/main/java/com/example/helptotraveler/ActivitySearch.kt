package com.example.helptotraveler

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//Activity que mostra as opções de busca de passagens aéreas e hotéis


class ActivitySearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val trivago = findViewById<Button>(R.id.btnTrivago)
        val passagens = findViewById<Button>(R.id.btnPassagens)
        trivago.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.trivago.com.br/"))
            startActivity(intent)
        }
        passagens.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.decolar.com/"))
            startActivity(intent)
        }
    }




}