package com.ue.permissionutil;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.ue.permissionutil.common.AccessibilityUtils;
import com.ue.permissionutil.common.CommonUtils;
import com.ue.permissionutil.common.NotificationListenerUtils;
import com.ue.permissionutil.common.PermissionManifestUtil;
import com.ue.permissionutil.common.PermissionOps;
import com.ue.permissionutil.common.PermissionStatus;
import com.ue.permissionutil.rom.HuaweiUtils;
import com.ue.permissionutil.rom.MeizuUtils;
import com.ue.permissionutil.rom.MiuiUtils;
import com.ue.permissionutil.rom.OppoUtils;
import com.ue.permissionutil.rom.QikuUtils;
import com.ue.permissionutil.rom.RomUtils;
import com.ue.permissionutil.rom.SmartisanUtils;
import com.ue.permissionutil.rom.VivoUtils;

import java.lang.reflect.Method;

/**
 * Created by hawk on 2017/4/17.
 */

public class PermissionUtils {

    @SuppressLint("NewApi")
    public static int checkPermission(Context context, int permissionOp) {
        final int version = Build.VERSION.SDK_INT;

        if (permissionOp == PermissionOps.OP_ACCESSIBILITY) {
            boolean isAccessibilityOn = AccessibilityUtils.isAccessibilityServiceOn(context);
            return isAccessibilityOn ? PermissionStatus.PERMISSION_ALLOWED : PermissionStatus.PERMISSION_DENIED;
        }

        if (version < 19) {
            CommonUtils.logE("Below API 19 cannot invoke!");
            return PermissionStatus.PERMISSION_UNKNOWN;
        }

        //读取通知权限单独处理,api19以下，该方法判断不了通知权限，所以要放在version<19的判断之后
        if (permissionOp == PermissionOps.OP_NOTIFICATION_LISTENER) {
            return NotificationListenerUtils.isNotificationServiceOn(context) ? PermissionStatus.PERMISSION_ALLOWED : PermissionStatus.PERMISSION_DENIED;
        }

        if (version >= 23 && permissionOp != PermissionOps.OP_SYSTEM_ALERT_WINDOW) {
            //使用Android原生api进行除悬浮窗权限以外的权限判断
            String manifestPermission = PermissionManifestUtil.getManifestPermission(permissionOp);
            if (TextUtils.isEmpty(manifestPermission)) {
                return PermissionStatus.PERMISSION_UNKNOWN;
            }
            boolean isGranted = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, manifestPermission);
            return isGranted ? PermissionStatus.PERMISSION_ALLOWED : PermissionStatus.PERMISSION_DENIED;
        }
        //通过反射调用系统方法判断
        return checkOp(context, permissionOp);
    }

    public static boolean checkPermissions(Context context, int[] ops) {
        if (ops == null) {
            return false;
        }
        for (int i = 0, len = ops.length; i < len; i++) {
            if (checkPermission(context, ops[i]) != PermissionStatus.PERMISSION_ALLOWED) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint("NewApi")
    private static int checkOp(Context context, int op) {
        if (Build.VERSION.SDK_INT < 19) {
            CommonUtils.logE("Below API 19 cannot invoke!");
            return PermissionStatus.PERMISSION_UNKNOWN;
        }
        AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        try {
            Method method = AppOpsManager.class.getDeclaredMethod("checkOp", int.class, int.class, String.class);
            int res = (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
            return res == AppOpsManager.MODE_ALLOWED ? PermissionStatus.PERMISSION_ALLOWED : PermissionStatus.PERMISSION_DENIED;
        } catch (Exception e) {
            CommonUtils.logE(e.getMessage());
        }
        return PermissionStatus.PERMISSION_UNKNOWN;
    }

    public static void forwardPermSettingPage(Context context, int permOp) {
        if (permOp == PermissionOps.OP_ACCESSIBILITY) {
            AccessibilityUtils.goToSettingPage(context);
            return;
        }
        if (permOp == PermissionOps.OP_NOTIFICATION_LISTENER) {
            NotificationListenerUtils.goToSettingPage(context);
            return;
        }

        if (RomUtils.isMiui()) {
            MiuiUtils.forwardPermSettingPage(context, permOp);
            return;
        }
        if (RomUtils.isEmui()) {
            HuaweiUtils.forwardPermSettingPage(context, permOp);
            return;
        }
        if (RomUtils.isFlyme()) {
            MeizuUtils.forwardPermSettingPage(context, permOp);
            return;
        }
        if (RomUtils.isQiku()) {
            QikuUtils.forwardPermSettingPage(context, permOp);
            return;
        }
        if (RomUtils.isOppo()) {
            OppoUtils.forwardPermSettingPage(context, permOp);
            return;
        }
        if (RomUtils.isVivo()) {
            VivoUtils.forwardPermSettingPage(context, permOp);
            return;
        }
        if (RomUtils.isSmartisan()) {
            SmartisanUtils.forwardPermSettingPage(context, permOp);
            return;
        }

        CommonUtils.forwardAppDetailPage(context);
    }
}