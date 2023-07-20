package com.example.helptotraveler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddTravel.setOnClickListener {
            val intent = Intent(this,AddTravel::class.java)
            startActivity(intent)
        }
        btnListTravels.setOnClickListener {
            val intent = Intent(this,ListTravel::class.java)
            startActivity(intent)
        }
        btnSearchTickets.setOnClickListener {
            val intent = Intent(this,ActivitySearch::class.java)
            startActivity(intent)
        }
        btnVideo.setOnClickListener {
            val intent = Intent(this,Video::class.java)
            startActivity(intent)
        }
    }

}