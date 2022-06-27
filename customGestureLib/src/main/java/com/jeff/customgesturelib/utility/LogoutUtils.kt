package com.jeff.customgesturelib.utility

import android.util.Log

class LogoutUtils {
    public var logoutUnit: () -> Unit = {}

    @JvmName("setLogoutUnit1")
    fun setLogoutUnit(unit: () -> Unit){
        logoutUnit = unit
        unit
        Log.e("jeff", "setLogoutUnit")
    }

    @JvmName("getLogoutUnit1")
    fun getLogoutUnit(): () -> Unit {
        return logoutUnit
    }
}