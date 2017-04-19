package com.ue.permissionutil.rom;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.PermissionOps;

/**
 * Created by hujiang on 2017/4/19.
 */

public class SmartisanUtils {
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

        // 锤子 坚果|5.1.1|2.5.3
        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("com.smartisanos.security.action.SWITCHED_PERMISSIONS_NEW");
        comp = new ComponentName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
        intent.setComponent(comp);
        intent.putExtra("index", 17); // 不同版本会不一样
        if (CommonUtils.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }


        // 锤子 坚果|4.4.4|2.1.2
        intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("com.smartisanos.security.action.SWITCHED_PERMISSIONS");
        comp = new ComponentName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
        intent.setComponent(comp);
        intent.putExtra("permission", new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW});
        if (CommonUtils.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        forwardPermSettingsPage(context);
    }

    private static void forwardPermSettingsPage(Context context) {
        Intent intent;

        intent = context.getPackageManager().getLaunchIntentForPackage("com.smartisanos.security");
        if (intent != null && CommonUtils.isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}
