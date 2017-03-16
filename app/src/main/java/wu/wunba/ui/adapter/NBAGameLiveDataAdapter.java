package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import wu.wunba.R;
import wu.wunba.model.NBAGameLiveDataInfo;

/**
 * 描述：
 * 作者：Wu on 2017/3/9 19:41
 * 邮箱：wuwende@live.cn
 */

public class NBAGameLiveDataAdapter extends RecyclerView.Adapter<GameLiveDataBaseHolder>{


    private final int VIEW_SCORE = 1000;
    private final int VIEW_MAX_PLAYER = 1001;
    private final int VIEW_TEAM_DATA = 1002;

    private Context mContext;
    private NBAGameLiveDataInfo gameLiveDataInfo;

    public NBAGameLiveDataAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0) return VIEW_SCORE;
        if(position == 1) return VIEW_MAX_PLAYER;
        return VIEW_TEAM_DATA;
    }

    @Override
    public int getItemCount() {
        return gameLiveDataInfo == null?0:3;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public GameLiveDataBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_SCORE:
                View view_score = LayoutInflater.from(mContext).inflate(R.layout.item_live_score,parent,false);
                return new LiveScoreViewHolder(view_score,mContext);
            case VIEW_MAX_PLAYER:
                if(gameLiveDataInfo.getMatchPeriod().equals("1")){
                    View view_player_stats = LayoutInflater.from(mContext).inflate(R.layout.item_live_score,parent,false);
                    return new LivePlagerStatsAdapter(view_player_stats,mContext);
                }else {
                    View view_max_palyer = LayoutInflater.from(mContext).inflate(R.layout.item_live_max_player,parent,false);
                    return new LiveMaxPlayerViewHolder(view_max_palyer,mContext);
                }
            case VIEW_TEAM_DATA:
                View view_team_data = LayoutInflater.from(mContext).inflate(R.layout.item_live_team_data,parent,false);
                return new LiveTeamDataViewHolder(view_team_data,mContext);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(GameLiveDataBaseHolder holder, int position) {
        holder.refreshData(gameLiveDataInfo,position);
    }



    public void setData(NBAGameLiveDataInfo liveDataInfo){
        this.gameLiveDataInfo = liveDataInfo;

        Logger.e("=== " + gameLiveDataInfo.getScore().getText());
        notifyDataSetChanged();
    }
}
