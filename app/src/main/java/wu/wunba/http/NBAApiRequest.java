package wu.wunba.http;

import android.text.TextUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import wu.wunba.model.ModelNBAVideoArticleIds;
import wu.wunba.model.NBAVideoInfo;

/**
 * 描述：
 * 作者：Wu on 2017/2/22 01:25
 * 邮箱：wuwende@live.cn
 */

public class NBAApiRequest {

    /*********************************以下是视频相关**************************************/

    /**
     * @param requestCallBack 获取集锦ids
     */
    public static void getHighlightVideoArticleIds(final RequestCallBack<ModelNBAVideoArticleIds> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAVideoArticleId());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
//                    Logger.d("头条articles :" + result);
                    ModelNBAVideoArticleIds articleIds = JsonParser.parseWithGson(ModelNBAVideoArticleIds.class,result);
                    requestCallBack.onSuccess(articleIds);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleIds
     * @param requestCallBack
     */
    public static void getHighlightVideo(String articleIds,  final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAVideos(articleIds));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }

    /**
     * @param requestCallBack 获取集锦ids
     */
    public static void getBestVideoArticleIds(final RequestCallBack<ModelNBAVideoArticleIds> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAVideoBestArticleId());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
//                    Logger.d("头条articles :" + result);
                    ModelNBAVideoArticleIds articleIds = JsonParser.parseWithGson(ModelNBAVideoArticleIds.class,result);
                    requestCallBack.onSuccess(articleIds);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleIds
     * @param requestCallBack
     */
    public static void getBestVideo(String articleIds,  final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAVideoBest(articleIds));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }




    /**
     * @param requestCallBack 获取集锦ids
     */
    public static void getTidbitsVideoArticleIds(final RequestCallBack<ModelNBAVideoArticleIds> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAVideoTidbitsArticleId());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
//                    Logger.d("集锦articles :" + result);
                    ModelNBAVideoArticleIds articleIds = JsonParser.parseWithGson(ModelNBAVideoArticleIds.class,result);
                    requestCallBack.onSuccess(articleIds);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleIds
     * @param requestCallBack
     */
    public static void getTidbitsVideo(String articleIds,  final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAVideoTidbits(articleIds));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /** 根据视频vid 获取真实的视频播放地址
     * @param vid
     * @param requestCallBack
     */
    public static void getNBAVideoRealUrl(String vid,final RequestCallBack<String> requestCallBack){
        x.http().get(new RequestParams(NBAApi.getNBAVideoRealUrl(vid)), new Callback.CommonCallback<String >() {
            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}

            @Override
            public void onFinished() {}

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    String resp = result.replaceAll("QZOutputJson=", "").replaceAll(" ", "").replaceAll("\n", "");
                    if (resp.endsWith(";")) resp = resp.substring(0, resp.length() - 1);
//                    Logger.d("=====视频信息 ：" + resp);
                    NBAVideoInfo videoInfo = JsonParser.parseWithGson(NBAVideoInfo.class, resp);
                    if (videoInfo.vl.vi != null && videoInfo.vl.vi.size() > 0) {
                        String vid = videoInfo.vl.vi.get(0).vid;
                        String vkey = videoInfo.vl.vi.get(0).fvkey;
                        String url = videoInfo.vl.vi.get(0).ul.ui.get(0).url + vid + ".mp4?vkey=" + vkey;
                        requestCallBack.onSuccess(url);
                    }else{
                        requestCallBack.onFailure("解析视频地址失败");
                    }
                }else{
                    requestCallBack.onFailure("获取数据失败");
                }
            }
        });
    }



    /*********************************以下是新闻相关**************************************/


    /**
     * 获取NBA头条的articleIds
     * @param requestCallBack
     */
    public static void getNBANewsHeadlineArticleIds(final RequestCallBack<ModelNBAVideoArticleIds> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsHeadlineArticleIds());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    ModelNBAVideoArticleIds articleIds = JsonParser.parseWithGson(ModelNBAVideoArticleIds.class,result);
                    requestCallBack.onSuccess(articleIds);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleIds 根据articleids 获取新闻数据
     * @param requestCallBack
     */
    public static void getNewsHighlines(String articleIds,  final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsHeadlines(articleIds));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * 获取NBA资讯的articleIds
     * @param requestCallBack
     */
    public static void getNBANewsInformArticleIds(final RequestCallBack<ModelNBAVideoArticleIds> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsInformArticleIds());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    ModelNBAVideoArticleIds articleIds = JsonParser.parseWithGson(ModelNBAVideoArticleIds.class,result);
                    requestCallBack.onSuccess(articleIds);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleIds 根据articleids 获取新闻资讯数据
     * @param requestCallBack
     */
    public static void getNewsInforms(String articleIds,  final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsInform(articleIds));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }





    /**
     * 获取NBA资讯的articleIds
     * @param requestCallBack
     */
    public static void getNBANewsPictureArticleIds(final RequestCallBack<ModelNBAVideoArticleIds> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsPictureArticleIds());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    ModelNBAVideoArticleIds articleIds = JsonParser.parseWithGson(ModelNBAVideoArticleIds.class,result);
                    requestCallBack.onSuccess(articleIds);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleIds 根据articleids 获取新闻资讯数据
     * @param requestCallBack
     */
    public static void getNewsPictures(String articleIds,  final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsPicture(articleIds));
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {}

            @Override
            public void onFinished() {}
        });
    }


    /**
     * @param articleId 获取头天新闻详情
     * @param requestCallBack
     */
    public static void getNBANewsHeadlineDetail(String articleId,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsHeadlineDetail(articleId));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * @param articleId 获取头天新闻详情
     * @param requestCallBack
     */
    public static void getNBANewsInformDetail(String articleId,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBANewsInformDetail(articleId));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**获取图集详情
     * @param articleId
     * @param requestCallBack
     */
    public static void getNBAPictureDetail(String articleId,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAPicturesDetail(articleId));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**根据联盟排名获取数据
     * @param requestCallBack
     */
    public static void getNBATeamRankingByUnion(final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBATeamRankingByUnion());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**根据分区排名获取数据
     * @param requestCallBack
     */
    public static void getNBATeamRankingByDivision(final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBATeamRankingByDivision());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * @param tabType
     * @param statType
     * @param seasonId
     * @param requestCallBack
     */
    public static void getNBADataSort(String tabType,String statType,String seasonId,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAPDataSort(tabType,statType,seasonId));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * @param date
     * @param requestCallBack
     */
    public static void getNBAGameLive(String date,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAGameLive(date));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



    /**
     * @param playerId
     * @param requestCallBack
     */
    public static void getNBAPlayerBaseInfo(String playerId,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAPlayerBaseInfo(playerId));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



    /**
     * @param playerId
     * @param requestCallBack
     */
    public static void getNBAPlayerData(String playerId,String type,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAPlayerData(playerId,type));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * @param requestCallBack 获取球队列表
     */
    public static void getNBATeamList(final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBATeamList());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * @param teamId 获取球队信息
     * @param requestCallBack
     */
    public static void getNBATeamBaseInfo(String teamId,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBATeamBaseInfo(teamId));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



    /**
     * 获取NBA球员列表
     * @param requestCallBack
     */
    public static void getNBAPlayers(final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAPlayerList());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }




    /**
     * 获取比赛基本信息
     * @param requestCallBack
     */
    public static void getNBAGameBaseInfo(String mid,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAGameBaseInfo(mid));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }




    /**
     * 获取图文直播的ids
     * @param requestCallBack
     */
    public static void getNBAGameTextLiveIndex(String mid,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAGameLiveTextIndex(mid));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 获取图文直播的内容
     * @param requestCallBack
     */
    public static void getNBAGameTextLiveInfo(String mid,String ids,final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAGameTextLiveInfo(mid,ids));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



    /**
     * 获取图文直播的统计数据
     * @param requestCallBack
     */
    public static void getNBAGameLiveStatistics(String mid, final RequestCallBack<String> requestCallBack){
        RequestParams params = new RequestParams(NBAApi.getNBAGameLiveStatistics(mid));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !TextUtils.isEmpty(result)) {
                    requestCallBack.onSuccess(result);
                }else {
                    requestCallBack.onFailure("获取数据失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestCallBack.onFailure("获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
