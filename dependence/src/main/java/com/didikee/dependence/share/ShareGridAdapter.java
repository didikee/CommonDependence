package com.didikee.dependence.share;

import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.didikee.dependence.R;
import com.didikee.dependence.interf.OnSampleClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by didik on 2017/2/19.
 */

public class ShareGridAdapter extends RecyclerView.Adapter<ShareGridAdapter.ViewHolder> {


    private List<ActionMenuItem> menuItems = new ArrayList<>();

    private OnSampleClickListener<ActionMenuItem> clickListener;

    public void setClickListener(OnSampleClickListener<ActionMenuItem> clickListener) {
        this.clickListener = clickListener;
    }

    public void setMenuItems(List<ActionMenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .layout_bottomsheet_share_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ActionMenuItem actionMenuItem = menuItems.get(position);
        holder.icon.setImageDrawable(actionMenuItem.getIcon());
        holder.text.setText(actionMenuItem.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onClick(view, actionMenuItem);
                }
            }
        });
        int location = position % 3;
        if ( location == 0){
            // left
            holder.llContainer.setLayoutParams(getFrameLayoutParams(true));
            return;
        }
        if (location == 1){
            // center
            holder.llContainer.setLayoutParams(getFrameLayoutParams(null));
            return;
        }
        if (location == 2){
            // right
            holder.llContainer.setLayoutParams(getFrameLayoutParams(false));
            return;
        }
    }

    private FrameLayout.LayoutParams getFrameLayoutParams(Boolean left){
        FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (left == null){
            params.gravity = Gravity.CENTER;
            return params;
        }
        if (left){
            params.gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
        }else {
            params.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        }
        return params;
    }

    @Override
    public int getItemCount() {
        return menuItems == null ? 0 : menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;
        LinearLayout llContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            text = (TextView) itemView.findViewById(R.id.text);
            llContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);
        }
    }
}
