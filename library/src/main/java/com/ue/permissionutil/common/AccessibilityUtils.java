package com.ue.permissionutil.common;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.ArrayList;

public class AccessibilityUtils {

    private AccessibilityUtils() {
        throw new UnsupportedOperationException();
    }

    private static ArrayList<String> getAllAccessibilityServices(Context context) {
        if (context == null) {
            return null;
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        ArrayList<String> allAccessibilityServices = new ArrayList<String>();

        String settingValue = Settings.Secure.getString(
                context.getApplicationContext().getContentResolver(),
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);

        if (!TextUtils.isEmpty(settingValue)) {
            colonSplitter.setString(settingValue);
            while (colonSplitter.hasNext()) {
                String accessibilityService = colonSplitter.next();
                allAccessibilityServices.add(accessibilityService);
            }
        }
        return allAccessibilityServices;
    }

    /**
     * @param context
     * @param serviceName = Service.class.getName();
     * @return
     */
    public static boolean isAccessibilityServiceOn(Context context, String serviceName) {
        if (context == null) {
            return false;
        }
        if (TextUtils.isEmpty(serviceName)) {
            return isAccessibilityServiceOn(context);
        }
        ArrayList<String> allAccessibilityServices = getAllAccessibilityServices(context);
        if (allAccessibilityServices == null || allAccessibilityServices.size() == 0) {
            return false;
        }
        StringBuilder concat = new StringBuilder();
        concat.append(context.getPackageName());
        concat.append('/');
        concat.append(serviceName);
        return allAccessibilityServices.contains(concat.toString());
    }

    public static boolean isAccessibilityServiceOn(Context context) {
        if (context == null) {
            return false;
        }
        ArrayList<String> allAccessibilityServices = getAllAccessibilityServices(context);
        String pkgName = context.getPackageName();
        if (allAccessibilityServices == null && allAccessibilityServices.size() == 0) {
            return false;
        }
        for (int i = allAccessibilityServices.size() - 1; i >= 0; i--) {
            if (allAccessibilityServices.get(i).contains(pkgName)) {
                return true;
            }
        }
        return false;
    }

    public static void goToSettingPage(Context context) {
        if (context == null) {
            return;
        }
        Intent accessibilityServiceIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        if (CommonUtils.safelyStartActivity(context, accessibilityServiceIntent, null)) {
            return;
        }
        CommonUtils.forwardAppDetailPage(context);
    }
}
