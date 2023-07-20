package com.example.helptotraveler


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.helptotraveler.Model.Viagem
import com.example.helptotraveler.db.HttApplication
import com.example.helptotraveler.db.HttViewModel
import com.example.helptotraveler.db.HttViewModelFactory
import kotlinx.android.synthetic.main.activity_add_travel.*
import java.text.SimpleDateFormat
import java.util.*


class AddTravel : AppCompatActivity() {

    // Algumas variáveis declaradas antes do onCreate para serem usadas em outras funções também

    lateinit var datepickerB:DatePicker
    lateinit var datepickerF:DatePicker

    private val viewModel: HttViewModel by viewModels {
        HttViewModelFactory((application as HttApplication).repository)
    }

    lateinit var optionTravel:Spinner
    lateinit var optionTransport:Spinner
    lateinit var optionCoin:Spinner
    lateinit var calendarB:Calendar
    lateinit var calendarF:Calendar
    var dayB:Int = 0
    var monthB:Int = 0
    var yearB:Int = 0
    var dayF:Int = 0
    var monthF:Int = 0
    var yearF:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)

        optionTravel = findViewById(R.id.spTravelType)
        optionTransport = findViewById(R.id.spTransport)
        optionCoin = findViewById(R.id.spCoin)
        datepickerB = findViewById(R.id.dpBeginDay)
        datepickerF = findViewById(R.id.dpFinalDay)
        calendarB = Calendar.getInstance()
        calendarF = Calendar.getInstance()

        //Definindo as opções do Spinner
        val optionsTravel = arrayOf("Nacional","Internacional")
        val optionsTransport = arrayOf("Carro","Avião","Ônibus")
        val optionsCoin = arrayOf("Dólar","Euro")
        optionTravel.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsTravel)
        optionTransport.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsTransport)
        optionCoin.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,optionsCoin)


        //Deixando a opção de escolher a moeda visível ou invisível, de acordo com o tipo de viagem
        optionTravel.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    1 -> {
                        tvCoin.visibility = View.VISIBLE
                        optionCoin.visibility = View.VISIBLE
                    }
                    0 -> {
                        tvCoin.visibility = View.INVISIBLE
                        optionCoin.visibility = View.INVISIBLE
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        btnSave.setOnClickListener {
            dayB = datepickerB.dayOfMonth
            monthB = datepickerB.month
            yearB = datepickerB.year

            dayF = datepickerF.dayOfMonth
            monthF = datepickerF.month
            yearF = datepickerF.year

            calendarB.set(yearB, monthB, dayB)
            calendarF.set(yearF, monthF, dayF)

            if(etOrigin.text.isEmpty()){
                tvOrigin.setTextColor(Color.RED)
                tvOrigin.requestFocus()
                etOrigin.requestFocus()
            }else if(etDestiny.text.isEmpty()){
                tvDestiny.setTextColor(Color.RED)
                tvDestiny.requestFocus()
                etDestiny.requestFocus()
            }else if(calendarB>calendarF){
                tvFinalDay.setTextColor(Color.RED)
                tvFinalDay.requestFocus()
                datepickerF.requestFocus()
            }else{
                insertToDataBase()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun insertToDataBase(){

        val sdf = SimpleDateFormat("dd-MM-yyyy")
        var moeda:String

        val origem : String = etOrigin.text.toString()
        val destino : String = etDestiny.text.toString()
        val tipoViagem : String = optionTravel.selectedItem.toString()
        val transporte : String = optionTransport.selectedItem.toString()
        if(optionCoin.visibility==View.INVISIBLE){
            moeda = "Real"
        }else{
            moeda = optionCoin.selectedItem.toString()
        }
        val dataI : String = sdf.format(calendarB.time)
        val dataF : String = sdf.format(calendarF.time)
        val viagem = Viagem(0,origem,destino,tipoViagem,transporte,moeda,dataI,dataF)
        viagem.calcularMoedaExterna(this)
        viewModel.insertViagem(viagem)
    }
}