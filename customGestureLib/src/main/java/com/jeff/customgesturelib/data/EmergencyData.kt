package com.jeff.customgesturelib.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class EmergencyData (
    var data: Data,
    var status: Int = 0
)
@Parcelize
data class Data(
    var emergency: String,
    var date: String
) : Parcelable