package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;

import butterknife.Bind;
import butterknife.OnClick;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.ui.presenter.NBANewsDetailPresenter;
import wu.wunba.ui.view.NBANewsDetailView;
import wu.wunba.ui.widget.BasketballLoading;
import wu.wunba.utils.DimenUtils;
import wu.wunba.utils.MyStatusBarUtil;
import wu.wunba.utils.MyUtils;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：新闻详情
 * 作者：Wu on 2017/2/23 17:09
 * 邮箱：wuwende@live.cn
 */

public class NBANewsDetailActivity extends BaseSwipeBackCompatActivity implements NBANewsDetailView {

    @Bind(R.id.common_toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_news_detail)
    LinearLayout llNewsDetail;
    @Bind(R.id.tv_news_title)
    TextView tvNewsTitle;
    @Bind(R.id.tv_pub_time)
    TextView tvPubTime;
    @Bind(R.id.tv_new_source)
    TextView tvNewSource;
    @Bind(R.id.phothview)
    PhotoView phothview;


    @Bind(R.id.ll_content)
    LinearLayout ll;


    private Info mInfo;
    private static String articleId;
    private static String upperName;
    private AlphaAnimation in = new AlphaAnimation(0, 1);
    private AlphaAnimation out = new AlphaAnimation(1, 0);
    private NBANewsDetailPresenter nbaNewsDetailPresenter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_news_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        toolbar.setTitle(upperName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initPhotoView();
        nbaNewsDetailPresenter = new NBANewsDetailPresenter(this, mContext);
        nbaNewsDetailPresenter.getHeadlineNewsDetail(articleId);
    }

    /**
     * 初始化photoview
     */
    private void initPhotoView() {
        in.setDuration(300);
        out.setDuration(300);
        phothview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        phothview.enable();
        phothview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                    showSaveDialog();
            return false;
            }
        });

    }



    @OnClick(R.id.phothview)
    public void onClick() {
        phothview.animaTo(mInfo, new Runnable() {
            @Override
            public void run() {
                phothview.setVisibility(View.GONE);
                MyStatusBarUtil.setStatusColor(mContext,getResources().getColor(R.color.default_color));
                ll.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (phothview.getVisibility() == View.VISIBLE) {
            phothview.animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    phothview.setVisibility(View.GONE);
                    MyStatusBarUtil.setStatusColor(mContext,getResources().getColor(R.color.default_color));
                    ll.setVisibility(View.VISIBLE);                }
            });
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showError(String msg) {
        if (msg.equals("0")) {
            //没有网络
            Toast.makeText(mContext, "没有网络连接", Toast.LENGTH_SHORT).show();
        } else {
            //获取数据失败
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context
     */
    public static void startAction(Activity context, Bundle bundle) {
        Intent intent = new Intent(context, NBANewsDetailActivity.class);
        context.startActivity(intent);
        articleId = bundle.getString(Config.ARTICLE_ID);
        upperName = bundle.getString(Config.UPPER_ACTIVITY_NAME);
    }

    @Override
    public void showNewsDetail(String json) {
        llNewsDetail.removeAllViews();
        try {
            JSONObject jsonObject = new JSONObject(json);
            shareUrl = jsonObject.getString("url");
            txtAbstract = jsonObject.getString("abstract");
            tvNewsTitle.setText(jsonObject.getString("title"));
            tvNewSource.setText(jsonObject.getString("source"));
            tvPubTime.setText(jsonObject.getString("pub_time"));
            JSONArray jsonArray = new JSONArray(jsonObject.getString("content"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                if (object.getString("type").equals("text")) {
                    TextView tvInfo = new TextView(mContext);
                    tvInfo.setTextSize(16);
                    tvInfo.setTextColor(getResources().getColor(R.color.text_black_80));
                    tvInfo.setText("        " + object.getString("info"));
                    tvInfo.setLayoutParams(getLinearLayoutTextViewParms(21, 0, 0, 0));
                    llNewsDetail.addView(tvInfo);
                } else if (object.getString("type").equals("img")) {
                    PhotoView ivNews = new PhotoView(mContext);
                    JSONObject objectImg = new JSONObject(object.getString("img"));
                    JSONObject jsonImgInfo = new JSONObject(objectImg.getString("imgurl640"));
                    final String url = jsonImgInfo.getString("imgurl");
                    int width = jsonImgInfo.getInt("width");
                    int height = jsonImgInfo.getInt("height");
                    ivNews.setLayoutParams(getLinearLayoutImageViewParms(5, 5, width, height));
                    ivNews.setMaxWidth(DensityUtil.getScreenWidth());
                    ivNews.setMaxHeight(DensityUtil.getScreenWidth());

                    Xutils3ImageUtils.display(ivNews, url, Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_head_loading_720px,
                            R.mipmap.latest_pic_head_loading_720px));
                    llNewsDetail.addView(ivNews);
                    ivNews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Picasso.with(mContext).load(url). placeholder(R.mipmap.latest_pic_default)
                                    .error(R.mipmap.latest_pic_default).into(phothview);
                            ll.setVisibility(View.GONE);
                            MyStatusBarUtil.setStatusTransparent(mContext,false);
                            mInfo = ((PhotoView) v).getInfo();
                            phothview.setVisibility(View.VISIBLE);
                            phothview.animaFrom(mInfo);
                        }
                    });
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param topMargin    顶部距离
     * @param bottomMargin 底部距离
     * @param leftMargin   左边距离
     * @param rightMargin  右边距离
     * @return
     */
    private LinearLayout.LayoutParams getLinearLayoutTextViewParms(int topMargin, int bottomMargin, int leftMargin, int rightMargin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = topMargin;
        return params;
    }

    /**
     * @param topMargin    顶部距离
     * @param bottomMargin 底部距离
     * @return
     */
    private LinearLayout.LayoutParams getLinearLayoutImageViewParms(int topMargin, int bottomMargin, int w, int h) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = topMargin;
        params.bottomMargin = bottomMargin;
        params.width = DimenUtils.getScreenWidth();
        params.height = DimenUtils.getScreenWidth() * 2 / 3;
        return params;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share,menu);
        return true;
    }


    String shareUrl;
    String txtAbstract;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.news_share){
            MyUtils.showShare(mContext,shareUrl,shareUrl,tvNewsTitle.getText().toString(),txtAbstract);
        }
        return super.onOptionsItemSelected(item);
    }
}
