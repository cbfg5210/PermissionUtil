package com.ue.permissionutil.common;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujiang on 2017/7/18.
 */

public class NotificationListenerUtils {
    public static List<String> getAllNotificationServices(Context context) {
        if (context == null) {
            return null;
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        ArrayList<String> allNotificationServices = new ArrayList<String>();

        String settingValue = Settings.Secure.getString(
                context.getApplicationContext().getContentResolver(),
                "enabled_notification_listeners");

        if (!TextUtils.isEmpty(settingValue)) {
            colonSplitter.setString(settingValue);
            while (colonSplitter.hasNext()) {
                String notificationService = colonSplitter.next();
                allNotificationServices.add(notificationService);
            }
        }
        return allNotificationServices;
    }

    public static boolean isNotificationServiceOn(Context context) {
        if (context == null) {
            return false;
        }
        List<String> allNotificationServices = getAllNotificationServices(context);
        String pkgName = context.getPackageName();
        if (allNotificationServices == null || allNotificationServices.size() == 0) {
            return false;
        }
        for (int i = allNotificationServices.size() - 1; i >= 0; i--) {
            if (allNotificationServices.get(i).contains(pkgName)) {
                return true;
            }
        }
        return false;
    }

    public static void goToSettingPage(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
        if (CommonUtils.safelyStartActivity(context, intent, null)) {
            return;
        }
        CommonUtils.forwardAppDetailPage(context);
    }
}
