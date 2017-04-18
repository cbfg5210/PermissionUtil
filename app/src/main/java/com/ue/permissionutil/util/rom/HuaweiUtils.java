/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.util.rom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.util.CommonUtils;
import com.ue.permissionutil.util.PermissionInfoUtil;

import static com.ue.permissionutil.util.CommonUtils.isIntentAvailable;

public class HuaweiUtils {

    public static void forwardPermSettingPage(Context context,int permOp){
        if(permOp== PermissionInfoUtil.OP_SYSTEM_ALERT_WINDOW){
            forwardPopupWinPermSettingPage(context);
        }else{
            forwardPermSettingsPage(context);
        }
    }

    private static void forwardPopupWinPermSettingPage(Context context) {
        Intent intent;
        ComponentName comp;

        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        comp = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");//悬浮窗管理页面
        intent.setComponent(comp);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        comp = new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");//悬浮窗管理页面
        intent.setComponent(comp);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent;
        ComponentName comp;

        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理，跳转到本app的权限管理页面,这个需要华为接口权限，未解决
        intent.setComponent(comp);
        if(isIntentAvailable(intent,context)){
            context.startActivity(intent);
            return;
        }

        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        comp = new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem");//权限管理页面 android4.4
        intent.setComponent(comp);
        if(isIntentAvailable(intent,context)){
            context.startActivity(intent);
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}