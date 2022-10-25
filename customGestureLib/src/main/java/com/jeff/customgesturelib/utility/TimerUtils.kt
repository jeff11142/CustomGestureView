package com.jeff.customgesturelib.utility

import android.os.CountDownTimer

class TimerUtils {
    private var cdt = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            //do nothing
        }

        override fun onFinish() {
            this.start()
            EmergencyStatusUtils().checkEmergencyStatus()
        }
    }

    private var cdtForce = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            //do nothing
        }

        override fun onFinish() {
            this.start()
            EmergencyStatusUtils().checkEmergencyStatusForceOne()
        }
    }

    fun getCountDownTimer(): CountDownTimer {
        return cdt
    }

    fun getForceCountDownTimer(): CountDownTimer {
        return cdtForce
    }
}