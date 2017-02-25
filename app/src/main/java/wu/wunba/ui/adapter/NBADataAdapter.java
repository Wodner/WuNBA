package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.R;
import wu.wunba.model.ModeNBADataBlock;
import wu.wunba.model.ModelNBADataAssist;
import wu.wunba.model.ModelNBADataPoint;
import wu.wunba.model.ModelNBADataRebound;
import wu.wunba.model.ModelNBADataSteal;

/**
 * 描述：NBA数据 适配器
 * 作者：Wu on 2017/2/18 15:46
 * 邮箱：wuwende@live.cn
 */

public class NBADataAdapter extends RecyclerView.Adapter<NBADataAdapter.MyViewHolder> {

    private Context mContext;
    private int dataType =10;
    private int dataSize = 0;
    private List<ModelNBADataPoint.DataBean.PointBean> points ;
    private List<ModelNBADataRebound.DataBean.ReboundBean> rebound;
    private List<ModelNBADataSteal.DataBean.StealBean> steal;
    private List<ModelNBADataAssist.DataBean.AssistBean> assist;
    private List<ModeNBADataBlock.DataBean.BlockBean> block;


    public NBADataAdapter(Context context){
        this.mContext = context;
        points = new ArrayList<>();
        rebound = new ArrayList<>();
        steal = new ArrayList<>();
        assist = new ArrayList<>();
        block = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public NBADataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_nba_data_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        x.view().inject(vh,v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final NBADataAdapter.MyViewHolder holder, int position) {

        switch (dataType){
            case 0:
                if(points==null){
                    return;
                }
                holder.tvRanking.setText("第" + points.get(position).getSerial());
                holder.tvPoints.setText(points.get(position).getValue());
                holder.tvPlayerName.setText(points.get(position).getPlayerName());
                holder.tvTeamName.setText(points.get(position).getTeamName());
                holder.ivPlayer.setTag(points.get(position).getPlayerIcon());
                holder.ivTeam.setTag(points.get(position).getTeamIcon());
                if(holder.ivPlayer.getTag().equals(points.get(position).getPlayerIcon())){
                    x.image().bind(holder.ivPlayer,points.get(position).getPlayerIcon());
                }
                if(holder.ivTeam.getTag().equals(points.get(position).getTeamIcon())){
                    x.image().bind(holder.ivTeam,points.get(position).getTeamIcon());
                }
                break;
            case 1:
                if(rebound==null){
                    return;
                }
                holder.tvRanking.setText("第" + rebound.get(position).getSerial());
                holder.tvPoints.setText(rebound.get(position).getValue());
                holder.tvPlayerName.setText(rebound.get(position).getPlayerName());
                holder.tvTeamName.setText(rebound.get(position).getTeamName());
                holder.ivPlayer.setTag(rebound.get(position).getPlayerIcon());
                holder.ivTeam.setTag(rebound.get(position).getTeamIcon());
                if(holder.ivPlayer.getTag().equals(rebound.get(position).getPlayerIcon())){
                    x.image().bind(holder.ivPlayer,rebound.get(position).getPlayerIcon());
                }
                if(holder.ivTeam.getTag().equals(rebound.get(position).getTeamIcon())){
                    x.image().bind(holder.ivTeam,rebound.get(position).getTeamIcon());
                }
                break;
            case 2:
                if(assist==null){
                    return;
                }
                holder.tvRanking.setText("第" + assist.get(position).getSerial());
                holder.tvPoints.setText(assist.get(position).getValue());
                holder.tvPlayerName.setText(assist.get(position).getPlayerName());
                holder.tvTeamName.setText(assist.get(position).getTeamName());
                holder.ivPlayer.setTag(assist.get(position).getPlayerIcon());
                holder.ivTeam.setTag(assist.get(position).getTeamIcon());
                if(holder.ivPlayer.getTag().equals(assist.get(position).getPlayerIcon())){
                    x.image().bind(holder.ivPlayer,assist.get(position).getPlayerIcon());
                }
                if(holder.ivTeam.getTag().equals(assist.get(position).getTeamIcon())){
                    x.image().bind(holder.ivTeam,assist.get(position).getTeamIcon());
                }
                break;
            case 3:
                if(block==null){
                    return;
                }
                holder.tvRanking.setText("第" + block.get(position).getSerial());
                holder.tvPoints.setText(block.get(position).getValue());
                holder.tvPlayerName.setText(block.get(position).getPlayerName());
                holder.tvTeamName.setText(block.get(position).getTeamName());
                holder.ivPlayer.setTag(block.get(position).getPlayerIcon());
                holder.ivTeam.setTag(block.get(position).getTeamIcon());
                if(holder.ivPlayer.getTag().equals(block.get(position).getPlayerIcon())){
                    x.image().bind(holder.ivPlayer,block.get(position).getPlayerIcon());
                }
                if(holder.ivTeam.getTag().equals(block.get(position).getTeamIcon())){
                    x.image().bind(holder.ivTeam,block.get(position).getTeamIcon());
                }
                break;
            case 4:
                if(steal==null){
                    return;
                }
                holder.tvRanking.setText("第" + steal.get(position).getSerial());
                holder.tvPoints.setText(steal.get(position).getValue());
                holder.tvPlayerName.setText(steal.get(position).getPlayerName());
                holder.tvTeamName.setText(steal.get(position).getTeamName());
                holder.ivPlayer.setTag(steal.get(position).getPlayerIcon());
                holder.ivTeam.setTag(steal.get(position).getTeamIcon());
                if(holder.ivPlayer.getTag().equals(steal.get(position).getPlayerIcon())){
                    x.image().bind(holder.ivPlayer,steal.get(position).getPlayerIcon());
                }
                if(holder.ivTeam.getTag().equals(steal.get(position).getTeamIcon())){
                    x.image().bind(holder.ivTeam,steal.get(position).getTeamIcon());
                }
                break;
        }

        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    onRecyclerViewItemClickListener.onItemClick(v ,holder.getLayoutPosition());
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.iv_player)
        ImageView ivPlayer;
        @ViewInject(R.id.iv_team)
        ImageView ivTeam;
        @ViewInject(R.id.tv_ranking)
        TextView tvRanking;
        @ViewInject(R.id.tv_points)
        TextView tvPoints;
        @ViewInject(R.id.tv_player_name)
        TextView tvPlayerName;
        @ViewInject(R.id.tv_team_name)
        TextView tvTeamName;

        public MyViewHolder(View v){
            super(v);
        }
    }

    public void setPointsData(List<ModelNBADataPoint.DataBean.PointBean> pointData ,int type){
        this.points = pointData;
        dataType = type;
        dataSize = points.size();
        notifyDataSetChanged();
    }

    public void setReboundData(List<ModelNBADataRebound.DataBean.ReboundBean> rebound ,int type){
        this.rebound = rebound;
        dataType = type;
        dataSize = rebound.size();
        notifyDataSetChanged();
    }
    public void setAssistData(List<ModelNBADataAssist.DataBean.AssistBean> assist ,int type){
        this.assist = assist;
        dataType = type;
        dataSize = assist.size();
        notifyDataSetChanged();
    }
    public void setBlockData(List<ModeNBADataBlock.DataBean.BlockBean> block ,int type){
        this.block = block;
        dataType = type;
        dataSize = block.size();
        notifyDataSetChanged();
    }
    public void setStealData(List<ModelNBADataSteal.DataBean.StealBean> steal ,int type){
        this.steal = steal;
        dataType = type;
        dataSize = steal.size();
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }

    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, String msg,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

}
