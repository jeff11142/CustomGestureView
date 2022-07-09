package com.jeff.customgestureview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jeff.customgesturelib.CustomGestureActivity
import com.jeff.customgesturelib.setting.UserData
import com.jeff.customgesturelib.utility.PatternLockUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (PatternLockUtils.isNeedtoShowGestureLock) {
            val intent = Intent(this, GestureActivity::class.java)
            intent.putExtra(PatternLockUtils.appVersion, "8.1.1")
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        PatternLockUtils.isNeedtoShowGestureLock = true
    }
}