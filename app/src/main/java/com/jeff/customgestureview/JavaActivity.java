package com.jeff.customgestureview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jeff.customgesturelib.CustomGestureActivity;
import com.jeff.customgesturelib.GlobalVariable;
import com.jeff.customgesturelib.utility.PatternLockUtils;

import kotlin.Unit;

public class JavaActivity extends Activity {
    private final GlobalVariable globalVariable = new GlobalVariable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalVariable.setLogoutUnit(this::onLogoutUnit);
        globalVariable.setSettingUnit(this::onSettingUnit);
        globalVariable.setAppVersion("8.1.1");
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (PatternLockUtils.isNeedtoShowGestureLock) {
            startActivity(new Intent(this, CustomGestureActivity.class));
        }
    }

    private Unit onLogoutUnit() {
        //do something here
        Log.e("jeff", "LogoutUnit");
        return Unit.INSTANCE;
    }

    private Unit onSettingUnit() {
        //do something here
        Log.e("jeff", "SettingUnit");
        return Unit.INSTANCE;
    }
}
