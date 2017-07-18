package com.ue.permissionutil.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ue.permissionutil.BuildConfig;

import java.util.Locale;

import static android.os.Looper.getMainLooper;

/**
 * Created by hawk on 2017/4/18.
 */

public class CommonUtils {

    public static void forwardAppDetailPage(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));

        boolean isZhLanguage = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage());
        String toastMsg = isZhLanguage ? "定位不到设置页面,请手动前往设置" : "Failed to open the setting page,please go to setting page manually.";

        safelyStartActivity(context, intent, toastMsg);
    }

    public static boolean safelyStartActivity(final Context context, Intent intent, final String errTip) {
        if (context == null) {
            return false;
        }
        if (intent == null) {
            return false;
        }
        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch (Exception exp) {
            if (TextUtils.isEmpty(errTip)) {
                return false;
            }
            Looper mainLooper = getMainLooper();
            if (mainLooper == null) {
                return false;
            }
            new Handler(mainLooper).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, errTip, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return false;
    }

    public static void logE(String logMsg) {
        if (BuildConfig.DEBUG && !TextUtils.isEmpty(logMsg)) {
            Log.e("--**PermissionUtil**--", logMsg);
        }
    }
}
