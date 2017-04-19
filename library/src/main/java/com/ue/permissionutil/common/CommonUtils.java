package com.ue.permissionutil.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import static android.os.Looper.getMainLooper;

/**
 * Created by hawk on 2017/4/18.
 */

public class CommonUtils {
    private static boolean isDebug = true;

    private static boolean isIntentAvailable(Intent intent, Context context) {
        if (intent == null) {
            return false;
        }
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    public static void forwardAppDetailPage(final Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        if (safelyStartActivity(context, intent)) {
            return;
        }

        boolean isZhLanguage = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage());
        final String toastMsg = isZhLanguage ? "定位不到设置页面,请手动前往设置" : "Unable to navigate to the setting page,please go to settings manually.";

        Looper mainLooper = getMainLooper();
        if (mainLooper != null) {
            new Handler(mainLooper).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static boolean safelyStartActivity(Context context, Intent intent) {
        if (intent == null || !isIntentAvailable(intent, context)) {
            return false;
        }

        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch (Exception exp) {
        }

        return false;
    }

    public static void logE(String logMsg) {
        if (isDebug && !TextUtils.isEmpty(logMsg)) {
            Log.e("--**PermissionUtil**--", logMsg);
        }
    }
}
