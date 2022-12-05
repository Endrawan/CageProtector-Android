package com.endrawan.cageprotector

import android.os.Parcelable
import com.endrawan.cageprotector.models.Axis
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MPU6050(
    var accelerometer: Axis = Axis(),
    var gyroscope: Axis = Axis(),
) : Parcelable