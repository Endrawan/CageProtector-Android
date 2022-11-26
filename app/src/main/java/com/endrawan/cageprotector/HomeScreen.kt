package com.endrawan.cageprotector

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    mainViewModel.startCageListener {
        Log.d("HomeScreen", "Cage value: $it")
        stateCage = it
    }

    Column(
        modifier = modifier
    ) {
        StatusPanel()
        Row {
            AxisCard(
                Axis(stateCage.gyroscope.x, stateCage.gyroscope.y, stateCage.gyroscope.z),
                "Gyroscope",
                R.drawable.ic_gyroscope_24,
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            AxisCard(
                Axis(stateCage.accelerometer.x, stateCage.accelerometer.y, stateCage.accelerometer.z),
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

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
fun HomeScreenPreview() {
    CageProtectorTheme {
        HomeScreen(Modifier.padding(8.dp))
    }
}