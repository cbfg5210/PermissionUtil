/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.rom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.PermissionOps;

import static com.ue.permissionutil.common.CommonUtils.isIntentAvailable;

public class OppoUtils {

    public static void forwardPermSettingPage(Context context, int permOp) {
        if (permOp == PermissionOps.OP_SYSTEM_ALERT_WINDOW) {
            forwardPopupWinPermSettingPage(context);
        } else {
            forwardPermSettingsPage(context);
        }
    }

    private static void forwardPopupWinPermSettingPage(Context context) {
        Intent intent;
        ComponentName comp;

        // OPPO A53|5.1.1|2.1
        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", context.getPackageName());
        intent.setAction("com.oppo.safe");
        comp = new ComponentName("com.oppo.safe", "com.oppo.safe.permission.floatwindow.FloatWindowListActivity");
        intent.setComponent(comp);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }


        // OPPO R7s|4.4.4|2.1
        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", context.getPackageName());
        intent.setAction("com.color.safecenter");
        comp = new ComponentName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
        intent.setComponent(comp);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }


        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", context.getPackageName());
        intent.setAction("com.coloros.safecenter");
        comp = new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
        intent.setComponent(comp);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }


        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent != null && CommonUtils.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        intent = context.getPackageManager().getLaunchIntentForPackage("com.color.safecenter");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent != null && CommonUtils.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        intent = context.getPackageManager().getLaunchIntentForPackage("com.coloros.safecenter");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent != null && CommonUtils.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}