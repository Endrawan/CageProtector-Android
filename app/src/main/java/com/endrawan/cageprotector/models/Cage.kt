package com.endrawan.cageprotector.models

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cage(
    var PIR: List<Boolean> = listOf(false, false, false, false),
    var accelerometer: Axis = Axis(),
    var gyroscope: Axis = Axis(),
    @get:PropertyName("system_status") @set:PropertyName("system_status") var systemStatus: Int = 0
) : Parcelable