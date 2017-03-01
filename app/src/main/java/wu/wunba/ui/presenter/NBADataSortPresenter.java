package wu.wunba.ui.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApi;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.ModelNBADataAssist;
import wu.wunba.model.ModelNBADataBlock;
import wu.wunba.model.ModelNBADataPoint;
import wu.wunba.model.ModelNBADataRebound;
import wu.wunba.model.ModelNBADataSteal;
import wu.wunba.model.NBADataSort;
import wu.wunba.ui.view.NBADataSortView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/25 21:41
 * 邮箱：wuwende@live.cn
 */

public class NBADataSortPresenter implements Presenter {

    public static final int TYPE_TAB_DAY = 0;
    public static final int TYPE_TAB_SEASION = 1;

    public static final int TYPE_STAT_POINT = 0;
    public static final int TYPE_STAT_REBOUND = 1;
    public static final int TYPE_STAT_ASSIST = 2;
    public static final int TYPE_STAT_BLOCK = 3;
    public static final int TYPE_STAT_STEAL = 4;
    private static final String seasonId = "2016";
    private List<NBADataSort> dataSortList;

    public static final String[] UrlStatType= {
            NBAApi.NBA_DATA_STATTYPE_POINT,
            NBAApi.NBA_DATA_STATTYPE_REBOUND,
            NBAApi.NBA_DATA_STATTYPE_ASSIST,
            NBAApi.NBA_DATA_STATTYPE_BLOCK,
            NBAApi.NBA_DATA_STATTYPE_STEAL};

    public static final String[] UrlTabType= {
            NBAApi.NBA_DATA_TABTYPE_DAY,
            NBAApi.NBA_DATA_TABTYPE_SEASON};


    private Context mContext;
    private NBADataSortView nbaDataSortView;

    public NBADataSortPresenter(Context mContext, NBADataSortView nbaDataSortView) {
        this.mContext = mContext;
        this.nbaDataSortView = nbaDataSortView;
        dataSortList = new ArrayList<>();
    }


    @Override
    public void initialized(int type) {
        getNBADataSort(TYPE_TAB_DAY,TYPE_STAT_POINT);
    }




    public void getNBADataSort(final int type_tab,final int type_stat ){
        nbaDataSortView.showLoading(true);
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBADataSort(UrlTabType[type_tab], UrlStatType[type_stat], seasonId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseNBADataSort(type_stat,s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    nbaDataSortView.showError("获取不到数据");
                    nbaDataSortView.hideLoading(true);
                }
            });
        }else {
            nbaDataSortView.showError("0");
        }
    }


    private void parseNBADataSort(int statType,String result){
        dataSortList.clear();
        if(statType==TYPE_STAT_POINT){
            ModelNBADataPoint point = JsonParser.parseWithGson(ModelNBADataPoint.class,result);
            for(int i=0;i<point.getData().getPoint().size();i++ ){
                NBADataSort nbaDataSort = new NBADataSort();
                nbaDataSort.setJerseyNum(point.getData().getPoint().get(i).getJerseyNum());
                nbaDataSort.setPlayerEnName(point.getData().getPoint().get(i).getPlayerEnName());
                nbaDataSort.setPlayerIcon(point.getData().getPoint().get(i).getPlayerIcon());
                nbaDataSort.setPlayerId(point.getData().getPoint().get(i).getPlayerId());
                nbaDataSort.setPlayerName(point.getData().getPoint().get(i).getPlayerName());
                nbaDataSort.setPlayerUrl(point.getData().getPoint().get(i).getPlayerUrl());
                nbaDataSort.setSerial(point.getData().getPoint().get(i).getSerial());
                nbaDataSort.setTeamIcon(point.getData().getPoint().get(i).getTeamIcon());
                nbaDataSort.setTeamId(point.getData().getPoint().get(i).getTeamId());
                nbaDataSort.setTeamName(point.getData().getPoint().get(i).getTeamName());
                nbaDataSort.setTeamUrl(point.getData().getPoint().get(i).getTeamUrl());
                nbaDataSort.setValue(point.getData().getPoint().get(i).getValue());
                dataSortList.add(nbaDataSort);
            }
        }else if(statType==TYPE_STAT_REBOUND){
            ModelNBADataRebound rebound = JsonParser.parseWithGson(ModelNBADataRebound.class,result);
            for(int i=0;i<rebound.getData().getRebound().size();i++ ){
                NBADataSort nbaDataSort = new NBADataSort();
                nbaDataSort.setJerseyNum(rebound.getData().getRebound().get(i).getJerseyNum());
                nbaDataSort.setPlayerEnName(rebound.getData().getRebound().get(i).getPlayerEnName());
                nbaDataSort.setPlayerIcon(rebound.getData().getRebound().get(i).getPlayerIcon());
                nbaDataSort.setPlayerId(rebound.getData().getRebound().get(i).getPlayerId());
                nbaDataSort.setPlayerName(rebound.getData().getRebound().get(i).getPlayerName());
                nbaDataSort.setPlayerUrl(rebound.getData().getRebound().get(i).getPlayerUrl());
                nbaDataSort.setSerial(rebound.getData().getRebound().get(i).getSerial());
                nbaDataSort.setTeamIcon(rebound.getData().getRebound().get(i).getTeamIcon());
                nbaDataSort.setTeamId(rebound.getData().getRebound().get(i).getTeamId());
                nbaDataSort.setTeamName(rebound.getData().getRebound().get(i).getTeamName());
                nbaDataSort.setTeamUrl(rebound.getData().getRebound().get(i).getTeamUrl());
                nbaDataSort.setValue(rebound.getData().getRebound().get(i).getValue());
                dataSortList.add(nbaDataSort);
            }
        }else if(statType==TYPE_STAT_ASSIST){
            ModelNBADataAssist assist = JsonParser.parseWithGson(ModelNBADataAssist.class,result);
            for(int i=0;i<assist.getData().getAssist().size();i++ ){
                NBADataSort nbaDataSort = new NBADataSort();
                nbaDataSort.setJerseyNum(assist.getData().getAssist().get(i).getJerseyNum());
                nbaDataSort.setPlayerEnName(assist.getData().getAssist().get(i).getPlayerEnName());
                nbaDataSort.setPlayerIcon(assist.getData().getAssist().get(i).getPlayerIcon());
                nbaDataSort.setPlayerId(assist.getData().getAssist().get(i).getPlayerId());
                nbaDataSort.setPlayerName(assist.getData().getAssist().get(i).getPlayerName());
                nbaDataSort.setPlayerUrl(assist.getData().getAssist().get(i).getPlayerUrl());
                nbaDataSort.setSerial(assist.getData().getAssist().get(i).getSerial());
                nbaDataSort.setTeamIcon(assist.getData().getAssist().get(i).getTeamIcon());
                nbaDataSort.setTeamId(assist.getData().getAssist().get(i).getTeamId());
                nbaDataSort.setTeamName(assist.getData().getAssist().get(i).getTeamName());
                nbaDataSort.setTeamUrl(assist.getData().getAssist().get(i).getTeamUrl());
                nbaDataSort.setValue(assist.getData().getAssist().get(i).getValue());
                dataSortList.add(nbaDataSort);
            }
        }else if(statType==TYPE_STAT_BLOCK){
            ModelNBADataBlock block = JsonParser.parseWithGson(ModelNBADataBlock.class,result);
            for(int i=0;i<block.getData().getBlock().size();i++ ) {
                NBADataSort nbaDataSort = new NBADataSort();
                nbaDataSort.setJerseyNum(block.getData().getBlock().get(i).getJerseyNum());
                nbaDataSort.setPlayerEnName(block.getData().getBlock().get(i).getPlayerEnName());
                nbaDataSort.setPlayerIcon(block.getData().getBlock().get(i).getPlayerIcon());
                nbaDataSort.setPlayerId(block.getData().getBlock().get(i).getPlayerId());
                nbaDataSort.setPlayerName(block.getData().getBlock().get(i).getPlayerName());
                nbaDataSort.setPlayerUrl(block.getData().getBlock().get(i).getPlayerUrl());
                nbaDataSort.setSerial(block.getData().getBlock().get(i).getSerial());
                nbaDataSort.setTeamIcon(block.getData().getBlock().get(i).getTeamIcon());
                nbaDataSort.setTeamId(block.getData().getBlock().get(i).getTeamId());
                nbaDataSort.setTeamName(block.getData().getBlock().get(i).getTeamName());
                nbaDataSort.setTeamUrl(block.getData().getBlock().get(i).getTeamUrl());
                nbaDataSort.setValue(block.getData().getBlock().get(i).getValue());
                dataSortList.add(nbaDataSort);
            }
        }else if(statType==TYPE_STAT_STEAL){
                ModelNBADataSteal steal = JsonParser.parseWithGson(ModelNBADataSteal.class,result);
                for(int i=0;i<steal.getData().getSteal().size();i++ ) {
                    NBADataSort nbaDataSort = new NBADataSort();
                    nbaDataSort.setJerseyNum(steal.getData().getSteal().get(i).getJerseyNum());
                    nbaDataSort.setPlayerEnName(steal.getData().getSteal().get(i).getPlayerEnName());
                    nbaDataSort.setPlayerIcon(steal.getData().getSteal().get(i).getPlayerIcon());
                    nbaDataSort.setPlayerId(steal.getData().getSteal().get(i).getPlayerId());
                    nbaDataSort.setPlayerName(steal.getData().getSteal().get(i).getPlayerName());
                    nbaDataSort.setPlayerUrl(steal.getData().getSteal().get(i).getPlayerUrl());
                    nbaDataSort.setSerial(steal.getData().getSteal().get(i).getSerial());
                    nbaDataSort.setTeamIcon(steal.getData().getSteal().get(i).getTeamIcon());
                    nbaDataSort.setTeamId(steal.getData().getSteal().get(i).getTeamId());
                    nbaDataSort.setTeamName(steal.getData().getSteal().get(i).getTeamName());
                    nbaDataSort.setTeamUrl(steal.getData().getSteal().get(i).getTeamUrl());
                    nbaDataSort.setValue(steal.getData().getSteal().get(i).getValue());
                    dataSortList.add(nbaDataSort);
                }
        }
        nbaDataSortView.showDataSort(dataSortList);
        nbaDataSortView.hideLoading(true);
    }









}
