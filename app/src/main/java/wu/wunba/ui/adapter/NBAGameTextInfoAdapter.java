package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.wunba.R;
import wu.wunba.model.NBAGameTextLiveItem;

/**
 * 描述：
 * 作者：Wu on 2017/3/2 13:11
 * 邮箱：wuwende@live.cn
 */

public class NBAGameTextInfoAdapter extends RecyclerView.Adapter<NBAGameTextInfoAdapter.TextLiveInfoViewHolder> {



    private Context mContext;
    private List<NBAGameTextLiveItem> textLiveItemList;

    public NBAGameTextInfoAdapter(Context mContext) {
        this.mContext = mContext;
        textLiveItemList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return textLiveItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public TextLiveInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_game_text_info_list, parent, false);
        TextLiveInfoViewHolder holder = new TextLiveInfoViewHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }


    @Override
    public void onBindViewHolder(TextLiveInfoViewHolder holder, int position) {
        holder.tvQuart.setText(textLiveItemList.get(position).getQuarter());
        holder.tvTime.setText(textLiveItemList.get(position).getTime());
        holder.tvTeamName.setText(textLiveItemList.get(position).getTeamName());
        holder.tvPlus.setText(textLiveItemList.get(position).getPlus());
        if(textLiveItemList.get(position).getPlus().equals("")){
            holder.tvPoints.setVisibility(View.GONE);
        }else {
            holder.tvPoints.setVisibility(View.VISIBLE);
            holder.tvPoints.setText(textLiveItemList.get(position).getLeftGoal() + ":"+
            textLiveItemList.get(position).getRightGoal());
        }
        holder.tvContent.setText(textLiveItemList.get(position).getContent());
    }


    class TextLiveInfoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_quart)
        TextView tvQuart;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_team_name)
        TextView tvTeamName;
        @Bind(R.id.tv_plus)
        TextView tvPlus;
        @Bind(R.id.tv_points)
        TextView tvPoints;
        @Bind(R.id.tv_content)
        TextView tvContent;

        public TextLiveInfoViewHolder(View itemView) {
            super(itemView);
        }
    }


    public void setData(List<NBAGameTextLiveItem> infoList) {
        textLiveItemList.clear();
        textLiveItemList.addAll(infoList);
        notifyDataSetChanged();
    }
}
