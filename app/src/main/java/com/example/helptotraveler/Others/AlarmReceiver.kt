package com.example.helptotraveler.Others

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.helptotraveler.R

class AlarmReceiver:BroadcastReceiver() {
    private val channelID = "Notification"

    //Classe que cria a notification

    override fun onReceive(context: Context, intent: Intent) {
        val shared = SharedPreferences(context)
        val id = shared.getInt("id")
        val builder = NotificationCompat.Builder(context,channelID)
            .setSmallIcon(R.drawable.plane)
            .setContentTitle("Help to Traveler")
            .setContentText("Notificação de evento")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val manager = NotificationManagerCompat.from(context)
        if (id != null) {
            manager.notify(id,builder.build())
        }
    }
}