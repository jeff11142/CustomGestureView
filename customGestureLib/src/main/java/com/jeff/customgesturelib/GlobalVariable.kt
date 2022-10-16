package com.jeff.customgesturelib

import android.app.Application

class GlobalVariable : Application() {
    companion object {
        //存放變數
        var appVersion = ""
        var logoutUnit: (() -> Unit)? = null
        var settingUnit: (() -> Unit)? = null

        var isNowCheckEmergencyStatus: Boolean = false
    }

    fun getLogoutUnit(): (() -> Unit)? {
        return logoutUnit
    }

    fun setLogoutUnit(unit: () -> Unit) {
        logoutUnit = unit
    }

    fun getSettingUnit(): (() -> Unit)? {
        return settingUnit
    }

    fun setSettingUnit(unit: () -> Unit) {
        settingUnit = unit
    }

    fun setAppVersion(version: String) {
        appVersion = version
    }

    fun getAppVersion(): String {
        return appVersion
    }

    fun setIsNowCheckEmergencyStatus(status: Boolean) {
        isNowCheckEmergencyStatus = status
    }

    fun getIsNowCheckEmergencyStatus(): Boolean {
        return isNowCheckEmergencyStatus
    }

}