package com.ue.permissionutil.util;

import android.Manifest;

/**
 * Created by hujiang on 2017/4/19.
 */

public class PermissionNameUtil {
    public static String getPermissonName(int permOp){
        if(permOp<0||permOp>=permissions.length){
            return null;
        }
        return permissions[permOp];
    }

    private static final String[]permissions=new String[]{
            "低精度定位权限",//Manifest.permission.ACCESS_COARSE_LOCATION,
            "高精度定位(GPS)权限",//Manifest.permission.ACCESS_FINE_LOCATION,
            null,
            "振动权限",//Manifest.permission.VIBRATE,
            "读取联系人权限",//Manifest.permission.READ_CONTACTS,
            "修改联系人权限",//Manifest.permission.WRITE_CONTACTS,
            "读取通话记录权限",//Manifest.permission.READ_CALL_LOG,
            "读取通话记录权限",//Manifest.permission.WRITE_CALL_LOG,
            "访问日历权限",//Manifest.permission.READ_CALENDAR,
            "修改日历权限",//Manifest.permission.WRITE_CALENDAR,
            "扫描Wifi热点权限",//Manifest.permission.ACCESS_WIFI_STATE,
            null, // no permission required for notifications
            null, // neighboring cells shares the coarse location perm
            "拨打电话权限",//Manifest.permission.CALL_PHONE,
            "读取短信权限",//Manifest.permission.READ_SMS,
            null, // no permission required for writing sms
            "接收短信权限",//Manifest.permission.RECEIVE_SMS,
            null,//Manifest.permission.RECEIVE_EMERGENCY_BROADCAST,
            "接收彩信权限权限",//Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            "发送短信权限",//Manifest.permission.SEND_SMS,
            "读取短信权限",//Manifest.permission.READ_SMS,
            null, // no permission required for writing icc sms
            "修改系统设置权限",//Manifest.permission.WRITE_SETTINGS,
            "显示悬浮窗权限",//Manifest.permission.SYSTEM_ALERT_WINDOW,
            null,//Manifest.permission.ACCESS_NOTIFICATIONS,
            "调用相机权限",//Manifest.permission.CAMERA,
            "录音权限",//Manifest.permission.RECORD_AUDIO,
            null, // no permission for playing audio
            null, // no permission for reading clipboard
            null, // no permission for writing clipboard
            null, // no permission for taking media buttons
            null, // no permission for taking audio focus
            null, // no permission for changing master volume
            null, // no permission for changing voice volume
            null, // no permission for changing ring volume
            null, // no permission for changing media volume
            null, // no permission for changing alarm volume
            null, // no permission for changing notification volume
            null, // no permission for changing bluetooth volume
            "唤醒锁权限",//Manifest.permission.WAKE_LOCK,
            null, // no permission for generic location monitoring
            null, // no permission for high power location monitoring
            Manifest.permission.PACKAGE_USAGE_STATS,
            null, // no permission for muting/unmuting microphone
            null, // no permission for displaying toasts
            null, // no permission for projecting media
            null, // no permission for activating vpn
            null, // no permission for supporting wallpaper
            null, // no permission for receiving assist structure
            null, // no permission for receiving assist screenshot
            "读取手机状态权限",//Manifest.permission.READ_PHONE_STATE,
            "添加语音邮件权限",//Manifest.permission.ADD_VOICEMAIL,
            "使用SIP权限",//Manifest.permission.USE_SIP,
            "阻断拨号权限",//Manifest.permission.PROCESS_OUTGOING_CALLS,
            "使用指纹权限",//Manifest.permission.USE_FINGERPRINT,
            "开启身体传感器权限",//Manifest.permission.BODY_SENSORS,
            null,//Manifest.permission.READ_CELL_BROADCASTS,
            null,
            "读取外部存储权限",//Manifest.permission.READ_EXTERNAL_STORAGE,
            "写入外部存储权限",//Manifest.permission.WRITE_EXTERNAL_STORAGE,
            null, // no permission for turning the screen on
            "访问帐户列表权限",//Manifest.permission.GET_ACCOUNTS,
            null, // no permission for running in background
            "通知栏监听",
            "无障碍"
    };
}
