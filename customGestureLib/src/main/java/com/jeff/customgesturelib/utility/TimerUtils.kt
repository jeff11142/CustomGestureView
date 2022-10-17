package com.jeff.customgesturelib.utility

import android.os.CountDownTimer

class TimerUtils {
    var cdt = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            //do nothing
        }

        override fun onFinish() {
            this.start()
            EmergencyStatusUtils().checkEmergencyStatus()
        }
    }

    fun getCountDownTimer(): CountDownTimer{
        return cdt
    }
}