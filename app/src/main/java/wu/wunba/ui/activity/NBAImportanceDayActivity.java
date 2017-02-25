package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import butterknife.Bind;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.http.NBAApi;

/**
 * 描述：nba 重要日期
 * 作者：Wu on 2017/2/24 15:02
 * 邮箱：wuwende@live.cn
 */

public class NBAImportanceDayActivity extends BaseSwipeBackCompatActivity {

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.common_toolbar)
    Toolbar toolbar;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_importance_day;
    }

    @Override
    protected void initViewsAndEvents() {
        toolbar.setTitle("重要日期");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webview.loadUrl(NBAApi.getNBAImportanceDay());
    }
    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, NBAImportanceDayActivity.class);
        context.startActivity(intent);
    }

}
