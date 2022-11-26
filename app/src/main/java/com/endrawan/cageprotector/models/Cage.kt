package com.endrawan.cageprotector.models

data class Cage(
    var PIR: BooleanArray,
    var accelerometer: Axis,
    var gyroscope: Axis,
    var systemStatus: Int
)