/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.util;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-05-23
 */
public class RomUtils {
    private static final String TAG = RomUtils.class.getSimpleName();

    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_SMARTISAN = "SMARTISAN";

    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_QIKU = "QIKU";

    public static final String ROM_LENOVO = "LENOVO";
    public static final String ROM_SAMSUNG = "SAMSUNG";

    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    private static final String KEY_VERSION_GIONEE = "ro.gn.sv.version";
    private static final String KEY_VERSION_LENOVO = "ro.lenovo.lvp.version";
    private static final String KEY_VERSION_FLYME = "ro.build.display.id";

    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_HANDY_MODE_SF = "ro.miui.has_handy_mode_sf";
    private static final String KEY_MIUI_REAL_BLUR = "ro.miui.has_real_blur";

    private static final String KEY_FLYME_PUBLISHED = "ro.flyme.published";
    private static final String KEY_FLYME_FLYME = "ro.meizu.setupwizard.flyme";

    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";

    private static final String KEY_VIVO_OS_NAME = "ro.vivo.os.name";
    private static final String KEY_VIVO_OS_VERSION = "ro.vivo.os.version";
    private static final String KEY_VIVO_ROM_VERSION = "ro.vivo.rom.version";

    public static boolean isEmui() {
        if (!TextUtils.isEmpty(getSystemProperty(KEY_VERSION_EMUI))) {
            return true;
        }
        return isTheManufacturer("huawei");
    }

    public static boolean isMiui() {
        if (!TextUtils.isEmpty(getSystemProperty(KEY_VERSION_MIUI))) {
            return true;
        }
        return isTheManufacturer("xiaomi");
    }

    public static boolean isVivo() {
        if (!TextUtils.isEmpty(getSystemProperty(KEY_VERSION_VIVO))) {
            return true;
        }
        return isTheManufacturer("vivo");
    }

    public static boolean isOppo() {
        if (!TextUtils.isEmpty(getSystemProperty(KEY_VERSION_OPPO))) {
            return true;
        }
        return isTheManufacturer("oppo");
    }

    public static boolean isFlyme() {
        String meizuFlymeOSFlag = getSystemProperty("ro.build.display.id");
        if (!TextUtils.isEmpty(meizuFlymeOSFlag)) {
            return true;
        }
        if (meizuFlymeOSFlag.contains("flyme") || meizuFlymeOSFlag.toLowerCase().contains("flyme")) {
            return true;
        }
        if (Build.DISPLAY.toUpperCase().contains(ROM_FLYME)) {
            return true;
        }
        return isTheManufacturer("meizu");
    }

    public static boolean isQiku() {
        return isTheManufacturer("qiku")||isTheManufacturer("360");
    }

    public static boolean isSmartisan() {
        if (!TextUtils.isEmpty(getSystemProperty(KEY_VERSION_SMARTISAN))) {
            return true;
        }
        return isTheManufacturer("smartisan");
    }

    private static boolean isTheManufacturer(String manufacturer) {
        return Build.MANUFACTURER.toLowerCase().contains(manufacturer);
    }

    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getSystemProperty " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e(TAG, "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e(TAG, "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }
}