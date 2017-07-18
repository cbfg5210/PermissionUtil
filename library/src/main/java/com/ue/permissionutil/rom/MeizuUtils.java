/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.rom;

import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.PermissionOps;

public class MeizuUtils {

    public static void forwardPermSettingPage(Context context, int permOp) {
        if (permOp == PermissionOps.OP_SYSTEM_ALERT_WINDOW) {
            forwardPopupWinPermSettingPage(context);
        } else {
            forwardPermSettingsPage(context);
        }
    }

    private static void forwardPopupWinPermSettingPage(Context context) {
        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        if (CommonUtils.safelyStartActivity(context, intent, null)) {
            return;
        }
        CommonUtils.forwardAppDetailPage(context);
    }
}
