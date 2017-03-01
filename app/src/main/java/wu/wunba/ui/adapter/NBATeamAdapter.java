package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import wu.wunba.model.NBATeam;
import wu.wunba.utils.ItemAnimHelper;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 10:34
 * 邮箱：wuwende@live.cn
 */

public class NBATeamAdapter extends RecyclerView.Adapter<NBATeamAdapter.TeamViewHolder> {


    private Context mContext;
    private List<NBATeam> nbaTeamList;
    private ItemAnimHelper itemAnimhelper = new ItemAnimHelper();

    public NBATeamAdapter(Context mContext) {
        this.mContext = mContext;
        nbaTeamList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return nbaTeamList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_nba_team_list, parent, false);
        TeamViewHolder vh = new TeamViewHolder(view);
        ButterKnife.bind(vh, view);
        return vh;
    }


    @Override
    public void onBindViewHolder(TeamViewHolder holder, final int position) {
        holder.tvTeamName.setText(nbaTeamList.get(position).getTeamName());
        holder.ivTeamIcon.setTag(nbaTeamList.get(position).getLogo());
        if(holder.ivTeamIcon.getTag()!=null && holder.ivTeamIcon.getTag().equals(nbaTeamList.get(position).getLogo())){
            Xutils3ImageUtils.display(holder.ivTeamIcon,nbaTeamList.get(position).getLogo(),
                    Xutils3ImageUtils.getImageOptionsDefault());
        }
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v ,nbaTeamList.get(position),position);
                }
            });
        }
        itemAnimhelper.showItemAnim(holder.linearLayout, position);
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cardview)
        CardView linearLayout;
        @Bind(R.id.iv_team_icon)
        ImageView ivTeamIcon;
        @Bind(R.id.tv_team_name)
        TextView tvTeamName;
        public TeamViewHolder(View itemView) {
            super(itemView);
        }
    }


    public void setData(List<NBATeam> teamList){
        nbaTeamList.clear();
        nbaTeamList.addAll(teamList);
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
//        Logger.d("注册点击事件 -----------");
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, NBATeam nbaTeam, int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }

    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, NBATeam nbaTeam,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

}
