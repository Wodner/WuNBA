package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.wunba.R;
import wu.wunba.model.NBATeamRank;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：球队排名adapter
 * 作者：Wu on 2017/2/24 20:50
 * 邮箱：wuwende@live.cn
 */

public class NBATeamRankAdapter extends RecyclerView.Adapter<NBATeamRankAdapter.TeamRankViewHolder> {



    private Context mContext;
    private List<NBATeamRank> nbaTeamRankList;

    public NBATeamRankAdapter(Context mContext) {
        this.mContext = mContext;
        nbaTeamRankList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return nbaTeamRankList.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public TeamRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_team_ranking, parent, false);
        TeamRankViewHolder vh = new TeamRankViewHolder(v);
        ButterKnife.bind(vh, v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TeamRankViewHolder holder, final int position) {
        holder.tvTeamName.setText(nbaTeamRankList.get(position).getName());
        holder.tvRankNum.setText(nbaTeamRankList.get(position).getRankNum());
        holder.tvTeamWin.setText(nbaTeamRankList.get(position).getWin());
        holder.tvTeamLose.setText(nbaTeamRankList.get(position).getLose());
        holder.tvTeamWinProbility.setText(nbaTeamRankList.get(position).getWinProbility());
        holder.tvTeamWinBehind.setText(nbaTeamRankList.get(position).getWinBind());
        holder.ivTeamIcon.setTag(nbaTeamRankList.get(position).getBadge());
        if(holder.ivTeamIcon.getTag()!=null&&holder.ivTeamIcon.getTag().equals(nbaTeamRankList.get(position).getBadge())){
            Xutils3ImageUtils.display(holder.ivTeamIcon,nbaTeamRankList.get(position).getBadge(),Xutils3ImageUtils.getImageOptionsDefault());
        }
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v ,nbaTeamRankList.get(position),position);
                }
            });
        }
    }

    class TeamRankViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_rank_num)
        TextView tvRankNum;
        @Bind(R.id.iv_team_icon)
        ImageView ivTeamIcon;
        @Bind(R.id.tv_team_name)
        TextView tvTeamName;
        @Bind(R.id.tv_team_win)
        TextView tvTeamWin;
        @Bind(R.id.tv_team_lose)
        TextView tvTeamLose;
        @Bind(R.id.tv_team_winProbility)
        TextView tvTeamWinProbility;
        @Bind(R.id.tv_team_winBehind)
        TextView tvTeamWinBehind;
        public TeamRankViewHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * @param list
     */
    public void setData(List<NBATeamRank> list){
        nbaTeamRankList.clear();
        nbaTeamRankList.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, NBATeamRank nbaTeamRank, int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }

    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, NBATeamRank nbaTeamRank,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

}
