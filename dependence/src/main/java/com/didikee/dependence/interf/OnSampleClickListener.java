package com.didikee.dependence.interf;

import android.view.View;

/**
 * Created by didik on 2017/2/19.
 * 一个简单的监听回调接口
 */

public interface OnSampleClickListener<T> {
    void onClick(View view ,T obj);
}
