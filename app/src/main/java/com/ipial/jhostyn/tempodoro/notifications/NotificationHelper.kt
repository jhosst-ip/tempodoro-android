package com.ipial.jhostyn.tempodoro.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.ipial.jhostyn.tempodoro.R

class NotificationHelper(private val context: Context) {

    companion object{
        const val CHANNEL_ID="pomodoro_channel"
    }

    init{

        val channel=NotificationChannel(
            CHANNEL_ID,
            "Pomodoro",
            NotificationManager.IMPORTANCE_HIGH
        )

        val manager=context.getSystemService(NotificationManager::class.java)

        manager.createNotificationChannel(channel)

    }

    fun mostrarNotificacion(titulo: String, mensaje: String) {

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(NotificationManager::class.java)

        manager.notify(System.currentTimeMillis().toInt(), notification)

    }

}