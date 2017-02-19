package com.didikee.dependence.share;

import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    }

    @Override
    public int getItemCount() {
        return menuItems == null ? 0 : menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
