/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.ue.permissionutil.rom;

import android.os.Build;
import android.text.TextUtils;

import com.ue.permissionutil.common.CommonUtils;

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
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

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
            if (meizuFlymeOSFlag.toLowerCase().contains("flyme")) {
                return true;
            }
            if (Build.DISPLAY.toLowerCase().contains("flyme")) {
                return true;
            }
        }
        return isTheManufacturer("meizu");
    }

    public static boolean isQiku() {
        return isTheManufacturer("qiku") || isTheManufacturer("360");
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
            CommonUtils.logE("Unable to read sysprop " + propName + "\n" + ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    CommonUtils.logE("Exception while closing InputStream" + "\n" + e);
                }
            }
        }
        return line;
    }
}