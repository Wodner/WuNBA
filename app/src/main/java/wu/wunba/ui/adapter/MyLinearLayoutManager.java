package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 19:24
 * 邮箱：wuwende@live.cn
 */

public class MyLinearLayoutManager extends LinearLayoutManager{

    private int spanCount;
    private int parentWidth;
    private int width = 0;
    private int height = 0;

    public MyLinearLayoutManager(Context context, int spanCount) {
        super(context);
        this.spanCount = spanCount;
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout, int spanCount) {
        super(context, orientation, reverseLayout);
        this.spanCount = spanCount;
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int spanCount) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.spanCount = spanCount;
    }
    //
//    public MyLinearLayoutManager(Context context, int spanCount, int parentWidth) {
//        super(context, spanCount);
//        this.parentWidth = parentWidth;
//        this.spanCount = spanCount;
//    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);


        int itemCount = getItemCount();
        for(int i = 0; i < itemCount; i++){
            width = parentWidth/spanCount;
            if(itemCount%spanCount == 0){
                height = (itemCount/spanCount)*width;
            }else {
                height = (itemCount/spanCount+1)*width;
            }

        }
        width = widthSize;
        setMeasuredDimension(width, height);
    }
}
