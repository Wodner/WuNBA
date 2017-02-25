package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;

import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.utils.MyStatusBarUtil;

/**
 * 描述：球员详情
 * 作者：Wu on 2017/2/19 20:24
 * 邮箱：wuwende@live.cn
 */
public class NBAPlayerDetialActivity extends BaseSwipeBackCompatActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_player_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        MyStatusBarUtil.setStatusTransparent(mContext,true);
    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, NBAPlayerDetialActivity.class);
        context.startActivity(intent);
    }
}
