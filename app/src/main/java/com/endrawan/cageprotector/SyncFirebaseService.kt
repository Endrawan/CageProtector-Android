package com.endrawan.cageprotector

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.endrawan.cageprotector.models.Cage
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SyncFirebaseService : Service() {
    private val CHANNEL_ID = "CageProtectorUpdate"
    private val NOTIFICATION_ID = 2412

    private val TAG = "SyncFirebaseService"

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        val database = FirebaseDatabase.getInstance().getReference("")
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cage = snapshot.getValue(Cage::class.java)!!
                Log.d(TAG, "Cage data received: $cage")
                when(cage.systemStatus) {
                    Config.SYSTEM_STATUS_WARNING -> {
                        pushNotification(cage)
                    }
                    Config.SYSTEM_STATUS_DANGER -> {
                        pushNotification(cage)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "FirebaseError: ${error.message}")
            }

        })

        Log.d(TAG, "SyncFirebaseService successfully started")
    }

    private fun pushNotification(cage: Cage) {
        createNotificationChannel()

        var message = ""
        var title = ""

        when(cage.systemStatus) {
            Config.SYSTEM_STATUS_WARNING -> {
                message =
                    "Hati-hati! Ada sesuatu mencurigakan di kandang burungmu!"
                title =
                    "Peringatan kandang"
            }
            Config.SYSTEM_STATUS_DANGER -> {
                message =
                    "Bahaya! Kandang burung Anda sedang dicuri!"
                title =
                    "Kandang dicuri!"
            }
        }


        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, intent, 0)
        }


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.icon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}