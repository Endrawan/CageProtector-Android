package com.endrawan.cageprotector.models

import android.os.Parcelable
import com.endrawan.cageprotector.MPU6050
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Cage(
    var PIR: List<Boolean> = listOf(false, false, false, false),
    @get:PropertyName("base_mpu6050") @set:PropertyName("base_mpu6050") var baseMPU6050: MPU6050 = MPU6050(),
    var mpu6050: MPU6050 = MPU6050(),
    @get:PropertyName("system_status") @set:PropertyName("system_status") var systemStatus: Int = 0,
    var fingerprint: Fingerprint = Fingerprint(),
    @get:PropertyName("alert_status") @set:PropertyName("alert_status") var alertStatus: Boolean = false,
    @get:PropertyName("buzzer_status") @set:PropertyName("buzzer_status") var buzzerStatus: Boolean = false,
    @get:PropertyName("last_updated_arduino") @set:PropertyName("last_updated_arduino") var lastUpdatedArduino: String = "10-04-2020 10:51:20",
    @get:PropertyName("last_updated_android") @set:PropertyName("last_updated_android") var lastUpdatedAndroid: String = "10-04-2020 10:51:21"
) : Parcelable {

    fun getLastUpdatedCalendar(): Calendar {
        val date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).parse(lastUpdatedArduino)!!
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    fun getDateString(): String {
        val cal = getLastUpdatedCalendar()
        val dateFormat = SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT'Z yyyy")
        return dateFormat.format(cal.time)
    }
}