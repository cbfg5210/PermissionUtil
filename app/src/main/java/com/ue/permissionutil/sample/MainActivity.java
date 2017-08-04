package com.ue.permissionutil.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ue.permissionutil.PermissionUtils;
import com.ue.permissionutil.common.PermissionOps;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView permissionList;
    private PermissionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.open_perm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.forwardPermSettingPage(MainActivity.this, PermissionOps.OP_SYSTEM_ALERT_WINDOW);
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//                CommonUtils.safelyStartActivity(MainActivity.this, intent, null);
            }
        });

//        permissionList = (RecyclerView) findViewById(R.id.permission_list);
//        permissionList.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter=new PermissionAdapter(this,getPermissionItems());
//        permissionList.setAdapter(adapter);
    }

    private List<PermissionItem> getPermissionItems() {
        List<PermissionItem> permissionItems = new ArrayList<>(66);
        for (int i = 0; i < 66; i++) {
            permissionItems.add(new PermissionItem(i));
        }
        return permissionItems;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.updatePermissionStatus();
        }
    }
}