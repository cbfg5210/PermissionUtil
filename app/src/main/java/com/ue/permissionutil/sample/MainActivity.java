package com.ue.permissionutil.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView permissionList;
    private PermissionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionList = (RecyclerView) findViewById(R.id.permission_list);
        permissionList.setLayoutManager(new LinearLayoutManager(this));

        adapter=new PermissionAdapter(this,getPermissionItems());
        permissionList.setAdapter(adapter);
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