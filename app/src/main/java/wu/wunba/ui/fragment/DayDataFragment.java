package wu.wunba.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.http.NBAApi;
import wu.wunba.ui.activity.NBAPlayerDetialActivity;
import wu.wunba.ui.adapter.NBADataAdapter;
import wu.wunba.model.ModeNBADataBlock;
import wu.wunba.model.ModelNBADataAssist;
import wu.wunba.model.ModelNBADataPoint;
import wu.wunba.model.ModelNBADataRebound;
import wu.wunba.model.ModelNBADataSteal;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/18 02:22
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_nba_data)
public class DayDataFragment extends BaseFragment implements NBADataAdapter.OnRecyclerViewItemClickListener{

    @ViewInject(R.id.iv_type_add)
    ImageView ivTypeAdd;
    @ViewInject(R.id.iv_type_reduce)
    ImageView ivTypeReduce;
    @ViewInject(R.id.tv_stat_type)
    TextView tvStatType;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.iv_no_data)
    ImageView ivNoData;
    @ViewInject(R.id.iv_network_error)
    ImageView iv_network_error;

    private Context mContext;
    private Gson gson;
    private  Callback.Cancelable cancelable;
    public static DayDataFragment dayDataFragment;
    private NBADataAdapter nbaDataAdapter;
    private int currentTypeIndex = 0;
    private String[] TextStatType= {"得分","篮板","助攻","盖帽","抢断"};
    private final String UrlTabType = NBAApi.NBA_DATA_TABTYPE_DAY;
    private final String[] UrlStatType= {
            NBAApi.NBA_DATA_STATTYPE_POINT,
            NBAApi.NBA_DATA_STATTYPE_REBOUND,
            NBAApi.NBA_DATA_STATTYPE_ASSIST,
            NBAApi.NBA_DATA_STATTYPE_BLOCK,
            NBAApi.NBA_DATA_STATTYPE_STEAL};

    private ModelNBADataPoint points;
    private ModelNBADataRebound rebound;
    private ModelNBADataSteal steal;
    private ModelNBADataAssist assist;
    private ModeNBADataBlock block;


    public static DayDataFragment getInstance(){
        if(dayDataFragment == null){
            dayDataFragment = new DayDataFragment();
        }
        return dayDataFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext= getActivity();
        tvStatType.setText(TextStatType[currentTypeIndex]);
        gson = new Gson();
        points = new ModelNBADataPoint();
        rebound = new ModelNBADataRebound();
        assist = new ModelNBADataAssist();
        block = new ModeNBADataBlock();
        steal = new ModelNBADataSteal();
        nbaDataAdapter = new NBADataAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(nbaDataAdapter);
        nbaDataAdapter.setOnItemClickListener(this);
        if(MyUtils.isNetworkConnected(mContext)){
            iv_network_error.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            ballLaodingShow(0);
            getNBAData(NBAApi.getNBAPlayerData(UrlTabType,UrlStatType[currentTypeIndex],"2016"));
        }else{
            recyclerView.setVisibility(View.GONE);
            iv_network_error.setVisibility(View.VISIBLE);
        }
    }


    @Event(type= View.OnClickListener.class,value = R.id.iv_type_add)
    private void typeAddOnClick(View v){
        if(!MyUtils.isNetworkConnected(mContext)){
            Toast.makeText(mContext,"网络连接错误，请检查网络。",Toast.LENGTH_SHORT).show();
            return;
        }
        if(cancelable!=null){
            cancelable.cancel();
        }
        currentTypeIndex++;
        if(currentTypeIndex == 5){
            currentTypeIndex = 0;
        }
        Logger.d("当前 ： " + UrlStatType[currentTypeIndex]);
        ballLaodingShow(0);
        getNBAData(NBAApi.getNBAPlayerData(UrlTabType,UrlStatType[currentTypeIndex],"2016"));
    }

    @Event(type= View.OnClickListener.class,value = R.id.iv_type_reduce)
    private void typeReduceOnClick(View v){
        if(!MyUtils.isNetworkConnected(mContext)){
            Toast.makeText(mContext,"网络连接错误，请检查网络。",Toast.LENGTH_SHORT).show();
            return;
        }
        if(cancelable!=null){
            cancelable.cancel();
        }
        currentTypeIndex --;
        if(currentTypeIndex==-1){
            currentTypeIndex=4;
        }
        ballLaodingShow(0);
        getNBAData(NBAApi.getNBAPlayerData(UrlTabType,UrlStatType[currentTypeIndex],"2016"));
    }


    @Override
    public void onItemClick(View v,int postion) {
        NBAPlayerDetialActivity.startAction(getActivity());
        Toast.makeText(mContext,currentTypeIndex + "  postion : " + postion ,Toast.LENGTH_SHORT).show();
    }

    private void getNBAData(String url) {
        RequestParams requestParams = new RequestParams(url);
        cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ballLaodingDismiss();
                iv_network_error.setVisibility(View.GONE);
                parseNBAData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.e("当前 ： " + ex.toString());
                ballLaodingDismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                ballLaodingDismiss();
                Logger.e("当前 ： onCancelled");
            }

            @Override
            public void onFinished() {
                Logger.e("当前 ： onFinished");
                tvStatType.setText(TextStatType[currentTypeIndex]);
            }
        });
    }

    private void parseNBAData(String result){
        Logger.d( currentTypeIndex + "  返回信息 ：" + result);
        switch (currentTypeIndex){
            case 0:
                ModelNBADataPoint points = gson.fromJson(result,ModelNBADataPoint.class);

                if(points.getData().getPoint().size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    nbaDataAdapter.setPointsData(points.getData().getPoint(),currentTypeIndex);
                }else{
                    ivNoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case 1:
                ModelNBADataRebound rebound = gson.fromJson(result,ModelNBADataRebound.class);
                if(rebound.getData().getRebound().size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    nbaDataAdapter.setReboundData(rebound.getData().getRebound(),currentTypeIndex);
                }else{
                    ivNoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case 2:
                ModelNBADataAssist assist = gson.fromJson(result,ModelNBADataAssist.class);
                if(assist.getData().getAssist().size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    nbaDataAdapter.setAssistData(assist.getData().getAssist(),currentTypeIndex);
                }else{
                    ivNoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                break;
            case 3:
                ModeNBADataBlock  block= gson.fromJson(result,ModeNBADataBlock.class);
                if(block.getData().getBlock().size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    nbaDataAdapter.setBlockData(block.getData().getBlock(),currentTypeIndex);
                }else{
                    ivNoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                break;
            case 4:
                ModelNBADataSteal steal= gson.fromJson(result,ModelNBADataSteal.class);
                if(steal.getData().getSteal().size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    ivNoData.setVisibility(View.GONE);
                    nbaDataAdapter.setStealData(steal.getData().getSteal(),currentTypeIndex);
                }else{
                    ivNoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                break;
        }

    }
}
