# CustomGestureView

### Gradle引入 

在需要使用的module下，找到build.gradle檔案新增庫依賴:

```kotlin
repositories {
    mavenCentral()
    google()
    maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.jeff11142:CustomGestureView:1.0.23'
}
```
### LaunchActivity
    
* 宣告 `UnitUtils` 與 `CountDownTimer`
```java
import com.jeff.customgesturelib.CustomGestureActivity;
import com.jeff.customgesturelib.setting.UserData;
import com.jeff.customgesturelib.utility.PatternLockUtils;
import com.jeff.customgesturelib.utility.TimerUtils;
import com.jeff.customgesturelib.utility.UnitUtils;

private final UnitUtils unitUtils = new UnitUtils();  //宣告 UnitUtils 用以設定事件
private final CountDownTimer countDownTimer = new TimerUtils().getCountDownTimer();
```
    
* 於 `onCreate` 內設定事件
```java
unitUtils.setLogoutUnit(this::onLogoutUnit);
unitUtils.setForceLogoutUnit(this::onForceLogoutUnit);
unitUtils.setSettingAccountUnit(this::onSettingAccountUnit);
unitUtils.setAppVersion("9.0.2"); //版本號
```
```java
private Unit onLogoutUnit() {
    List<String> userIdSet = PatternLockUtils.getUserIdSet(this);
    if (userIdSet.size() != 0) {
        boolean isLogout = false;
        for (int a = 0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
            if (userIdSet.contains(String.valueOf(UserConfig.getInstance(a).getClientUserId()))) {
                switchToAccount(a, true);
                MessagesController.getInstance(a).performLogout(1);
                isLogout = true;
            }
        }
        if (isLogout) {
            PatternLockUtils.setUserIdSet(new ArrayList<>(), this);
        }
    }
    return Unit.INSTANCE;
}
```
```java
private Unit onForceLogoutUnit() {
    List<String> userIdSet = PatternLockUtils.getUserIdSet(this);
    if (userIdSet.size() != 0) {
        for (int a = 0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
            switchToAccount(a, true);
            MessagesController.getInstance(a).performLogout(1);
        }
        PatternLockUtils.setUserIdSet(new ArrayList<>(), this);
    }

    return Unit.INSTANCE;
}
```
```java
private Unit onSettingAccountUnit() {
    ArrayList<UserData> list = new ArrayList<>();
    for (int a = 0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
        TLRPC.User user = UserConfig.getInstance(a).getCurrentUser();
        if (user != null) {
            UserData data = new UserData();
            data.setId(String.valueOf(user.id));
            if (user.last_name != null) {
                data.setLastName(user.last_name);
            }
            if (user.first_name != null) {
                data.setFirstName(user.first_name);
            }
            list.add(data);
        }
    }
    PatternLockUtils.setActiveAccountList(list);
    return Unit.INSTANCE;
}
```
* 於 `onResume` 內設定事件
```kotlin
if (PatternLockUtils.isNeedtoShowGestureLock) {
    startActivity(new Intent(this, CustomGestureActivity.class));
}
countDownTimer.start();
```
* 於 `onPause` 內設定事件
```kotlin
PatternLockUtils.isNeedtoShowGestureLock = true;
countDownTimer.cancel();
```
### LoginActivity

* 於`needFinishActivity` 尾端新增 `checkAccount()`
```kotlin
private void checkAccount() {
    ArrayList<UserData> list = new ArrayList<>();
    for (int a = 0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
        TLRPC.User user = UserConfig.getInstance(a).getCurrentUser();
        if (user != null) {
            UserData data = new UserData();
            data.setId(String.valueOf(user.id));
            if (user.last_name != null) {
                data.setLastName(user.last_name);
            }
            if (user.first_name != null) {
                data.setFirstName(user.first_name);
            }
            list.add(data);
        }
    }
    PatternLockUtils.setActiveAccountList(list);

    SettingAccountDialog dialog = new SettingAccountDialog(LoginActivity.this.getParentActivity());
    dialog.show(false, "9.0.2", list);
}
```
### License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
