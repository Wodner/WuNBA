package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.wunba.R;
import wu.wunba.model.NBAGameLiveDataInfo;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/9 20:10
 * 邮箱：wuwende@live.cn
 */

public class LiveMaxPlayerViewHolder extends GameLiveDataBaseHolder<NBAGameLiveDataInfo> {

    private Context mContext;
    private TextView tvTitle;
    private RecyclerView recyclerView;

    public LiveMaxPlayerViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
    }

    @Override
    public void refreshData(NBAGameLiveDataInfo data, int position) {
        tvTitle.setText(data.getMaxPlayer().getText());
        MaxPlayerAdapter maxPlayerAdapter = new MaxPlayerAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(maxPlayerAdapter);
        maxPlayerAdapter.setData(data);
    }


    class MaxPlayerAdapter extends RecyclerView.Adapter<MaxPlayerAdapter.MyViewHolder> {

        private Context mContext;
        private NBAGameLiveDataInfo liveDataInfo;

        public MaxPlayerAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getItemCount() {
            return liveDataInfo==null?0:liveDataInfo.getMaxPlayer().getMaxPlayers().size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_max_player_list, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            ButterKnife.bind(holder,view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tvLeftPlayerName.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getLeftPlayer().getName());
            holder.tvLeftScore.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getLeftVal());
            holder.tvLeftPlayerPositionJersey.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getLeftPlayer().getPosition()
                + "  " +  "#" + liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getLeftPlayer().getJerseyNum());
            Xutils3ImageUtils.display(holder.ivLeftPlayerIcon,liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getLeftPlayer().getIcon(),
                    Xutils3ImageUtils.getConfigImageOptions(true,R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));

            holder.tvRightPlayerName.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getRightPlayer().getName());
            holder.tvRightScore.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getRightVal());
            holder.tvRightPlayerPositionJersey.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getRightPlayer().getPosition()
                    + "  " +  "#" + liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getRightPlayer().getJerseyNum());
            Xutils3ImageUtils.display(holder.ivRightPlayerIcon,liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getRightPlayer().getIcon(),
                    Xutils3ImageUtils.getConfigImageOptions(true,R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));

            holder.tvNbaCategory.setText(liveDataInfo.getMaxPlayer().getMaxPlayers().get(position).getText());

        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.iv_left_player_icon)
            ImageView ivLeftPlayerIcon;
            @Bind(R.id.tv_left_score)
            TextView tvLeftScore;
            @Bind(R.id.tv_left_player_name)
            TextView tvLeftPlayerName;
            @Bind(R.id.tv_left_player_position_jersey)
            TextView tvLeftPlayerPositionJersey;
            @Bind(R.id.tv_nba_category)
            TextView tvNbaCategory;
            @Bind(R.id.tv_right_score)
            TextView tvRightScore;
            @Bind(R.id.iv_right_player_icon)
            ImageView ivRightPlayerIcon;
            @Bind(R.id.tv_right_player_name)
            TextView tvRightPlayerName;
            @Bind(R.id.tv_right_player_position_jersey)
            TextView tvRightPlayerPositionJersey;

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }

        public void setData(NBAGameLiveDataInfo liveDataInfo) {
            this.liveDataInfo = liveDataInfo;
            notifyDataSetChanged();
        }
    }


}
