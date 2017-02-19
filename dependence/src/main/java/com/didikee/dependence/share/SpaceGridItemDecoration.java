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
        super.getItemOffsets(outRect, view, parent, state);
        if (grid == 0)return;
        int childCount = parent.getChildCount();
        int childLayoutPosition = parent.getChildLayoutPosition(view);
        if (childLayoutPosition < grid) {
            //顶部
            if (space != 0) {
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
            } else {
                outRect.left = left;
                outRect.right = right;
                outRect.bottom = bottom;
            }
            return;
        }
        if (childCount - childLayoutPosition < grid){
            // 底部
            if (space != 0) {
                outRect.left = space;
                outRect.top = space;
                outRect.right = space;
            } else {
                outRect.left = left;
                outRect.top = top;
                outRect.right = right;
            }
            return;
        }

        if (space != 0) {
            outRect.left = space;
            outRect.top = space;
            outRect.right = space;
            outRect.bottom = space;
        } else {
            outRect.left = left;
            outRect.top = top;
            outRect.right = right;
            outRect.bottom = bottom;
        }

    }
}
