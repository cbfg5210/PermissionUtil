package com.ue.permissionutil.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by hawk on 2017/4/18.
 */

public class CommonUtils {
    public static boolean isIntentAvailable(Intent intent, Context context) {
        if (intent == null) {
            return false;
        }
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    public static void forwardAppDetailPage(final Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return;
        }

        boolean isZhLanguage = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage());
        final String toastMsg = isZhLanguage ? "定位不到设置页面,请手动前往设置" : "Unable to navigate to the setting page,please go to settings manually.";

        //如果是在主线程
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!(context instanceof Activity)) {
            return;
        }
        Activity contextAty = (Activity) context;
        contextAty.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
