package com.ue.permissionutil.sample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ue.permissionutil.PermissionUtils;
import com.ue.permissionutil.common.PermissionStatus;

import java.util.List;

/**
 * Created by hujiang on 2017/4/19.
 */

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.ViewHolder> {
    private Context context;
    private List<PermissionItem> permissionItems;

    public PermissionAdapter(Context context, List<PermissionItem> permissionItems) {
        this.context = context;
        this.permissionItems = permissionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_permission, null);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PermissionItem permissionItem = permissionItems.get(position);

        String permissionName = TextUtils.isEmpty(permissionItem.permName) ? "unknown" : permissionItem.permName;
        holder.tv_permission_name.setText(permissionName);

        if (permissionItem.permStatus == PermissionStatus.PERMISSION_ALLOWED) {
            holder.tv_permission_status.setTextColor(Color.GREEN);
            holder.tv_permission_status.setText("Granted");
        } else if (permissionItem.permStatus == PermissionStatus.PERMISSION_DENIED) {
            holder.tv_permission_status.setTextColor(Color.RED);
            holder.tv_permission_status.setText("Denied");
        } else {
            holder.tv_permission_status.setTextColor(Color.RED);
            holder.tv_permission_status.setText("Unknown");
        }

        holder.item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.forwardPermSettingPage(context, permissionItem.permOp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return permissionItems == null ? 0 : permissionItems.size();
    }


    public void updatePermissionStatus() {
        if (permissionItems != null) {
            for (int i = 0, len = permissionItems.size(); i < len; i++) {
                permissionItems.get(i).updatePermInfo(context);
            }
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View item_container;
        TextView tv_permission_name;
        TextView tv_permission_status;

        public ViewHolder(View itemView) {
            super(itemView);
            item_container = itemView.findViewById(R.id.item_container);
            tv_permission_name = (TextView) itemView.findViewById(R.id.tv_permission_name);
            tv_permission_status = (TextView) itemView.findViewById(R.id.tv_permission_status);
        }
    }
}
