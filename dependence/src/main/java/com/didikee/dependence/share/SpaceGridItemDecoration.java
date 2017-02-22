package com.didikee.dependence.share;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by didik on 2017/2/19.
 */

public class SpaceGridItemDecoration extends RecyclerView.ItemDecoration {

    private int left;
    private int top;
    private int right;
    private int bottom;
    private int space = 0;
    private final int grid;

    public SpaceGridItemDecoration(int grid,int left, int top, int right, int bottom) {
        this.grid = grid;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public SpaceGridItemDecoration(int grid,int space) {
        this.grid =grid;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
//        super.getItemOffsets(outRect, view, parent, state);
        if (grid == 0)return;
        int childCount = parent.getLayoutManager().getItemCount();
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        if (childLayoutPosition < grid) {
            //顶部
            if (space != 0) {
                outRect.bottom = space;
            } else {
                outRect.bottom = bottom;
            }
            outRect.top = 0;
            // 一行中的位置
            int hLocation = childLayoutPosition % grid;
            if ( hLocation ==0 ){
                // 左
                outRect.left = 0;
                outRect.right = right;
                return;
            }
            if (hLocation == (grid -1)){
                //右
                outRect.left = left;
                outRect.right = 0;
                return;
            }
            //中间
            outRect.left = left;
            outRect.right = right;
            return;
        }
        if (childCount - childLayoutPosition < grid){
            // 底部
            if (space != 0) {
                outRect.top = space;
            } else {
                outRect.top = top;
            }
            outRect.bottom = 0;
            // 一行中的位置
            int hLocation = childLayoutPosition % grid;
            if ( hLocation ==0 ){
                // 左
                outRect.left = 0;
                outRect.right = right;
                return;
            }
            if (hLocation == (grid -1)){
                //右
                outRect.left = left;
                outRect.right = 0;
                return;
            }
            //中间
            outRect.left = left;
            outRect.right = right;
            return;
        }
        // 中间的items
        if (space != 0) {
            outRect.top = space;
            outRect.bottom = space;
        } else {
            outRect.top = top;
            outRect.bottom = bottom;
        }

        // 一行中的位置
        int hLocation = childLayoutPosition % grid;
        if ( hLocation ==0 ){
            // 左
            outRect.left = 0;
            outRect.right = right;
            return;
        }
        if (hLocation == (grid -1)){
            //右
            outRect.left = left;
            outRect.right = 0;
            return;
        }
        //中间
        outRect.left = left;
        outRect.right = right;
        return;

    }
}
