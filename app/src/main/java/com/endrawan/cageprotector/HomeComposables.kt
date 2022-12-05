package com.endrawan.cageprotector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.endrawan.cageprotector.models.Axis
import com.endrawan.cageprotector.models.Cage
import com.endrawan.cageprotector.ui.theme.CageProtectorTheme

@Composable
fun StatusPanel(systemStatus: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "App Icon"
        )

        Column(
            modifier = modifier.padding(8.dp)
        ) {
            lateinit var statusText: String

            Text(text = "Status")
            when (systemStatus) {
                Config.SYSTEM_STATUS_STANDBY -> statusText = "Aman"
                Config.SYSTEM_STATUS_WARNING -> statusText = "Peringatan"
                Config.SYSTEM_STATUS_DANGER -> statusText = "Bahaya"
                Config.SYSTEM_STATUS_DOOR_OPENED -> statusText = "Kandang dibuka"
                Config.SYSTEM_STATUS_FINGERPRINT_ENROLL -> statusText = "Mengubah fingeprint"
                Config.SYSTEM_STATUS_CALIBRATE_MPU6050 -> statusText = "Kalibrasi MPU6050"
                Config.SYSTEM_STATUS_30_MIN_SAFE -> statusText = "Aman 30 ke depan"
            }
            Text(text = statusText, style = MaterialTheme.typography.h1)
        }
    }
}

@Composable
fun AlertStatusText(alertStatus: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Status pengaman: ", style = MaterialTheme.typography.h5)
        Text(if(alertStatus) "Aktif" else "Mati", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun AxisCard(axis: Axis, title: String, imageId: Int, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "$title Image"
                )
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h5
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "X: ${axis.x}", style = MaterialTheme.typography.body1)
            Text(text = "Y: ${axis.y}", style = MaterialTheme.typography.body1)
            Text(text = "Z: ${axis.z}", style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun ButtonCard(
    imageId: Int,
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painterResource(id = imageId),
                contentDescription = "$buttonText image",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buttonText,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }
//    Surface(
//        shape = MaterialTheme.shapes.small,
//        modifier = modifier,
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Image(
//                painterResource(id = imageId),
//                contentDescription = "$buttonText image",
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = buttonText, style = MaterialTheme.typography.h5)
//        }
//    }
}

@Composable
fun ButtonDualStatusCard(
    currentState: Boolean,
    trueImageId: Int,
    falseImageId: Int,
    trueText: String,
    falseText: String,
    trueOnClick: () -> Unit,
    falseOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var imageId = 0
    val buttonText: String
    lateinit var onClick: () -> Unit

    if (currentState) {
        imageId = trueImageId
        buttonText = trueText
        onClick = trueOnClick
    } else {
        imageId = falseImageId
        buttonText = falseText
        onClick = falseOnClick
    }

    ButtonCard(imageId = imageId, buttonText = buttonText, onClick = onClick, modifier = modifier)
}

@Composable
fun PIRCard(PIR: List<Boolean>, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = R.drawable.ic_pir_24),
                    contentDescription = "PIR image"
                )
                Text(
                    text = "PIR Sensor",
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h5
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    "1: ${if (PIR[0]) "Aktif" else "Mati"}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    "2: ${if (PIR[1]) "Aktif" else "Mati"}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    "3: ${if (PIR[2]) "Aktif" else "Mati"}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    "4: ${if (PIR[3]) "Aktif" else "Mati"}",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun FingerprintEnrollDialog(dialogStatus: Boolean, onDismiss: () -> Unit, onDone:() -> Unit, modifier: Modifier = Modifier) {
    if (dialogStatus) {
        Dialog(
            onDismissRequest = onDismiss,
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 8.dp,
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Silahkan letakkan sidik jari Anda pada sensor",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_fingerprint_24),
                        contentDescription = "Fingerprint image",
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Status enrollment")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = onDone
                    ) {
                        Text("Selesai")
                    }
                }
            }
        }
    }
}


@Composable
fun CalibrateMPU6050Dialog(dialogStatus: Boolean, baseMPU6050: MPU6050, onDismiss: () -> Unit, onDone:() -> Unit, modifier: Modifier = Modifier) {
    if (dialogStatus) {
        Dialog(
            onDismissRequest = onDismiss,
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 8.dp,
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Silahkan tempatkan kandang di tempat",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_sensor_24),
                        contentDescription = "MPU6050 image",
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = """
                            Accel -> x:${baseMPU6050.accelerometer.x} y:${baseMPU6050.accelerometer.y} z:${baseMPU6050.accelerometer.z}
                            Gyro -> x:${baseMPU6050.gyroscope.x} y:${baseMPU6050.gyroscope.y} z:${baseMPU6050.gyroscope.z} 
                        """.trimIndent()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = onDone
                    ) {
                        Text("Selesai")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StatusPanelPreview() {
    CageProtectorTheme {
        StatusPanel(Config.SYSTEM_STATUS_STANDBY)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun GyroscopeCardPreview() {
    CageProtectorTheme {
        AxisCard(
            Axis(25.5f, 69.6f, 75.6f),
            "Gyroscope",
            R.drawable.ic_gyroscope_24,
            Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AccelerometerCardPreview() {
    CageProtectorTheme {
        AxisCard(
            Axis(73.5f, 32.6f, 30.6f),
            "Accelerometer",
            R.drawable.ic_accelerometer_24,
            Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ButtonCardPreview() {
    CageProtectorTheme {
        ButtonCard(R.drawable.ic_lock_24, "Kunci Kandang", {}, Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun PIRCardPreview() {
    CageProtectorTheme {
        PIRCard(PIR = listOf(true, false, true, false), Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FingerprintEnrollDialogPreview() {
    CageProtectorTheme {
        FingerprintEnrollDialog(true, {}, {}, Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CalibrateMPU6050DialogPreview() {
    CageProtectorTheme {
        CalibrateMPU6050Dialog(true, MPU6050(), {}, {}, Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlertStatusTextPreview() {
    CageProtectorTheme {
        AlertStatusText(alertStatus = true, modifier = Modifier.padding(8.dp))
    }
}
