package com.jeff.customgesturelib

import android.app.Application

class GlobalVariable : Application() {
    companion object {
        //存放變數
        var appVersion = ""
        var logoutUnit: (() -> Unit)? = null
        var settingUnit: (() -> Unit)? = null
            get() = {

            }

        var isNowCheckEmergencyStatus: Boolean = false
    }
}