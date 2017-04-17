package com.ue.permissionutil.util;


import android.Manifest;
import android.text.TextUtils;

/**
 * Created by hujiang on //.
 */

public class PermissionInfoUtil {
    /**  No operation specified. */
    public static final int OP_NONE = -1;
    /**  Access to coarse location information. 低精度定位*/
    public static final int OP_COARSE_LOCATION = 0;
    /**  Access to fine location information. 高精度定位*/
    public static final int OP_FINE_LOCATION = 1;
    /**  Causing GPS to run. */
    public static final int OP_GPS = 2;
    /** 📳震动权限 */
    public static final int OP_VIBRATE = 3;
    /** 读取联系人权限 */
    public static final int OP_READ_CONTACTS = 4;
    /** 修改联系人权限 */
    public static final int OP_WRITE_CONTACTS = 5;
    /** 读取通话记录权限 */
    public static final int OP_READ_CALL_LOG = 6;
    /** 读取通话记录权限 */
    public static final int OP_WRITE_CALL_LOG = 7;
    /** 访问日历权限 */
    public static final int OP_READ_CALENDAR = 8;
    /** 修改日历权限 */
    public static final int OP_WRITE_CALENDAR = 9;
    /** 扫描Wifi热点权限 */
    public static final int OP_WIFI_SCAN = 10;
    /** 发布通知 */
    public static final int OP_POST_NOTIFICATION = 11;
    /**  */
    public static final int OP_NEIGHBORING_CELLS = 12;
    /** 拨打电话权限 */
    public static final int OP_CALL_PHONE = 13;
    /** 读取短信权限 */
    public static final int OP_READ_SMS = 14;
    /** 编辑短信权限 */
    public static final int OP_WRITE_SMS = 15;
    /** 接收短信权限 */
    public static final int OP_RECEIVE_SMS = 16;
    /** 接收紧急短信 */
    public static final int OP_RECEIVE_EMERGECY_SMS = 17;
    /** 接收彩信权限 */
    public static final int OP_RECEIVE_MMS = 18;
    /** 接收Wap Push权限 */
    public static final int OP_RECEIVE_WAP_PUSH = 19;
    /** 发送短信权限 */
    public static final int OP_SEND_SMS = 20;
    /** 接收运营商短信 */
    public static final int OP_READ_ICC_SMS = 21;
    /** 编辑运营商短信 */
    public static final int OP_WRITE_ICC_SMS = 22;
    /** 修改系统设置权限 */
    public static final int OP_WRITE_SETTINGS = 23;
    /** 显示悬浮窗权限 */
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    /** 访问通知权限 */
    public static final int OP_ACCESS_NOTIFICATIONS = 25;
    /** 调用相机权限 */
    public static final int OP_CAMERA = 26;
    /** 录音权限 */
    public static final int OP_RECORD_AUDIO = 27;
    /** 播放音频 */
    public static final int OP_PLAY_AUDIO = 28;
    /** 获取粘贴板内容 */
    public static final int OP_READ_CLIPBOARD = 29;
    /** 编辑粘贴板内容 */
    public static final int OP_WRITE_CLIPBOARD = 30;
    /** 获取多媒体按钮权限 */
    public static final int OP_TAKE_MEDIA_BUTTONS = 31;
    /** 获取声音焦点权限 */
    public static final int OP_TAKE_AUDIO_FOCUS = 32;
    /**  */
    public static final int OP_AUDIO_MASTER_VOLUME = 33;
    /**  */
    public static final int OP_AUDIO_VOICE_VOLUME = 34;
    /**  */
    public static final int OP_AUDIO_RING_VOLUME = 35;
    /**  */
    public static final int OP_AUDIO_MEDIA_VOLUME = 36;
    /**  */
    public static final int OP_AUDIO_ALARM_VOLUME = 37;
    /**  */
    public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    /**  */
    public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    /**  */
    public static final int OP_WAKE_LOCK = 40;
    /**  Continually monitoring location data. */
    public static final int OP_MONITOR_LOCATION = 41;
    /**  Continually monitoring location data with a relatively high power request. */
    public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
    /**  Retrieve current usage stats via {link UsageStatsManager}. 获取应用使用情况权限*/
    public static final int OP_GET_USAGE_STATS = 43;
    /**  */
    public static final int OP_MUTE_MICROPHONE = 44;
    /**  */
    public static final int OP_TOAST_WINDOW = 45;
    /**  Capture the device's display contents and/or audio */
    public static final int OP_PROJECT_MEDIA = 46;
    /**  Activate a VPN connection without user intervention. */
    public static final int OP_ACTIVATE_VPN = 47;
    /**  Access the WallpaperManagerAPI to write wallpapers. */
    public static final int OP_WRITE_WALLPAPER = 48;
    /**  Received the assist structure from an app. */
    public static final int OP_ASSIST_STRUCTURE = 49;
    /**  Received a screenshot from assist. */
    public static final int OP_ASSIST_SCREENSHOT = 50;
    /**  Read the phone state. */
    public static final int OP_READ_PHONE_STATE = 51;
    /**  Add voicemail messages to the voicemail content provider. */
    public static final int OP_ADD_VOICEMAIL = 52;
    /**  Access APIs for SIP calling over VOIP or WiFi. */
    public static final int OP_USE_SIP = 53;
    /**  Intercept outgoing calls. 处理(拦截)外拨号码*/
    public static final int OP_PROCESS_OUTGOING_CALLS = 54;
    /**  User the fingerprint API. 使用指纹读取器*/
    public static final int OP_USE_FINGERPRINT = 55;
    /**  Access to body sensors such as heart rate, etc. 身体传感器*/
    public static final int OP_BODY_SENSORS = 56;
    /**  Read previously received cell broadcast messages. 读取移动蜂窝广播*/
    public static final int OP_READ_CELL_BROADCASTS = 57;
    /**  Inject mock location into the system. 模拟位置*/
    public static final int OP_MOCK_LOCATION = 58;
    /**  Read external storage. */
    public static final int OP_READ_EXTERNAL_STORAGE = 59;
    /**  Write external storage. */
    public static final int OP_WRITE_EXTERNAL_STORAGE = 60;
    /**  Turned on the screen. */
    public static final int OP_TURN_SCREEN_ON = 61;
    /**  Get device accounts. */
    public static final int OP_GET_ACCOUNTS = 62;
    /**  Control whether an application is allowed to run in the background. */
    public static final int OP_RUN_IN_BACKGROUND = 63;
    /**  */
    public static final int _NUM_OP = 64;

    public static final int OP_NOTIFICATION_LISTENER=65;//自定义添加：通知监听
    public static final int OP_ACCESSIBILITY=66;//自定义添加：无障碍

    private static String[] sOpPerms = new String[] {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            null,
            Manifest.permission.VIBRATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.ACCESS_WIFI_STATE,
            null, // no permission required for notifications
            null, // neighboring cells shares the coarse location perm
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_SMS,
            null, // no permission required for writing sms
            Manifest.permission.RECEIVE_SMS,
            null,//Manifest.permission.RECEIVE_EMERGENCY_BROADCAST,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            null, // no permission required for writing icc sms
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            null,//Manifest.permission.ACCESS_NOTIFICATIONS,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
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
            Manifest.permission.WAKE_LOCK,
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
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.USE_FINGERPRINT,
            Manifest.permission.BODY_SENSORS,
            null,//Manifest.permission.READ_CELL_BROADCASTS,
            null,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            null, // no permission for turning the screen on
            Manifest.permission.GET_ACCOUNTS,
            null, // no permission for running in background
    };

    private static final String[]opZhName=new String[]{
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

    public static String getPermNameByOp(int permOp){
        if(permOp<0||permOp>=opZhName.length){
            return null;
        }
        return opZhName[permOp];
    }

    public static int getPermOpByManifeast(String manifeast){
        if(TextUtils.isEmpty(manifeast)){
            return -1;
        }
        for(int i=0,len=sOpPerms.length;i<len;i++){
            if(manifeast.equals(sOpPerms[i])){
                return i;
            }
        }
        return -1;
    }


    public static String getManifestPermByOp(int permissionOp){
        if(permissionOp<0||permissionOp>=sOpPerms.length){
            return null;
        }
        return sOpPerms[permissionOp];
    }
}
