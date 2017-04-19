package com.ue.permissionutil.common;

import java.util.Locale;

/**
 * Created by hujiang on 2017/4/19.
 */

public class PermissionNameUtil {
    public static String getPermissonName(int permOp) {
        if (permOp < 0 || permOp >= zhPermissionNames.length) {
            return null;
        }

        boolean isZhLanguage = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage());
        if (isZhLanguage) {
            return zhPermissionNames[permOp];
        } else {
            return enPermissionNames[permOp];
        }
    }

    private static final String[] zhPermissionNames = new String[]{
            "低精度定位",//Manifest.permission.ACCESS_COARSE_LOCATION,
            "高精度定位(GPS)",//Manifest.permission.ACCESS_FINE_LOCATION,
            null,
            "振动",//Manifest.permission.VIBRATE,
            "读取联系人",//Manifest.permission.READ_CONTACTS,
            "修改联系人",//Manifest.permission.WRITE_CONTACTS,
            "读取通话记录",//Manifest.permission.READ_CALL_LOG,
            "读取通话记录",//Manifest.permission.WRITE_CALL_LOG,
            "访问日历",//Manifest.permission.READ_CALENDAR,
            "修改日历",//Manifest.permission.WRITE_CALENDAR,
            "扫描Wifi热点",//Manifest.permission.ACCESS_WIFI_STATE,
            null, // no permission required for notifications
            null, // neighboring cells shares the coarse location perm
            "拨打电话",//Manifest.permission.CALL_PHONE,
            "读取短信",//Manifest.permission.READ_SMS,
            null, // no permission required for writing sms
            "接收短信",//Manifest.permission.RECEIVE_SMS,
            null,//Manifest.permission.RECEIVE_EMERGENCY_BROADCAST,
            "接收彩信",//Manifest.permission.RECEIVE_MMS,
            "服务器推送消息",//Manifest.permission.RECEIVE_WAP_PUSH,
            "发送短信",//Manifest.permission.SEND_SMS,
            "读取短信",//Manifest.permission.READ_SMS,
            null, // no permission required for writing icc sms
            "修改系统设置",//Manifest.permission.WRITE_SETTINGS,
            "显示悬浮窗",//Manifest.permission.SYSTEM_ALERT_WINDOW,
            null,//Manifest.permission.ACCESS_NOTIFICATIONS,
            "调用相机",//Manifest.permission.CAMERA,
            "录音",//Manifest.permission.RECORD_AUDIO,
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
            "唤醒锁",//Manifest.permission.WAKE_LOCK,
            null, // no permission for generic location monitoring
            null, // no permission for high power location monitoring
            "读取应用使用情况",//Manifest.permission.PACKAGE_USAGE_STATS,
            null, // no permission for muting/unmuting microphone
            null, // no permission for displaying toasts
            null, // no permission for projecting media
            null, // no permission for activating vpn
            null, // no permission for supporting wallpaper
            null, // no permission for receiving assist structure
            null, // no permission for receiving assist screenshot
            "读取手机状态",//Manifest.permission.READ_PHONE_STATE,
            "添加语音邮件",//Manifest.permission.ADD_VOICEMAIL,
            "使用SIP",//Manifest.permission.USE_SIP,
            "阻断拨号",//Manifest.permission.PROCESS_OUTGOING_CALLS,
            "使用指纹",//Manifest.permission.USE_FINGERPRINT,
            "开启身体传感器",//Manifest.permission.BODY_SENSORS,
            null,//Manifest.permission.READ_CELL_BROADCASTS,
            null,
            "读取外部存储",//Manifest.permission.READ_EXTERNAL_STORAGE,
            "写入外部存储",//Manifest.permission.WRITE_EXTERNAL_STORAGE,
            null, // no permission for turning the screen on
            "访问帐户列表",//Manifest.permission.GET_ACCOUNTS,
            null, // no permission for running in background
            "通知栏监听",
            "无障碍"
    };

    private static final String[] enPermissionNames = new String[]{
            "Access coarse location",//Manifest.permission.ACCESS_COARSE_LOCATION,
            "Access fine location",//Manifest.permission.ACCESS_FINE_LOCATION,
            null,
            "Vibrate",//Manifest.permission.VIBRATE,
            "Read contacts",//Manifest.permission.READ_CONTACTS,
            "Modify and delete contacts",//Manifest.permission.WRITE_CONTACTS,
            "Read call history",//Manifest.permission.READ_CALL_LOG,
            "Write and delete call history",//Manifest.permission.WRITE_CALL_LOG,
            "Read calendar",//Manifest.permission.READ_CALENDAR,
            "Write calendar",//Manifest.permission.WRITE_CALENDAR,
            "Access wifi state",//Manifest.permission.ACCESS_WIFI_STATE,
            null, // no permission required for notifications
            null, // neighboring cells shares the coarse location perm
            "Make phone calls",//Manifest.permission.CALL_PHONE,
            "Read sms",//Manifest.permission.READ_SMS,
            null, // no permission required for writing sms
            "Receive sms",//Manifest.permission.RECEIVE_SMS,
            null,//Manifest.permission.RECEIVE EMERGENCY_BROADCAST,
            "Receive mms",//Manifest.permission.RECEIVE_MMS,
            "Receive wap push",//Manifest.permission.RECEIVE_WAP_PUSH,
            "Send sms",//Manifest.permission.SEND_SMS,
            "Read sms",//Manifest.permission.READ_SMS,
            null, // no permission required for writing icc sms
            "Modify system settings",//Manifest.permission.WRITE_SETTINGS,
            "Display pop-up window",//Manifest.permission.SYSTEM_ALERT_WINDOW,
            null,//Manifest.permission.ACCESS_NOTIFICATIONS,
            "Access camera",//Manifest.permission.CAMERA,
            "Record audio",//Manifest.permission.RECORD_AUDIO,
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
            "Wake lock",//Manifest.permission.WAKE_LOCK,
            null, // no permission for generic location monitoring
            null, // no permission for high power location monitoring
            "Package usage stats",//Manifest.permission.PACKAGE_USAGE_STATS,
            null, // no permission for muting/unmuting microphone
            null, // no permission for displaying toasts
            null, // no permission for projecting media
            null, // no permission for activating vpn
            null, // no permission for supporting wallpaper
            null, // no permission for receiving assist structure
            null, // no permission for receiving assist screenshot
            "Read phone state",//Manifest.permission.READ_PHONE_STATE,
            "Add voicemail",//Manifest.permission.ADD_VOICEMAIL,
            "Use sip",//Manifest.permission.USE_SIP,
            "Process outgoing calls",//Manifest.permission.PROCESS_OUTGOING_CALLS,
            "Use fingerprint",//Manifest.permission.USE_FINGERPRINT,
            "Body sensors",//Manifest.permission.BODY_SENSORS,
            null,//Manifest.permission.READ_CELL_BROADCASTS,
            null,
            "Read external storage",//Manifest.permission.READ_EXTERNAL_STORAGE,
            "Write external storage",//Manifest.permission.WRITE_EXTERNAL_STORAGE,
            null, // no permission for turning the screen on
            "Get accounts",//Manifest.permission.GET_ACCOUNTS,
            null, // no permission for running in background
            "Notification listener",
            "Accessibility"
    };
}
