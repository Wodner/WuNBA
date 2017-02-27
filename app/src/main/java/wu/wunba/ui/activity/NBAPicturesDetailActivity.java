package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.ui.NBAPicturesDetailPresenter;
import wu.wunba.ui.view.NBAPictruesDetailView;
import wu.wunba.ui.widget.BasketballLoading;
import wu.wunba.utils.MyStatusBarUtil;
import wu.wunba.utils.MyUtils;

/**
 * 描述：图集详情页面
 * 作者：Wu on 2017/2/23 22:34
 * 邮箱：wuwende@live.cn
 */

public class NBAPicturesDetailActivity extends BaseSwipeBackCompatActivity implements NBAPictruesDetailView {

    @Bind(R.id.tv_pic_msg)
    TextView tvPicMsg;
    @Bind(R.id.photo_viewpager)
    ViewPager phoViewPager;
    @Bind(R.id.common_toolbar)
    Toolbar toolbar;

    private static String articleId;
    private static String upperName;
    private NBAPicturesDetailPresenter picturesDetailPresenter;
    private List<String> imgUrl = new ArrayList<>();
    private List<String> imgMsg = new ArrayList<>();
    private SamplePagerAdapter samplePagerAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_pictures_detail;
    }


    @Override
    protected void initViewsAndEvents() {
        toolbar.setTitle(upperName);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(0xff000000);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MyStatusBarUtil.setStatusTransparent(this,false);
        picturesDetailPresenter = new NBAPicturesDetailPresenter(mContext, this);
        picturesDetailPresenter.getNBAPicturesDetail(articleId);
        phoViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int i = position+1;
                if(imgMsg.size()>0)
                    tvPicMsg.setText("("+i + "/" + imgMsg.size() + ")" + "  " + imgMsg.get(position));
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        samplePagerAdapter = new SamplePagerAdapter(mContext,imgUrl);
        phoViewPager.setAdapter(samplePagerAdapter);
    }

    /**
     * @param context
     */
    public static void startAction(Activity context, Bundle bundle) {
        Intent intent = new Intent(context, NBAPicturesDetailActivity.class);
        context.startActivity(intent);
        articleId = bundle.getString(Config.ARTICLE_ID);
        upperName = bundle.getString(Config.UPPER_ACTIVITY_NAME);
    }


    @Override
    public void showError(String msg) {

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
    public void showNBAPictures(String result) {
        imgUrl.clear();
        imgMsg.clear();
        try {

            txtTitle = new JSONObject(result).getString("title");
            shareUrl = new JSONObject(result).getString("url");
            txtAbstract = new JSONObject(result).getString("abstract");


            JSONArray jsonArray = new JSONArray(new JSONObject(result).getString("content"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                if(object.getString("type").equals("text")){
                    imgMsg.add(object.getString("info"));
                }else if(object.getString("type").equals("img")){
                    JSONObject objectImg = new JSONObject(object.getString("img"));
                    JSONObject jsonImgInfo = new JSONObject( objectImg.getString("imgurl640"));
                    imgUrl.add(jsonImgInfo.getString("imgurl"));
                }
            }
            samplePagerAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tvPicMsg.setText("(1" + "/" + imgMsg.size() + ")"+ "  " + imgMsg.get(0));
    }


    /**
     * 适配器
     */
    class SamplePagerAdapter extends PagerAdapter {
        private Activity mContext;
        private List<String> imgUrl = new ArrayList<>();

        public SamplePagerAdapter(Activity context, List<String> imgUrl) {
            this.mContext = context;
            this.imgUrl = imgUrl;
        }

        @Override
        public int getCount() {
            return imgUrl.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView view = new PhotoView(mContext);
            view.enable();
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Picasso.with(mContext).load(imgUrl.get(position)).
            placeholder(R.mipmap.latest_pic_default).error(R.mipmap.latest_pic_default)
                    .into(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share,menu);
        return true;
    }


    String shareUrl;
    String txtAbstract;
    String txtTitle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.news_share){
            Logger.d("---- " + shareUrl);
            MyUtils.showShare(mContext,shareUrl,shareUrl,txtTitle,txtAbstract);
        }
        return super.onOptionsItemSelected(item);
    }


}
