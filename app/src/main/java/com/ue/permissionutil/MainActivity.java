package com.ue.permissionutil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View view){
        forwardPermSettingsPage(MainActivity.this);
    }


    private static void forwardPermSettingsPage(Context context) {
        Intent intent;

        intent = context.getPackageManager().getLaunchIntentForPackage("com.miui.securitycenter");
        if (intent != null) {
            context.startActivity(intent);
            return;
        }

        //小米v6,小米v7
//        intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
//        intent.putExtra("extra_pkgname", context.getPackageName());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (isIntentAvailable(intent, context)) {
//            context.startActivity(intent);
//            return;
//        }

        //小米v8 --1--
//        intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
//        intent.putExtra("extra_pkgname", context.getPackageName());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (isIntentAvailable(intent, context)) {
//            context.startActivity(intent);
//            return;
//        }

        //小米v8 --2--
//        intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//        intent.setPackage("com.miui.securitycenter");
//        intent.putExtra("extra_pkgname", context.getPackageName());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (isIntentAvailable(intent, context)) {
//            context.startActivity(intent);
//            return;
//        }

        //小米v5
//        String packageName = context.getPackageName();
//        intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", packageName, null);
//        intent.setData(uri);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (isIntentAvailable(intent, context)) {
//            context.startActivity(intent);
//            return;
//        }
    }
}
