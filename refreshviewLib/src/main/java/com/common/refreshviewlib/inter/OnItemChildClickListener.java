package com.common.refreshviewlib.inter;

import android.view.View;


/**
 * desc  : item中孩子点击监听接口
 */
public interface OnItemChildClickListener {

    /**
     * item中孩子点击监听接口
     *
     * @param view     view
     * @param position position索引
     */
    void onItemChildClick(View view, int position);
}
