package com.example.helptotraveler

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helptotraveler.Model.Viagem
import com.example.helptotraveler.Others.RecyclerViagens
import com.example.helptotraveler.db.HttViewModel
import com.example.helptotraveler.db.HttApplication
import com.example.helptotraveler.db.HttViewModelFactory
import java.lang.Thread.sleep


class ListTravel : AppCompatActivity(), RecyclerViagens.RowClickListener {

    private val viewModel: HttViewModel by viewModels {
        HttViewModelFactory((application as HttApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_travel)


        val recyclerView = findViewById<RecyclerView>(R.id.listTravel)
        val adapter = RecyclerViagens(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val statusObserver = Observer<List<Viagem>>{viagens ->
            adapter.setData(viagens)
        }

        viewModel.allViagens.observe(this,statusObserver)

    }

    //sobescrevendo o método do adapter de recyclerview que é responsável por deletar um objeto
    override fun onDeleteViagemClickListener(viagem: Viagem) {
        val alert = AlertDialog.Builder(this)
        alert.setMessage("Delete?")
            .setCancelable(false)
            .setPositiveButton("Sim") { message, id ->
                viewModel.deleteViagem(viagem)
            }
            .setNegativeButton("Não") { message, id ->
                message.dismiss()
            }
        val alerta = alert.create()
        alerta.show()
    }

    //Esse método é utilizado para mostrar todos os detalhes da viagem escolhida em uma position da recyclerview
    override fun onItemClickListener(viagem: Viagem) {
        val travel = viagem
        viagem.calcularMoedaExterna(this)
        val intent = Intent(this,ActivityTravel::class.java)
        intent.putExtra("VIAGEM",travel)
        startActivity(intent)
    }

}