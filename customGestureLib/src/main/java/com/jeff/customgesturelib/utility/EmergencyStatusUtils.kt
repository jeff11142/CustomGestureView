package com.jeff.customgesturelib.utility

import android.util.Log
import com.jeff.customgesturelib.network.ApiService
import com.jeff.customgesturelib.network.AppClientManager
import com.jeff.customgesturelib.data.EmergencyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmergencyStatusUtils {
    companion object {
        const val TAG = "EmergencyStatusUtils"
    }

    fun checkEmergencyStatus() {
        val apiService = AppClientManager.emergencyClient.create(ApiService::class.java)
        apiService.getEmergencyData().enqueue(object : Callback<EmergencyData> {
            override fun onResponse(
                call: Call<EmergencyData>,
                response: Response<EmergencyData>
            ) {
                //當data陣列中emergency的值為1時，登出所有帳號

                val data = response.body()?.data

                Log.d(TAG, "emergency = ${data?.emergency}")

                if (data?.emergency == 1) {
                    UnitUtils.forceLogoutUnit?.let { it() }
                }
            }

            override fun onFailure(call: Call<EmergencyData>, t: Throwable) {
                //介接失敗，不做任何動作
                Log.e(TAG, "error = ${t.message}")
            }
        })
    }
}