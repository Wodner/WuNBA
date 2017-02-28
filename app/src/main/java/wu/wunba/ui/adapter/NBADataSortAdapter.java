package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.R;
import wu.wunba.model.NBADataSort;
import wu.wunba.utils.ItemAnimHelper;

/**
 * 描述：NBA数据 适配器
 * 作者：Wu on 2017/2/18 15:46
 * 邮箱：wuwende@live.cn
 */

public class NBADataSortAdapter extends RecyclerView.Adapter<NBADataSortAdapter.MyViewHolder> {

    private Context mContext;
    private int dataType =10;
    private int dataSize = 0;
    private List<NBADataSort> nbaDataSortList;
    private ItemAnimHelper itemAnimhelper = new ItemAnimHelper();


    public NBADataSortAdapter(Context context){
        this.mContext = context;
        nbaDataSortList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return nbaDataSortList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public NBADataSortAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_nba_data_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        x.view().inject(vh,v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final NBADataSortAdapter.MyViewHolder holder, int position) {
        holder.tvRanking.setText("第" + nbaDataSortList.get(position).getSerial());
        holder.tvPoints.setText(nbaDataSortList.get(position).getValue());
        holder.tvPlayerName.setText(nbaDataSortList.get(position).getPlayerName());
        holder.tvTeamName.setText(nbaDataSortList.get(position).getTeamName());
        holder.ivPlayer.setTag(nbaDataSortList.get(position).getPlayerIcon());
        holder.ivTeam.setTag(nbaDataSortList.get(position).getTeamIcon());
        if(holder.ivPlayer.getTag().equals(nbaDataSortList.get(position).getPlayerIcon())){
            x.image().bind(holder.ivPlayer,nbaDataSortList.get(position).getPlayerIcon());
        }
        if(holder.ivTeam.getTag().equals(nbaDataSortList.get(position).getTeamIcon())){
            x.image().bind(holder.ivTeam,nbaDataSortList.get(position).getTeamIcon());
        }
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    onRecyclerViewItemClickListener.onItemClick(v ,nbaDataSortList.get(position),holder.getLayoutPosition());
                }
            });
        }
//        itemAnimhelper.showItemAnim(holder.linearLayout, position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.rl_data)
        RelativeLayout linearLayout;
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


    /**
     * @param dataSortList
     */
    public void setData(List<NBADataSort> dataSortList){
        nbaDataSortList.clear();
        this.nbaDataSortList .addAll(dataSortList);
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, NBADataSort nbaDataSort,int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }

    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, NBADataSort nbaDataSort,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

}
