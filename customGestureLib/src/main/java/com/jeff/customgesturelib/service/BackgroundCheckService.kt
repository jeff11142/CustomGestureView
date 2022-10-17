package com.jeff.customgesturelib.service

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.jeff.customgesturelib.utility.EmergencyStatusUtils


class BackgroundCheckService : Service() {
    companion object {
        const val TAG = "BackgroundCheckService"
    }

    private val binder = CustomBinder()
    private var cdt: CountDownTimer? = null

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    inner class CustomBinder : Binder() {
        val service: BackgroundCheckService
            get() = this@BackgroundCheckService
    }

    override fun onCreate() {
        super.onCreate()
        checkEmergencyStatus()
        createNewCutDownTimer()
    }

    fun createNewCutDownTimer() {
        cdt = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isServiceRunning()) {
                    cancel()
                }
            }

            override fun onFinish() {
                if (isServiceRunning()) {
                    checkEmergencyStatus()
                    createNewCutDownTimer()
                }
            }
        }.start()
    }

    private fun checkEmergencyStatus() {
        Log.d(TAG, "checkEmergencyStatus from service")
        EmergencyStatusUtils().checkEmergencyStatus()
    }

    private fun isServiceRunning(): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (service.service.className.contains(BackgroundCheckService::class.java.simpleName)) {
                return true;
            }
        }
        return false
    }

}