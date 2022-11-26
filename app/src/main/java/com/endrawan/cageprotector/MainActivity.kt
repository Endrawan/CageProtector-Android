package com.endrawan.cageprotector

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endrawan.cageprotector.models.Axis
import com.endrawan.cageprotector.ui.theme.CageProtectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CageProtectorTheme {
                HomeScreen(Modifier.padding(8.dp))
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    HomeScreen()
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CageProtectorTheme {
        Greeting("Android")
    }
}

@Composable
fun StatusPanel(modifier: Modifier = Modifier) {
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
            Text(text = "Status")
            Text(text = "Aman", style = MaterialTheme.typography.h1)
        }
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "X: ${axis.x}", style = MaterialTheme.typography.body1)
                Text(text = "Y: ${axis.y}", style = MaterialTheme.typography.body1)
                Text(text = "Z: ${axis.z}", style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
fun ButtonCard(state: Boolean, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painterResource(id = R.drawable.ic_lock_24),
                contentDescription = "Lock Button",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Buka Kunci", style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
fun PIRCard(PIR: BooleanArray, modifier: Modifier = Modifier) {
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
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        StatusPanel()
        Row {
            AxisCard(
                Axis(25.5f, 69.6f, 75.6f),
                "Gyroscope",
                R.drawable.ic_gyroscope_24,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            AxisCard(
                Axis(73.5f, 32.6f, 30.6f),
                "Accelerometer",
                R.drawable.ic_accelerometer_24,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        PIRCard(PIR = booleanArrayOf(true, false, true, false), Modifier.padding(8.dp))
        Row {
            ButtonCard(
                state = false,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            ButtonCard(
                state = false,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            ButtonCard(
                state = false,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Row {
            ButtonCard(
                state = false,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            ButtonCard(
                state = false,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            ButtonCard(
                state = false,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusPanelPreview() {
    CageProtectorTheme {
        StatusPanel()
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
        ButtonCard(state = false, Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun PIRCardPreview() {
    CageProtectorTheme {
        PIRCard(PIR = booleanArrayOf(true, false, true, false), Modifier.padding(8.dp))
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
fun HomeScreenPreview() {
    CageProtectorTheme {
        HomeScreen(Modifier.padding(8.dp))
    }
}