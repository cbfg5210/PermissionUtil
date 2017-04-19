package com.ue.permissionutil;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.ue.permissionutil.common.AccessibilityUtil;
import com.ue.permissionutil.common.CommonUtils;
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
    private static final String TAG = PermissionUtils.class.getSimpleName();

    @SuppressLint("NewApi")
    public static int checkPermission(Context context, int permissionOp) {
        final int version = Build.VERSION.SDK_INT;

        //该方法不能判断无障碍权限
        if (permissionOp == PermissionOps.OP_ACCESSIBILITY) {
            return PermissionStatus.PERMISSION_UNKNOWN;
        }

        if (version < 19) {
            Log.e(TAG, "Below API 19 cannot invoke!");
            return PermissionStatus.PERMISSION_UNKNOWN;
        }

        //读取通知权限单独处理,api19以下，该方法判断不了通知权限，所以要放在version<19的判断之后
        if (permissionOp == PermissionOps.OP_NOTIFICATION_LISTENER) {
            return checkNotificationPermission(context) ? PermissionStatus.PERMISSION_GRANTED : PermissionStatus.PERMISSION_DENIED;
        }

        if (version >= 23 && permissionOp != PermissionOps.OP_SYSTEM_ALERT_WINDOW) {
            //使用Android原生api进行除悬浮窗权限以外的权限判断
            String manifestPermission = PermissionManifestUtil.getManifestPermission(permissionOp);
            if (TextUtils.isEmpty(manifestPermission)) {
                return PermissionStatus.PERMISSION_UNKNOWN;
            }
            boolean isGranted = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, manifestPermission);
            return isGranted ? PermissionStatus.PERMISSION_GRANTED : PermissionStatus.PERMISSION_DENIED;
        }
        //通过反射调用系统方法判断
        boolean isGranted = checkOp(context, permissionOp);
        return isGranted ? PermissionStatus.PERMISSION_GRANTED : PermissionStatus.PERMISSION_DENIED;
    }

    @SuppressLint("NewApi")
    private static boolean checkOp(Context context, int op) {
        if (Build.VERSION.SDK_INT < 19) {
            Log.e(TAG, "Below API 19 cannot invoke!");
            return true;
        }
        AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        try {
            Method method = AppOpsManager.class.getDeclaredMethod("checkOp", int.class, int.class, String.class);
            return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return false;
    }

    private static boolean checkNotificationPermission(Context context) {
        String pkgName = context.getPackageName();
        final String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkAccessibilityPermission(Context context, String accessibilitySerName) {
        if (TextUtils.isEmpty(accessibilitySerName)) {
            return true;
        }
        return AccessibilityUtil.isAccessibilityServiceOn(context, accessibilitySerName);
    }

    public void forwardPermSettingPage(Context context, int permOp) {
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