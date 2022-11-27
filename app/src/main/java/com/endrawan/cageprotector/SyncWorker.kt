package com.endrawan.cageprotector

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.endrawan.cageprotector.models.Cage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.CountDownLatch

class SyncWorker(val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val latch = CountDownLatch(1)
        val database = FirebaseDatabase.getInstance().getReference("")
        database.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tempCage = snapshot.getValue(Cage::class.java)
                    if (tempCage != null)
                        Toast.makeText(appContext, "Terdapat perubahan pada Cage!", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(appContext, "Cage tidak memiliki isi!", Toast.LENGTH_SHORT).show()
                    latch.countDown()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(appContext, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                    latch.countDown()
                }
            }
        )
        latch.await()
        return Result.success()
    }
}