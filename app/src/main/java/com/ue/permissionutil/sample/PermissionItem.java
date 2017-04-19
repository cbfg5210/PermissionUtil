package com.ue.permissionutil.sample;

import android.content.Context;

import com.ue.permissionutil.PermissionUtils;
import com.ue.permissionutil.common.PermissionNameUtil;

/**
 * Created by hujiang on 2017/4/19.
 */

public class PermissionItem {
    public int permOp;
    public String permName;
    public int permStatus;

    public PermissionItem(int permOp){
        this.permOp=permOp;
        this.permName= PermissionNameUtil.getPermissonName(permOp);
    }

    public void updatePermInfo(Context context){
        permStatus= PermissionUtils.checkPermission(context,permOp);
    }
}
