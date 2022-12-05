package com.endrawan.cageprotector

object Config {
    val SYSTEM_STATUS_STANDBY = 0
    val SYSTEM_STATUS_WARNING = 1
    val SYSTEM_STATUS_DANGER = 2
    val SYSTEM_STATUS_DOOR_OPENED = 3
    val SYSTEM_STATUS_30_MIN_SAFE = 4
    val SYSTEM_STATUS_FINGERPRINT_ENROLL = 5
    val SYSTEM_STATUS_CALIBRATE_MPU6050 = 6

    val FINGERPRINT_STATUS_LISTENING = 0 // Listening
    val FINGERPRINT_STATUS_ENROLL = 1
    val FINGERPRINT_ENROLL_STATUS_READY = 0 // Ready to enroll
    val FINGERPRINT_ENROLL_STATUS_SUCCESS = 1 // Enroll success
    val FINGERPRINT_ENROLL_STATUS_FAILED = 2 // Enroll failed try again

    val DB_ENDPOINT_SYSTEM_STATUS = "system_status"
    val DB_ENDPOINT_ALERT_STATUS = "alert_status"
    val DB_ENDPOINT_FINGERPRINT = "fingerprint"
    val DB_ENDPOINT_LAST_UPDATED_ANDROID = "last_updated_android"
}