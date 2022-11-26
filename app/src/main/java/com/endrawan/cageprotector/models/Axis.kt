package com.endrawan.cageprotector.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Axis(
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f
) : Parcelable