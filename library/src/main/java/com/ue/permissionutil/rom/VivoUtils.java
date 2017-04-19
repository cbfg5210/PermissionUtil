/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.rom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.PermissionOps;

public class VivoUtils {

    public static void forwardPermSettingPage(Context context, int permOp) {
        if (permOp == PermissionOps.OP_SYSTEM_ALERT_WINDOW) {
            // 不支持直接到达悬浮窗设置页，只能到 i管家 首页
            forwardPopupWinPermSettingPage(context);
        } else {
            forwardPermSettingsPage(context);
        }
    }

    private static void forwardPopupWinPermSettingPage(Context context) {
        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent;
        ComponentName comp;

        intent = new Intent();
        comp = new ComponentName("com.iqoo.secure", "com.iqoo.secure.MainActivity");
        intent.setComponent(comp);

        if (CommonUtils.safelyStartActivity(context,intent)) {
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}