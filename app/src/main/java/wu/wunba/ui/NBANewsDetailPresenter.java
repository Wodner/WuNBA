package wu.wunba.ui;

import android.content.Context;

import com.orhanobut.logger.Logger;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.ui.view.NBANewsDetailView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/23 17:16
 * 邮箱：wuwende@live.cn
 */

public class NBANewsDetailPresenter implements Presenter {

    private Context mContext;
    private NBANewsDetailView newsDetailView;

    public NBANewsDetailPresenter(NBANewsDetailView newsDetailView,Context context) {
        this.mContext = context;
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void initialized(int type) {
    }


    /**
     * @param articleId 获取头条新闻详情
     */
    public void getHeadlineNewsDetail(String articleId){
        if(MyUtils.isNetworkConnected(mContext)){
            newsDetailView.showLoading(true);
            NBAApiRequest.getNBANewsHeadlineDetail(articleId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    String s = JsonParser.parseWithJSONObjectKey("data",result);
                    if(s!=null)
                        newsDetailView.showNewsDetail(s);
                    newsDetailView.hideLoading(true);
                }

                @Override
                public void onFailure(String errorMsg) {
                    newsDetailView.showError("获取信息失败");
                }
            });
        }else{
            newsDetailView.showError("0");
        }
    }

    /**
     * @param articleId 获取资讯新闻详情
     */
    public void getInformNewsDetail(String articleId){
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBANewsInformDetail(articleId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    Logger.d("返回资讯新闻详情 ：");
                }

                @Override
                public void onFailure(String errorMsg) {
                    newsDetailView.showError("获取信息失败");
                }
            });
        }else{
            newsDetailView.showError("0");
        }
    }


}
