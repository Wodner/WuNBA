package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import wu.wunba.model.NBAMatch;
import wu.wunba.utils.ItemAnimHelper;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/19 01:19
 * 邮箱：wuwende@live.cn
 */

public class NBAGameAdapter  extends RecyclerView.Adapter<NBAGameAdapter.NBAGameViewHolder>{


    private Context mContext;
    private List<NBAMatch.DataBean.MatchesBean> gameMatch;
    private ItemAnimHelper itemAnimhelper = new ItemAnimHelper();

    public NBAGameAdapter(Context context) {
        this.mContext = context;
        gameMatch = new ArrayList<>();

    }

    @Override
    public int getItemCount() {
        return gameMatch.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public NBAGameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_nba_game_list, parent, false);
        NBAGameViewHolder vh = new NBAGameViewHolder(v);
        x.view().inject(vh,v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final NBAGameViewHolder holder, final int position) {
        String gameBro="";
        if (gameMatch.get(position).getMatchInfo().getBroadcasters().size()>0){
            for (int i=0;i<gameMatch.get(position).getMatchInfo().getBroadcasters().size();i++){
                gameBro = gameBro+ gameMatch.get(position).getMatchInfo().getBroadcasters().get(i) + " ";
            }
            holder.tvBroWay.setText(gameBro);
        }else{
            holder.tvBroWay.setText("--");
        }
        if(gameMatch.get(position).getMatchInfo().getMatchPeriod().equals("0")){ //未开始
            holder.tvGameTime.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
            holder.tvGameTime.setText(timeSub(gameMatch.get(position).getMatchInfo().getStartTime()));
        }else if(gameMatch.get(position).getMatchInfo().getMatchPeriod().equals("1")){//直播
            holder.tvGameTime.setTextColor(mContext.getResources().getColor(R.color.firebrick));
            holder.tvGameTime.setText("直播  "+gameMatch.get(position).getMatchInfo().getQuarter() + "  " +
                    gameMatch.get(position).getMatchInfo().getQuarterTime());
        }else if(gameMatch.get(position).getMatchInfo().getMatchPeriod().equals("2")){
            holder.tvGameTime.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
            holder.tvGameTime.setText("已结束");
        }
        holder.tvGameType.setText(gameMatch.get(position).getMatchInfo().getMatchDesc());
        holder.tvTeamNameLeft.setText(gameMatch.get(position).getMatchInfo().getLeftName());
        holder.tvTeamNameRight.setText(gameMatch.get(position).getMatchInfo().getRightName());
        if(TextUtils.isEmpty(gameMatch.get(position).getMatchInfo().getLeftGoal() )){
            holder.tvRightTeamPoint.setText("-");
            holder.tvLeftTeamPoint.setText("-");
            holder.tvLeftTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
            holder.tvRightTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
        }else{
            if(Integer.valueOf(gameMatch.get(position).getMatchInfo().getLeftGoal()).intValue()
                    >Integer.valueOf(gameMatch.get(position).getMatchInfo().getRightGoal())){
                holder.tvLeftTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
                holder.tvRightTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));
            }else if(Integer.valueOf(gameMatch.get(position).getMatchInfo().getLeftGoal()).intValue()
                    ==Integer.valueOf(gameMatch.get(position).getMatchInfo().getRightGoal())){
                holder.tvLeftTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
                holder.tvRightTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
            }else{
                holder.tvLeftTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));
                holder.tvRightTeamPoint.setTextColor(mContext.getResources().getColor(R.color.text_black_80));
            }
            holder.tvRightTeamPoint.setText(gameMatch.get(position).getMatchInfo().getRightGoal());
            holder.tvLeftTeamPoint.setText(gameMatch.get(position).getMatchInfo().getLeftGoal());
        }
        holder.ivLeftTeam.setTag(gameMatch.get(position).getMatchInfo().getLeftBadge());
        holder.ivRightTeam.setTag(gameMatch.get(position).getMatchInfo().getRightBadge());
        if(!TextUtils.isEmpty(gameMatch.get(position).getMatchInfo().getRightBadge())){
            if(holder.ivRightTeam.getTag().equals(gameMatch.get(position).getMatchInfo().getRightBadge())){
                Xutils3ImageUtils.display(holder.ivRightTeam,gameMatch.get(position).getMatchInfo().getRightBadge(),
                        Xutils3ImageUtils.getImageOptionsDefault());
            }
            if(holder.ivLeftTeam.getTag().equals(gameMatch.get(position).getMatchInfo().getLeftBadge())){
                Xutils3ImageUtils.display(holder.ivLeftTeam,gameMatch.get(position).getMatchInfo().getLeftBadge(),
                        Xutils3ImageUtils.getImageOptionsDefault());
            }
        }
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v ,gameMatch.get(position),position);
                }
            });
        }
        itemAnimhelper.showItemAnim(holder.linearLayout, position);

    }

    class NBAGameViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.cardview)
        CardView linearLayout;

        @ViewInject(R.id.iv_left_team)
        ImageView ivLeftTeam;
        @ViewInject(R.id.iv_right_team)
        ImageView ivRightTeam;

        @ViewInject(R.id.tv_game_type)
        TextView tvGameType;
        @ViewInject(R.id.tv_game_time)
        TextView tvGameTime;
        @ViewInject(R.id.tv_broadcast_way)
        TextView tvBroWay;
        @ViewInject(R.id.tv_team_name_left)
        TextView tvTeamNameLeft;
        @ViewInject(R.id.tv_team_name_right)
        TextView tvTeamNameRight;
        @ViewInject(R.id.tv_left_team_point)
        TextView tvLeftTeamPoint;
        @ViewInject(R.id.tv_right_team_point)
        TextView tvRightTeamPoint;

        public NBAGameViewHolder(View v){
            super(v);
        }
    }

    /**
     * @param match
     */
    public void setData(List<NBAMatch.DataBean.MatchesBean> match){
        this.gameMatch = match;
        notifyDataSetChanged();
    }


    public String timeSub(String str){
        String [] time = str.split(" ");
        return time[1].substring(0,time[1].length()-3);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, NBAMatch.DataBean.MatchesBean matchesBean, int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }
    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, String msg,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;



    public interface OnScroceBoxPreviewAndHighlightTextLiveOnClickListener {
        void onClick(View v, NBAMatch.DataBean.MatchesBean matchesBean, int position, int type);
    }

    public void setOnScroceBoxPreviewAndHighlightTextLiveClickListener(OnScroceBoxPreviewAndHighlightTextLiveOnClickListener onClickListener){
        this.onScroceBoxPreviewAndHighlightTextLiveOnClickListener = onClickListener;
    }

    private OnScroceBoxPreviewAndHighlightTextLiveOnClickListener onScroceBoxPreviewAndHighlightTextLiveOnClickListener;



}
