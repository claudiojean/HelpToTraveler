package com.example.helptotraveler

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.Model.Viagem
import com.example.helptotraveler.Others.RecyclerEventos
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.example.helptotraveler.db.HttApplication
import com.example.helptotraveler.db.HttViewModel
import com.example.helptotraveler.db.HttViewModelFactory
import kotlinx.android.synthetic.main.activity_travel.*

//A activity extend o adapter de RecyclerEventos pois precisa do RowClickListener para realizar funções em cada uma das linhas da recyclerview
class ActivityTravel : AppCompatActivity(),RecyclerEventos.RowClickListener {

    private val viewModel: HttViewModel by viewModels {
        HttViewModelFactory((application as HttApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)
        val viagem = intent.getSerializableExtra("VIAGEM") as? Viagem //Pegando o objeto da activity anterior para mostrar todos os dados nas linhas abaixo

        val txtOrigin = findViewById<TextView>(R.id.originTravel)
        val txtDestiny = findViewById<TextView>(R.id.destinyTravel)
        val txtType = findViewById<TextView>(R.id.typeTravel)
        val txtTransport = findViewById<TextView>(R.id.transportTravel)
        val txtCoin = findViewById<TextView>(R.id.coinTravel)
        val txtDatei = findViewById<TextView>(R.id.dateITravel)
        val txtDatef = findViewById<TextView>(R.id.dateFTravel)
        val txtCotacao = findViewById<TextView>(R.id.cotacaoTravel)
        txtOrigin.text = ("Origem: "+viagem?.origem)
        txtDestiny.text = ("Destino: "+viagem?.destino)
        txtType.text = ("Tipo: "+viagem?.tipoViagem)
        txtTransport.text = ("Tipo: "+viagem?.transporte)
        txtCoin.text = ("Moeda: "+viagem?.moeda)
        txtDatei.text = ("Data Inicial: "+viagem?.dataI)
        txtDatef.text = ("Data Final: "+viagem?.dataF)
        txtCotacao.text = ("Cotação da moeda: "+viagem?.high.toString())

        //O objeto é passado novamente para ser salvo como viagemid(chave estrangeira) do evento
        btnAddEvent.setOnClickListener {
            val intent = Intent(this,AddEvent::class.java)
            intent.putExtra("VIAGEM",viagem)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.listEvent)
        val adapter = RecyclerEventos(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val statusObserver = Observer<List<Evento>>{evento->
            adapter.setData(evento)
        }
        val transformed = Transformations.switchMap(viewModel.allEventos){
            viagem?.let { it1 -> viewModel.getEventoById(it1.viagemId) }
        }
        transformed.observe(this,statusObserver)
    }

    //sobescrevendo o método do adapter que é responsável por deletar um objeto criando um caixa de confirmação antes
    override fun onDeleteEventClickListener(evento: Evento) {
        val alert = AlertDialog.Builder(this)
        alert.setMessage("Delete?")
            .setCancelable(false)
            .setPositiveButton("Sim") { message, id ->
                viewModel.deleteEvento(evento)
            }
            .setNegativeButton("Não") { message, id ->
                message.dismiss()
            }
        val alerta = alert.create()
        alerta.show()
    }

    //Esse método era utilizado para mostrar os detalhes de cada evento mas todos eles foram passados para essa activity
    override fun onItemClickListener(evento: Evento) {
        val event = evento
    }
}