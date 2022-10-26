package com.jeff.customgesturelib.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class EmergencyData (
    var data: Data,
    var status: String = "0"
)
@Parcelize
data class Data(
    var emergency: Int,
    var date: String
) : Parcelable