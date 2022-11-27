package com.endrawan.cageprotector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class StartSyncFirebaseAtBoot : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Intent(context, SyncFirebaseService::class.java).also {
            context?.startService(it)
        }
    }
}