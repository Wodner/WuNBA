package wu.wunba.ui.presenter;

import android.content.Context;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.ui.view.NBAPictruesDetailView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/23 22:52
 * 邮箱：wuwende@live.cn
 */

public class NBAPicturesDetailPresenter implements Presenter {


    private Context mContext;
    private NBAPictruesDetailView nbaPictruesDetailView;

    public NBAPicturesDetailPresenter(Context mContext, NBAPictruesDetailView nbaPictruesDetailView) {
        this.mContext = mContext;
        this.nbaPictruesDetailView = nbaPictruesDetailView;
    }

    @Override
    public void initialized(int type) {

    }



    public void getNBAPicturesDetail(String articleId){
        if(MyUtils.isNetworkConnected(mContext)){
            nbaPictruesDetailView.showLoading(true);
            NBAApiRequest.getNBANewsHeadlineDetail(articleId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    String s = JsonParser.parseWithJSONObjectKey("data",result);
                    if(s!=null)
                        nbaPictruesDetailView.showNBAPictures(s);
                    nbaPictruesDetailView.hideLoading(true);
                }

                @Override
                public void onFailure(String errorMsg) {
                    nbaPictruesDetailView.showError("获取信息失败");
                }
            });
        }else{
            nbaPictruesDetailView.showError("0");
        }
    }








}
