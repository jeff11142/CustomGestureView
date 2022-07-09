package com.jeff.customgestureview

import com.jeff.customgesturelib.CustomGestureActivity
import com.jeff.customgesturelib.setting.UserData

class GestureActivity: CustomGestureActivity() {

    override fun onLogOutEvent() {
        super.onLogOutEvent()
        //登出事件
    }

    override fun onForgotEvent() {
        super.onForgotEvent()
        //忘記手勢事件
    }

    override fun getAccountList(): MutableList<UserData> {
        //整理UserData
        val data = UserData()
        data.firstName = "Jeff"
        data.lastName = "Liu"
        data.id = "123456"

        val data1 = UserData()
        data1.firstName = "Jeff"
        data1.lastName = "Wang"
        data1.id = "654321"

        val list: MutableList<UserData> = mutableListOf()
        list.add(data)
        list.add(data1)
        return list
    }
}