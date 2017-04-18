/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.util.rom;

import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.util.CommonUtils;
import com.ue.permissionutil.util.PermissionInfoUtil;

import static com.ue.permissionutil.util.CommonUtils.isIntentAvailable;

public class QikuUtils {
    private static final String TAG = QikuUtils.class.getSimpleName();

    public static void forwardPermSettingPage(Context context,int permOp){
        if(permOp== PermissionInfoUtil.OP_SYSTEM_ALERT_WINDOW){
            forwardPopupWinPermSettingPage(context);
        }else{
            forwardPermSettingsPage(context);
        }
    }

    private static void forwardPopupWinPermSettingPage(Context context) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent =new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}