package com.jeff.customgestureview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.jeff.customgesturelib.CustomGestureActivity;
import com.jeff.customgesturelib.setting.UserData;
import com.jeff.customgesturelib.utility.PatternLockUtils;
import com.jeff.customgesturelib.utility.TimerUtils;
import com.jeff.customgesturelib.utility.UnitUtils;

import java.util.ArrayList;

import kotlin.Unit;

public class JavaActivity extends Activity {
    private final UnitUtils globalVariable = new UnitUtils();
    private final CountDownTimer timerUtils = new TimerUtils().getCountDownTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalVariable.setLogoutUnit(this::onLogoutUnit);
        globalVariable.setSettingAccountUnit(this::onSettingAccountUnit);
        globalVariable.setAppVersion("8.1.1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PatternLockUtils.isNeedtoShowGestureLock) {
            startActivity(new Intent(this, CustomGestureActivity.class));
        }
        timerUtils.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PatternLockUtils.isNeedtoShowGestureLock = true;
        timerUtils.cancel();
    }

    private Unit onLogoutUnit() {
        return Unit.INSTANCE;
    }

    private Unit onSettingAccountUnit() {
        UserData data = new UserData();
        data.setFirstName("Jeff");
        data.setLastName("Liu");
        data.setId("123456");

        UserData data1 = new UserData();
        data1.setFirstName("Jeff");
        data1.setLastName("Wang");
        data1.setId("654321");

        ArrayList<UserData> list = new ArrayList<>();
        list.add(data);
        list.add(data1);
        PatternLockUtils.setActiveAccountList(list);

        return Unit.INSTANCE;
    }
}
