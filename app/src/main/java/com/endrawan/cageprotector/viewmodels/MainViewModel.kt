package com.endrawan.cageprotector.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.endrawan.cageprotector.models.Cage
import com.endrawan.cageprotector.sealed.DataState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel {
    private val TAG = "MainViewModel"
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

//    init {
//        fetchDataFromFirebase()
//    }

    fun startCageListener(onCageChanged: (cage: Cage) -> Unit) {
        val database = FirebaseDatabase.getInstance().getReference("")
        database.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tempCage = snapshot.getValue(Cage::class.java)
                    if (tempCage != null)
                        onCageChanged(tempCage)
                    else
                        Log.d(TAG, "Cage tidak memiliki isi!")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "onCancelled: ${error.message}")
                }
            }
        )
    }

//    private fun fetchDataFromFirebase() {
//        lateinit var tempCage: Cage
//        response.value = DataState.Loading
//        FirebaseDatabase.getInstance().getReference("").addValueEventListener(
//            object: ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val cage = snapshot.getValue(Cage::class.java)
//                    if (cage != null)
//                        tempCage = cage
//                    response.value = DataState.Success(tempCage)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    response.value = DataState.Failure(error.message)
//                }
//
//            }
//        )
//    }
}