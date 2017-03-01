package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.wunba.R;
import wu.wunba.model.NBAPlayer;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 16:16
 * 邮箱：wuwende@live.cn
 */

public class NBATeamPlayerAdapter extends RecyclerView.Adapter<NBATeamPlayerAdapter.PlayerAdapter> {



    private Context mContext;
    private List<NBAPlayer> nbaPlayerList;

    public NBATeamPlayerAdapter(Context mContext) {
        this.mContext = mContext;
        nbaPlayerList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return nbaPlayerList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public PlayerAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_player_list, parent, false);
        PlayerAdapter vh = new PlayerAdapter(v);
        ButterKnife.bind(vh, v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final PlayerAdapter holder, final int position) {
        holder.tvPlayerChName.setText(nbaPlayerList.get(position).getCnName());
        holder.tvPlayerEnName.setText(nbaPlayerList.get(position).getEnName());
        holder.tvTeamName.setText(nbaPlayerList.get(position).getTeamName());
        holder.tvPositionJerseynum.setText(nbaPlayerList.get(position).getPosition()+".#"+ nbaPlayerList.get(position).getJerseyNum());
        holder.ivPlayer.setTag(nbaPlayerList.get(position).getIcon());
        holder.ivTeam.setTag(nbaPlayerList.get(position).getTeamLogo());
        if(holder.ivPlayer.getTag().equals(nbaPlayerList.get(position).getIcon())){
            x.image().bind(holder.ivPlayer,nbaPlayerList.get(position).getIcon());
        }
        if(holder.ivTeam.getTag().equals(nbaPlayerList.get(position).getTeamLogo())){
            x.image().bind(holder.ivTeam,nbaPlayerList.get(position).getTeamLogo());
        }

        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    onRecyclerViewItemClickListener.onItemClick(v ,nbaPlayerList.get(position),holder.getLayoutPosition());
                }
            });
        }
    }

    class PlayerAdapter extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_player_ch_name)
        TextView tvPlayerChName;
        @Bind(R.id.tv_player_en_name)
        TextView tvPlayerEnName;
        @Bind(R.id.tv_team_name)
        TextView tvTeamName;
        @Bind(R.id.tv_position_jerseynum)
        TextView tvPositionJerseynum;
        @Bind(R.id.iv_team)
        ImageView ivTeam;
        @Bind(R.id.iv_player)
        ImageView ivPlayer;
        public PlayerAdapter(View itemView) {
            super(itemView);
        }
    }


    public void setData(List<NBAPlayer> playerList){
        nbaPlayerList.clear();
        nbaPlayerList.addAll(playerList);
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, NBAPlayer nbaPlayer, int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }

    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, NBAPlayer nbaPlayer,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;
}
