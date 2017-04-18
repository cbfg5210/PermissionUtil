package com.ue.permissionutil.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.ue.permissionutil.util.rom.HuaweiUtils;
import com.ue.permissionutil.util.rom.MeizuUtils;
import com.ue.permissionutil.util.rom.MiuiUtils;
import com.ue.permissionutil.util.rom.QikuUtils;
import com.ue.permissionutil.util.rom.RomUtils;

import java.lang.reflect.Method;

/**
 * Created by hawk on 2017/4/17.
 */

public class PermissionUtils {
    private static final String TAG = PermissionUtils.class.getSimpleName();

    @SuppressLint("NewApi")
    public static boolean checkPermission(Context context, int permissionOp, String accessibilitySerName) {
        final int version = Build.VERSION.SDK_INT;

        //无障碍权限单独处理
        if (permissionOp == PermissionInfoUtil.OP_ACCESSIBILITY) {
            if (TextUtils.isEmpty(accessibilitySerName)) {
                return true;
            }
            return AccessibilityUtil.isAccessibilityServiceOn(context, accessibilitySerName);
        }

        if (version < 19) {
            Log.e(TAG, "Below API 19 cannot invoke!");
            return true;
        }

        //读取通知权限单独处理,api19以下，该方法判断不了通知权限，所以要放在version<19的判断之后
        if (permissionOp == PermissionInfoUtil.OP_NOTIFICATION_LISTENER) {
            return NotificationUtil.isNotificationEnabled(context);
        }

        if (version >= 23 && permissionOp != PermissionInfoUtil.OP_SYSTEM_ALERT_WINDOW) {
            //使用Android原生api进行除悬浮窗权限以外的权限判断
            String manifestPermission = PermissionInfoUtil.getManifestPermByOp(permissionOp);
            if (TextUtils.isEmpty(manifestPermission)) {
                return false;
            }
            return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, manifestPermission);
        }
        //通过反射调用系统方法判断
        return checkOp(context,permissionOp);
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

    public void forwardPermSettingPage(Context context,int permOp){
        if (RomUtils.isMiui()) {
            MiuiUtils.forwardPermSettingPage(context,permOp);
            return;
        }
        if (RomUtils.isEmui()) {
            HuaweiUtils.forwardPermSettingPage(context,permOp);
            return;
        }
        if (RomUtils.isFlyme()) {
            MeizuUtils.forwardPermSettingPage(context,permOp);
            return;
        }
        if (RomUtils.isQiku()) {
            QikuUtils.forwardPermSettingPage(context,permOp);
            return;
        }
        if (RomUtils.isOppo()) {
            return manageDrawOverlaysForOppo(context);
            return;
        }
        if (RomUtils.isVivo()) {
            return manageDrawOverlaysForVivo(context);
            return;
        }
        if (RomUtils.isSmartisan()) {
            return manageDrawOverlaysForSmartisan(context);
            return;
        }
    }

    public static void manageDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (manageDrawOverlaysForRom(context)) {
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        }
    }

    public static void manageWriteSettings(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        }
    }

    // 小米
    private static boolean manageDrawOverlaysForMiui(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        // miui v5 的支持的android版本最高 4.x
        // http://www.romzj.com/list/search?keyword=MIUI%20V5#search_result
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent1.setData(Uri.fromParts("package", context.getPackageName(), null));
            return startSafely(context, intent1);
        }
        return false;
    }

    private final static String HUAWEI_PACKAGE = "com.huawei.systemmanager";

    // 华为
    private static boolean manageDrawOverlaysForEmui(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.setClassName(HUAWEI_PACKAGE, "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
            if (startSafely(context, intent)) {
                return true;
            }
        }
        // Huawei Honor P6|4.4.4|3.0
        intent.setClassName(HUAWEI_PACKAGE, "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
        intent.putExtra("showTabsNumber", 1);
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setClassName(HUAWEI_PACKAGE, "com.huawei.permissionmanager.ui.MainActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        return false;
    }

    // VIVO
    private static boolean manageDrawOverlaysForVivo(Context context) {
        // 不支持直接到达悬浮窗设置页，只能到 i管家 首页
        Intent intent = new Intent("com.iqoo.secure");
        intent.setClassName("com.iqoo.secure", "com.iqoo.secure.MainActivity");
        // com.iqoo.secure.ui.phoneoptimize.SoftwareManagerActivity
        // com.iqoo.secure.ui.phoneoptimize.FloatWindowManager
        return startSafely(context, intent);
    }

    // OPPO
    private static boolean manageDrawOverlaysForOppo(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        // OPPO A53|5.1.1|2.1
        intent.setAction("com.oppo.safe");
        intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.floatwindow.FloatWindowListActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        // OPPO R7s|4.4.4|2.1
        intent.setAction("com.color.safecenter");
        intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setAction("com.coloros.safecenter");
        intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
        return startSafely(context, intent);
    }

    // 魅族
    private static boolean manageDrawOverlaysForFlyme(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        return startSafely(context, intent);
    }

    // 360
    private static boolean manageDrawOverlaysForQihu(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        if (startSafely(context, intent)) {
            return true;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        return startSafely(context, intent);
    }

    // 锤子
    private static boolean manageDrawOverlaysForSmartisan(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 锤子 坚果|5.1.1|2.5.3
            Intent intent = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS_NEW");
            intent.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
            intent.putExtra("index", 17); // 不同版本会不一样
            return startSafely(context, intent);
        } else {
            // 锤子 坚果|4.4.4|2.1.2
            Intent intent = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS");
            intent.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
            intent.putExtra("permission", new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW});

            //        Intent intent = new Intent("com.smartisanos.security.action.MAIN");
            //        intent.setClassName("com.smartisanos.security", "com.smartisanos.security.MainActivity");
            return startSafely(context, intent);
        }
    }
}