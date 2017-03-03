package wu.wunba.ui.presenter;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAGameLvieStatisticsItem;
import wu.wunba.ui.view.NBAGameLiveStatisticsView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/3 14:55
 * 邮箱：wuwende@live.cn
 */

public class NBAGameLiveStatisticsPresenter implements Presenter {

    private Context mContext;
    private NBAGameLiveStatisticsView statisticsView;
    private boolean isFirst=true;
    private int typeCount;
    private List<List<String>> leftRows = new ArrayList<>();
    private List<List<String>> rightRows = new ArrayList<>();
    private NBAGameLvieStatisticsItem leftStatisticsItems = new NBAGameLvieStatisticsItem();
    private NBAGameLvieStatisticsItem rightStatisticsItems = new NBAGameLvieStatisticsItem();

    public NBAGameLiveStatisticsPresenter(Context mContext, NBAGameLiveStatisticsView statisticsView) {
        this.mContext = mContext;
        this.statisticsView = statisticsView;
    }

    @Override
    public void initialized(int type) {

    }


    /**
     * @param mid
     */
    public void getNBAGameLiveStatistics(boolean isShow,String mid){
        if(isShow){
            statisticsView.showLoading(true);
        }
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBAGameLiveStatistics(mid, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseLiveStatistics(s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    statisticsView.showError("请求数据失败");
                    statisticsView.hideLoading(true);
                }
            });
        }else {
            statisticsView.showError("0");
            statisticsView.hideLoading(true);
        }
    }



    /**
     * @param result
     */
    private void parseLiveStatistics(String result){
        leftRows.clear();
        rightRows.clear();
        isFirst=true;
        typeCount =0;
        String strData = JsonParser.parseWithJSONObjectKey("data",result);
        String strStats  = JsonParser.parseWithJSONObjectKey("stats",strData);
        try {
            JSONArray jsonArray = new JSONArray(strStats);
            String playerStats = JsonParser.parseWithJSONObjectKey("playerStats",jsonArray.get(0).toString());
            JSONArray statisticsArray = new JSONArray(playerStats);
            for (int i=0;i<statisticsArray.length();i++){
                JSONObject object = new JSONObject(statisticsArray.get(i).toString());
                if(isFirst){
                    if(object.has("subText")){
                        typeCount ++;
                        if (typeCount==1){
                            leftStatisticsItems.setSubText("left");
                        }
                        if(typeCount==2){
                            isFirst=false;
                            rightStatisticsItems.setSubText("right");
                        }
                    }
                    if(object.has("head")){
                        JSONArray headArray = new JSONArray(object.getString("head").toString());
                        List<String> headList = new ArrayList<>();
                        for (int n=0;n<headArray.length();n++){
                            headList.add(headArray.get(n).toString());
                        }
                        leftRows.add(headList);
                        leftStatisticsItems.setHead(headList);
                    }
                    if(object.has("row")){
                        List<List<String>> rows = new ArrayList<>();
                        JSONArray rowArray = new JSONArray(object.getString("row").toString());
                        List<String> rowList = new ArrayList<>();
                        for (int n=0;n<rowArray.length();n++){
                            rowList.add(rowArray.get(n).toString());
                        }
                        rows.add(rowList);
                        leftRows.add(rowList);

                    }
                }else {
                    if(object.has("head")){
                        JSONArray headArray = new JSONArray(object.getString("head").toString());
                        List<String> headList = new ArrayList<>();
                        for (int n=0;n<headArray.length();n++){
                            headList.add(headArray.get(n).toString());
                        }
                        rightRows.add(headList);
                        rightStatisticsItems.setHead(headList);
                    }
                    if(object.has("row")){
                        List<List<String>> rows = new ArrayList<>();
                        JSONArray rowArray = new JSONArray(object.getString("row").toString());
                        List<String> rowList = new ArrayList<>();
                        for (int n=0;n<rowArray.length();n++){
                            rowList.add(rowArray.get(n).toString());
                        }
                        rows.add(rowList);
                        rightRows.add(rowList);
                    }
                }
            }
            leftStatisticsItems.setRow(leftRows);
            rightStatisticsItems.setRow(rightRows);
            setLeftSideByLeft(leftStatisticsItems);
            setLeftSideByRight(rightStatisticsItems);
            statisticsView.hideLoading(true);
        } catch (JSONException e) {
            e.printStackTrace();
            statisticsView.showError("解析数据失败");
            statisticsView.hideLoading(true);
        }
    }

    private List<String> leftSide = new ArrayList<>();
    private List<String> rightSide = new ArrayList<>();

    /**
     * @param statisticsItems
     */
    private void setLeftSideByLeft(NBAGameLvieStatisticsItem statisticsItems){
        leftSide.clear();
        for (int i=0;i<statisticsItems.getRow().size();i++){
            leftSide.add(statisticsItems.getRow().get(i).get(0));
            statisticsItems.getRow().get(i).remove(0);
        }
        statisticsView.showLefeStatistics(leftSide,statisticsItems);
    }


    /**
     * @param statisticsItems
     */
    private void setLeftSideByRight(NBAGameLvieStatisticsItem statisticsItems){
        rightSide.clear();
        for (int i=0;i<statisticsItems.getRow().size();i++){
            rightSide.add(statisticsItems.getRow().get(i).get(0));
            statisticsItems.getRow().get(i).remove(0);
        }
        statisticsView.showRightStatistics(rightSide,statisticsItems);
    }

}
