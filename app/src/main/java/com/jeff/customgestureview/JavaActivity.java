package com.jeff.customgestureview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jeff.customgesturelib.CustomGestureActivity;
import com.jeff.customgesturelib.utility.PatternLockUtils;
import com.jeff.customgesturelib.utility.UnitUtils;

import kotlin.Unit;

public class JavaActivity extends Activity {
    private final UnitUtils globalVariable = new UnitUtils();

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

    @Override
    protected void  onPause() {
        super.onPause();
        PatternLockUtils.isNeedtoShowGestureLock = true;
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
