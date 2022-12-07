package com.endrawan.cageprotector.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.endrawan.cageprotector.Config
import com.endrawan.cageprotector.models.Cage
import com.endrawan.cageprotector.models.Fingerprint
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"
    private val database = FirebaseDatabase.getInstance()

    init{
    }

    fun startCageListener(onCageChanged: (cage: Cage) -> Unit) {
        val ref = database.getReference("")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tempCage = snapshot.getValue(Cage::class.java)
                    if (tempCage != null)
                        onCageChanged(tempCage)
                    else
                        Log.d(TAG, "Cage tidak memiliki isi!")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "onCancelled: ${error.message}")
                }
            }
        )
    }

    fun upsertValue(endpoint: String, value:Any?) {
        val ref = database.getReference(endpoint)
        ref.setValue(value)
        updateTimestamp()
    }

    fun fingerprintStartEnrollMode() {
//        val fingerprint = Fingerprint(enrollStatus = Config.FINGERPRINT_ENROLL_STATUS_ENROLLING)
        val ref = database.getReference(Config.DB_ENPOINT_FINGERPRINT_ENROLLMENT_STATUS)
        ref.setValue(Config.FINGERPRINT_ENROLL_STATUS_ENROLLING)
        upsertValue(Config.DB_ENDPOINT_SYSTEM_STATUS, Config.SYSTEM_STATUS_FINGERPRINT_ENROLL)
    }

    fun fingerprintStopEnrollMode() {
//        val fingerprint = Fingerprint(enrollStatus = Config.FINGERPRINT_ENROLL_STATUS_ENROLLING)
        val ref = database.getReference(Config.DB_ENPOINT_FINGERPRINT_ENROLLMENT_STATUS)
        ref.setValue(0)
        upsertValue(Config.DB_ENDPOINT_SYSTEM_STATUS, Config.SYSTEM_STATUS_STANDBY)
    }

    private fun getCurrentTimestamp(): String {
        val currentTime = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(currentTime)
    }

    private fun updateTimestamp() {
        val ref = database.getReference(Config.DB_ENDPOINT_LAST_UPDATED_ANDROID)
        ref.setValue(getCurrentTimestamp())
    }
}