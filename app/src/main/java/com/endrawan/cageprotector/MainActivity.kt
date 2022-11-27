package com.endrawan.cageprotector

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
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

        Intent(this, SyncFirebaseService::class.java).also {
            startService(it)
        }
//        workManagerTest(applicationContext)
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

fun workManagerTest(appContext: Context) {
    val syncWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<SyncWorker>()
        .build()
    WorkManager.getInstance(appContext).enqueue(syncWorkRequest)
}