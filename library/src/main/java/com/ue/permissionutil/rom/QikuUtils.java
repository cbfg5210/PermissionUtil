/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.rom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.PermissionOps;

public class QikuUtils {
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

        intent = new Intent();
        comp = new ComponentName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setComponent(comp);
        if (CommonUtils.safelyStartActivity(context, intent)) {
            return;
        }

        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        if (CommonUtils.safelyStartActivity(context, intent)) {
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}