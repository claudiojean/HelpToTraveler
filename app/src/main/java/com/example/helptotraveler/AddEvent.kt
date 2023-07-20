package com.example.helptotraveler

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import com.example.helptotraveler.Model.Evento
import com.example.helptotraveler.Model.Viagem
import com.example.helptotraveler.Others.AlarmReceiver
import com.example.helptotraveler.Others.SharedPreferences
import com.example.helptotraveler.db.HttApplication
import com.example.helptotraveler.db.HttViewModel
import com.example.helptotraveler.db.HttViewModelFactory
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.SimpleDateFormat
import java.util.*

class AddEvent : AppCompatActivity() {

    lateinit var datepicker: DatePicker
    lateinit var viagem: Viagem

    private val viewModel: HttViewModel by viewModels {
        HttViewModelFactory((application as HttApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        createChannel()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        var  tpHorarioInicial = findViewById<TimePicker>(R.id.tpHorarioInicial)
        var  tpHorarioFinal = findViewById<TimePicker>(R.id.tpHorarioFinal)
        //Mudando o padrão do timepicker para o modo de 24noras em vez de mostrar AM e PM
        tpHorarioInicial.setIs24HourView(true)
        tpHorarioFinal.setIs24HourView(true)
        viagem = (intent.getSerializableExtra("VIAGEM") as? Viagem)!!
        btnSalvar.setOnClickListener {
            insertToDataBase()
        }
    }

    //método criado para inserção dos dados no banco de dados
    private fun insertToDataBase(){

        datepicker = findViewById(R.id.dpData)
        val day = datepicker.dayOfMonth
        val month = datepicker.month
        val year = datepicker.year
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val descricao = etDescricao.text.toString()
        val local = etLocal.text.toString()
        val data = sdf.format(calendar.time)
        val horaInicial = tpHorarioInicial.hour
        val minutoInicial = tpHorarioInicial.minute
        val horaFinal = tpHorarioFinal.hour
        val minutoFinal = tpHorarioFinal.minute
        calendar.set(year,month,day,tpHorarioInicial.hour,tpHorarioInicial.minute,1)


        val intent = Intent(this, AlarmReceiver::class.java)
        val manager = getSystemService(ALARM_SERVICE) as AlarmManager
        val shared = SharedPreferences(this)
        val alarmIntent = shared.getInt("id")?.let { PendingIntent.getBroadcast(this, it, intent, 0) }

        manager.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,alarmIntent)

        var newId = (shared.getInt("id")?.plus(1))
        if (newId != null) {
            shared.saveInt("id",newId)
        }

        val hInicial = (horaInicial*60)+minutoInicial
        val hFinal = (horaFinal*60)+minutoFinal
        val evento = Evento(0,descricao,local,data,hInicial,hFinal, viagem!!.viagemId)
        viewModel.insertEvento(evento)
    }

    //Método criado para criar o canal da notificação e chamado no onCreate

    private fun createChannel(){

        val name:CharSequence = "ReminderChannel"
        val desc = "Channel for ReminderChannel"
        val import:Int = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("Notification",name,import)
        channel.description = desc
        val manager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}

