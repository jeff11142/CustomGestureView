package com.jeff.customgestureview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.jeff.customgesturelib.CustomGestureActivity
import com.jeff.customgesturelib.setting.UserData
import com.jeff.customgesturelib.utility.PatternLockUtils
import com.jeff.customgesturelib.utility.UnitUtils

class MainActivity : AppCompatActivity() {
    private val startActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Toast.makeText(this, it.resultCode.toString(), Toast.LENGTH_LONG)
            when (it.resultCode) {
                RESULT_OK -> {
                    Toast.makeText(this, "LOGOUT", Toast.LENGTH_LONG).show()
                }

                PatternLockUtils.GESTURE_FORGOT -> {
                    Toast.makeText(this, "FORGOT ", Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAccountList()
    }

    private fun setAccountList() {
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

        UnitUtils.logoutUnit =  {
            Log.e("jeff", "logout")
        }

        UnitUtils.settingUnit = {
            Log.e("jeff", "setting")
        }

    }

    override fun onResume() {
        super.onResume()
        if (PatternLockUtils.isNeedtoShowGestureLock) {
            UnitUtils.appVersion = "8.1.1"
            startActivity.launch(Intent(this, CustomGestureActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        PatternLockUtils.isNeedtoShowGestureLock = true
    }
}