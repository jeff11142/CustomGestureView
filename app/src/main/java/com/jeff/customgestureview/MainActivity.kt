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
//        setGestureInfo()
    }

    private fun setGestureInfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            PatternLockUtils.setSpecialGestureEvent {
                Toast.makeText(this, "logout", Toast.LENGTH_LONG).show()
            }
        }

        //整理UserData
        val data = UserData()
        data.firstName = "Jeff"
        data.lastName = "Liu"
        data.id = "123456"

        val data1 = UserData()
        data1.firstName = "Jeff"
        data1.lastName = "Wang"
        data1.id = "654321"

        val list: ArrayList<UserData> = arrayListOf()
        list.add(data)
        list.add(data1)

        PatternLockUtils.setActiveAccountList(list)
    }

    override fun onResume() {
        super.onResume()
        if (PatternLockUtils.isNeedtoShowGestureLock) {
            val intent = Intent(this, CustomGestureActivity::class.java)
            intent.putExtra(PatternLockUtils.appVersion, "8.1.1")
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        PatternLockUtils.isNeedtoShowGestureLock = true
    }
}