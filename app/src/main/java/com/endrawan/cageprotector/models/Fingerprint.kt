package com.endrawan.cageprotector.models

import android.os.Parcelable
import com.endrawan.cageprotector.Config
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fingerprint(
    var steps: Int = 0,
    @get:PropertyName("enroll_status") @set:PropertyName("enroll_status") var enrollStatus: Int = Config.FINGERPRINT_ENROLL_STATUS_SUCCESS
) : Parcelable