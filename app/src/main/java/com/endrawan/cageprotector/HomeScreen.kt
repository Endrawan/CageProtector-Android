package com.endrawan.cageprotector

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.endrawan.cageprotector.models.Axis
import com.endrawan.cageprotector.models.Cage
import com.endrawan.cageprotector.ui.theme.CageProtectorTheme
import com.endrawan.cageprotector.viewmodels.MainViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel()
) {
    var stateCage by rememberSaveable { mutableStateOf(Cage()) }
    var lockStatus by rememberSaveable {
        mutableStateOf(stateCage.systemStatus != Config.SYSTEM_STATUS_DOOR_OPENED)
    }
    var fingerprintDialogStatus by rememberSaveable { mutableStateOf(false) }
    var calibrateMPU6050DialogStatus by rememberSaveable { mutableStateOf(false) }

    mainViewModel.startCageListener {
        Log.d("HomeScreen", "Cage value: $it")
        stateCage = it
        lockStatus = stateCage.systemStatus != Config.SYSTEM_STATUS_DOOR_OPENED
    }

    Column(
        modifier = modifier
    ) {
        StatusPanel(stateCage.systemStatus)
        AlertStatusText(
            alertStatus = stateCage.alertStatus,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Row {
            AxisCard(
                stateCage.mpu6050.gyroscope,
                "Gyroscope",
                R.drawable.ic_gyroscope_24,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            AxisCard(
                stateCage.mpu6050.accelerometer,
                "Accelerometer",
                R.drawable.ic_accelerometer_24,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        PIRCard(stateCage.PIR, Modifier.padding(8.dp))
        Row {
            ButtonCard(
                R.drawable.ic_fingerprint_24,
                "Ubah Sidik Jari",
                {
                    fingerprintDialogStatus = true
                    mainViewModel.fingerprintStartEnrollMode()
                },
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            ButtonDualStatusCard(
                currentState = lockStatus,
                trueImageId = R.drawable.ic_unlock_24,
                falseImageId = R.drawable.ic_lock_24,
                trueText = "Buka kandang",
                falseText = "Kunci kandang",
                trueOnClick = {
                    mainViewModel.upsertValue(
                        Config.DB_ENDPOINT_SYSTEM_STATUS,
                        Config.SYSTEM_STATUS_DOOR_OPENED
                    )
                },
                falseOnClick = {
                    mainViewModel.upsertValue(
                        Config.DB_ENDPOINT_SYSTEM_STATUS,
                        Config.SYSTEM_STATUS_STANDBY
                    )
                },
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            ButtonCard(
                R.drawable.ic_check_24, "Menjadi aman",
                {
                    mainViewModel.upsertValue(
                        Config.DB_ENDPOINT_SYSTEM_STATUS,
                        Config.SYSTEM_STATUS_STANDBY
                    )
                },
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
        }
        Row {
            ButtonCard(
                R.drawable.ic_sensor_24, "Kalibrasi MPU6050",
                {
                    calibrateMPU6050DialogStatus = true
                    mainViewModel.upsertValue(
                        Config.DB_ENDPOINT_SYSTEM_STATUS,
                        Config.SYSTEM_STATUS_CALIBRATE_MPU6050
                    )
                },
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            ButtonDualStatusCard(
                currentState = stateCage.alertStatus,
                trueImageId = R.drawable.ic_close_24,
                falseImageId = R.drawable.ic_check_box_24,
                trueText = "Matikan Pengaman",
                falseText = "Hidup Pengaman",
                trueOnClick = {
                    mainViewModel.upsertValue(
                        Config.DB_ENDPOINT_ALERT_STATUS,
                        false
                    )
                },
                falseOnClick = {
                    mainViewModel.upsertValue(
                        Config.DB_ENDPOINT_ALERT_STATUS,
                        true
                    )
                }, Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
        }

        FingerprintEnrollDialog(fingerprintDialogStatus, stateCage.fingerprint,{
            mainViewModel.fingerprintStopEnrollMode()
            fingerprintDialogStatus = false
        }, {
            mainViewModel.fingerprintStopEnrollMode()
            fingerprintDialogStatus = false
        }, Modifier.padding(8.dp))

        CalibrateMPU6050Dialog(
            calibrateMPU6050DialogStatus,
            stateCage.baseMPU6050,
            onDismiss = {
                calibrateMPU6050DialogStatus = false
                mainViewModel.upsertValue(
                    Config.DB_ENDPOINT_SYSTEM_STATUS,
                    Config.SYSTEM_STATUS_STANDBY
                )
            },
            onDone = {
                calibrateMPU6050DialogStatus = false
                mainViewModel.upsertValue(
                    Config.DB_ENDPOINT_SYSTEM_STATUS,
                    Config.SYSTEM_STATUS_STANDBY
                )
            },
            Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
fun HomeScreenPreview() {
    CageProtectorTheme {
        HomeScreen(Modifier.padding(8.dp))
    }
}