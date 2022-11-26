package com.endrawan.cageprotector.sealed

import com.endrawan.cageprotector.models.Cage

sealed class DataState {
    class Success(val data: Cage) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}