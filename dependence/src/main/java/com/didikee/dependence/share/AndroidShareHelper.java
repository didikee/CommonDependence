package com.didikee.dependence.share;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.didikee.dependence.R;
import com.didikee.dependence.interf.OnSampleClickListener;
import com.didikee.uilibs.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by didik on 2017/2/19.
 *
 * Android 原生的分享
 * 提供两种的样式:
 * 1. 默认样式,可以左右滑动的Dialog
 * 2. BottomSheetsDialog 样式的上下滑动(增加了标题的显示)
 * 参考:{@literal https://material.io/guidelines/components/bottom-sheets.html#bottom-sheets-specs}
 */

public class AndroidShareHelper {

    public BottomSheetDialog getShareDialog(@NonNull Context context, @LayoutRes int layoutId) {
        return this.getShareDialog(context, layoutId, 0);
    }

    public BottomSheetDialog getShareDialog(@NonNull Context context, @LayoutRes int layoutId,
                                            @StyleRes int theme) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, theme);
        View contentView = LayoutInflater.from(context).inflate(layoutId, null, false);
        bottomSheetDialog.setContentView(contentView);
        return bottomSheetDialog;
    }


    public void shareDefault(@NonNull Activity activity, Intent shareIntent, String dialogTitle) {
        //创建分享的Dialog
        try {
            shareIntent = Intent.createChooser(shareIntent, dialogTitle);
            activity.startActivity(shareIntent);
        } catch (Exception e) {
            // error
            // sometime , there is no app to share
            Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareDefaultText(@NonNull Activity activity, String dialogTitle, String
            shareTitle, String shareContent) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型 text/plain
        share_intent.putExtra(Intent.EXTRA_SUBJECT, shareTitle);//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, shareContent);//添加分享内容
        shareDefault(activity, share_intent, dialogTitle);
    }

    public Intent getShareTextIntent(String shareTitle, String shareContent) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型 text/plain
        share_intent.putExtra(Intent.EXTRA_SUBJECT, shareTitle);//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, shareContent);//添加分享内容
        return share_intent;
    }

    /**
     *
     * @param activity
     * @param layoutWithRecyclerView 你可以对Layout里的元素进行设置参数,然后传入进来
     * @param shareIntent
     */
    public void shareWithBottomSheets(@NonNull final Activity activity, @NonNull View
            layoutWithRecyclerView, @NonNull final Intent shareIntent) {
        BottomSheetDialog shareDialog = new BottomSheetDialog(activity);
        shareDialog.setContentView(layoutWithRecyclerView);
        handleShareWithBottomSheets(activity, shareDialog, shareIntent);
    }


    public void shareWithBottomSheets(@NonNull Activity activity, @LayoutRes int
            layoutWithRecyclerView, @NonNull Intent shareIntent) {
        BottomSheetDialog shareDialog = getShareDialog(activity,
                layoutWithRecyclerView);
        handleShareWithBottomSheets(activity, shareDialog, shareIntent);
    }

    private void handleShareWithBottomSheets(@NonNull final Activity activity, @NonNull
            BottomSheetDialog
            shareDialog, @NonNull final Intent shareIntent) {
        List<ActionMenuItem> menus = new ArrayList<>();
        shareDialog.setCanceledOnTouchOutside(false);
        RecyclerView gridView = ((RecyclerView) shareDialog.findViewById(R.id.recyclerView));
        if (gridView == null) {
            throw new NullPointerException("you should declare a id named recyclerView in your " +
                    "bottomSheets layout!");
        }
        int grid = 4;
        gridView.setLayoutManager(new GridLayoutManager(activity, grid));
        gridView.setNestedScrollingEnabled(false);
        gridView.setFocusable(false);
        gridView.setHasFixedSize(true);
        int v = DisplayUtil.dp2px(activity, 3);
        int h = DisplayUtil.dp2px(activity, 8);
        SpaceGridItemDecoration spaceItemDecoration = new SpaceGridItemDecoration(grid, h, v,
                h, v);
        ShareGridAdapter gridAdapter = new ShareGridAdapter();
        gridView.setAdapter(gridAdapter);
        gridView.addItemDecoration(spaceItemDecoration);


        PackageManager pm = activity.getPackageManager();
        final List<ResolveInfo> list = pm.queryIntentActivities(shareIntent, 0);
        for (int i = 0; i < list.size(); i++) {
            ActionMenuItem item = new ActionMenuItem(activity, 0, i, 0, 0, list.get(i).loadLabel
                    (pm));
            item.setIcon(list.get(i).loadIcon(pm));
            menus.add(item);
        }

        gridAdapter.setClickListener(new OnSampleClickListener<ActionMenuItem>() {
            @Override
            public void onClick(View view, ActionMenuItem obj) {
                ActivityInfo activityInfo = list.get(obj.getItemId()).activityInfo;
                ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName,
                        activityInfo.name);
                shareIntent.setComponent(name);
                activity.startActivity(shareIntent);
            }
        });
        gridAdapter.setMenuItems(menus);
        gridAdapter.notifyDataSetChanged();
        shareDialog.show();
    }

    public void addFilter(){
        //TODO 增加过滤,过滤掉无用的包,或者竞争对手的app
    }

}
