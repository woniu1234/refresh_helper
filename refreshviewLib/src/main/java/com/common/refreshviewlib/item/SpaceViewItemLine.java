package com.common.refreshviewlib.item;

import static android.widget.LinearLayout.VERTICAL;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.common.refreshviewlib.adapter.RecyclerArrayAdapter;


/**
 * desc  : list条目的分割线
 * revise: 使用默认的分割线颜色，且分割线宽度也是默认值，适用于瀑布流中的间距设置
 */
public class SpaceViewItemLine extends RecyclerView.ItemDecoration {

    private final int space;
    private boolean mPaddingEdgeSide = true;
    private boolean mPaddingStart = true;
    private boolean mPaddingHeaderFooter = false;

    public SpaceViewItemLine(int space) {
        this.space = space;
    }

    public void setPaddingEdgeSide(boolean mPaddingEdgeSide) {
        this.mPaddingEdgeSide = mPaddingEdgeSide;
    }

    public void setPaddingStart(boolean mPaddingStart) {
        this.mPaddingStart = mPaddingStart;
    }

    public void setPaddingHeaderFooter(boolean mPaddingHeaderFooter) {
        this.mPaddingHeaderFooter = mPaddingHeaderFooter;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = 0;
        int orientation = 0;
        int spanIndex = 0;
        int headerCount = 0, footerCount = 0;
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        if (adapter instanceof RecyclerArrayAdapter) {
            headerCount = ((RecyclerArrayAdapter) adapter).getHeaderCount();
            footerCount = ((RecyclerArrayAdapter) adapter).getFooterCount();
        }

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            spanCount = 1;
            spanIndex = 0;
        }

        //普通Item的尺寸
        if ((position >= headerCount && position < adapter.getItemCount() - footerCount)) {
            if (orientation == VERTICAL) {
                float expectedWidth = (float) (parent.getWidth() - space * (spanCount + (mPaddingEdgeSide ? 1 : -1))) / spanCount;
                float originWidth = (float) parent.getWidth() / spanCount;
                float expectedX = (mPaddingEdgeSide ? space : 0) + (expectedWidth + space) * spanIndex;
                float originX = originWidth * spanIndex;
                outRect.left = (int) (expectedX - originX);
                outRect.right = (int) (originWidth - outRect.left - expectedWidth);
                if (position - headerCount < spanCount && mPaddingStart) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                float expectedHeight = (float) (parent.getHeight() - space * (spanCount + (mPaddingEdgeSide ? 1 : -1))) / spanCount;
                float originHeight = (float) parent.getHeight() / spanCount;
                float expectedY = (mPaddingEdgeSide ? space : 0) + (expectedHeight + space) * spanIndex;
                float originY = originHeight * spanIndex;
                outRect.bottom = (int) (expectedY - originY);
                outRect.top = (int) (originHeight - outRect.bottom - expectedHeight);
                if (position - headerCount < spanCount && mPaddingStart) {
                    outRect.left = space;
                }
                outRect.right = space;
            }
        } else if (mPaddingHeaderFooter) {
            if (orientation == VERTICAL) {
                outRect.right = outRect.left = mPaddingEdgeSide ? space : 0;
                outRect.top = mPaddingStart ? space : 0;
            } else {
                outRect.top = outRect.bottom = mPaddingEdgeSide ? space : 0;
                outRect.left = mPaddingStart ? space : 0;
            }
        }
    }


}