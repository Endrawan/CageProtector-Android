package com.endrawan.cageprotector.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cage(
    var PIR: List<Boolean> = listOf(false, false, false, false),
    var accelerometer: Axis = Axis(),
    var gyroscope: Axis = Axis(),
    var systemStatus: Int = 1
) : Parcelable