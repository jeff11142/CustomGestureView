package com.jeff.customgesturelib.setting

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeff.customgesturelib.R
import com.jeff.customgesturelib.utility.PatternLockUtils
import com.jeff.customgesturelib.utility.PreferenceContract
import com.jeff.customgesturelib.utility.PreferenceUtils

class SettingAccountDialog(context: Context) : AlertDialog.Builder(context) {

    fun show(isCancelable: Boolean , version: String?) {
        if (isHaveActivatedAccount()) {
            val customLayout =
                LayoutInflater.from(context).inflate(R.layout.dialog_special_account_setting, null)
            val builder = AlertDialog.Builder(context)
            builder.setView(customLayout)

            val alertDialog: AlertDialog = builder.create()
            initView(customLayout, alertDialog, version)
            alertDialog.setCancelable(isCancelable)
            alertDialog.show()
        } else {
            Toast.makeText(context, context.getString(R.string.LoginFirst), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun initView(view: View?, alertDialog: AlertDialog, version: String?) {
        view?.apply {
            val settingAdapter = context?.let { SettingAdapter(it) }
            val recyclerView = findViewById<RecyclerView>(R.id.rv_user_list)
            val btOk = findViewById<TextView>(R.id.tv_save)
            val btCancel = findViewById<TextView>(R.id.tv_cancel)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = settingAdapter
            }

            btOk.setOnClickListener {
                PatternLockUtils.setUserIdSet(settingAdapter?.checkedUserIdList, context)
                alertDialog.dismiss()
            }

            btCancel.setOnClickListener {
                alertDialog.dismiss()
            }

            settingAdapter?.updateList(PatternLockUtils.getActiveAccountList())

            PreferenceUtils.putString(
                PreferenceContract.KEY_APP_VERSION,
                version, context
            )
        }
    }

//    private fun getAllUserTGInfoList(): MutableList<TLRPC.User?> {
//        val userInfoList: MutableList<TLRPC.User?> = mutableListOf()
//        for (a in 0 until UserConfig.MAX_ACCOUNT_COUNT) {
//            if (UserConfig.getInstance(a).currentUser != null) {
//                userInfoList.add(UserConfig.getInstance(a).currentUser)
//            }
//        }
//        return userInfoList
//    }

    private fun isHaveActivatedAccount(): Boolean {
        return PatternLockUtils.getActiveAccountList().size > 0
    }
}