package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import wu.wunba.R;
import wu.wunba.model.NBAGameLiveDataInfo;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/9 20:10
 * 邮箱：wuwende@live.cn
 */

public class LiveScoreViewHolder extends GameLiveDataBaseHolder<NBAGameLiveDataInfo> {


    private Context mContext;
    private TextView tvTitle;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;



    public LiveScoreViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerview);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.ll_side_left);
    }


    @Override
    public void refreshData(NBAGameLiveDataInfo data, int position) {
        tvTitle.setText(data.getScore().getText());
        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(horizontalAdapter);
        horizontalAdapter.setData(data);

        linearLayout.removeAllViews();
        TextView t = new TextView(mContext);
        linearLayout.addView(t);

        LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params_text.height = 0;
        params_text.weight=1;
        params_text.topMargin=24;
        params_text.bottomMargin=24;
        params_text.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        t.setLayoutParams(params_text);
        t.setText("球队");
        t.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));

        ImageView ivLeft = new ImageView(mContext);
        linearLayout.addView(ivLeft);
        LinearLayout.LayoutParams params_0 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params_0.height = 0;
        params_0.weight=1;
        params_0.width = 68;
        params_text.bottomMargin=24;
        params_0.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        ivLeft.setLayoutParams(params_0);
        Xutils3ImageUtils.display(ivLeft,data.getTeamInfo().getLeftBadge(),
                Xutils3ImageUtils.getConfigImageOptions(true,R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));
        ImageView ivRight = new ImageView(mContext);
        linearLayout.addView(ivRight);
        ivRight.setLayoutParams(params_0);
        Xutils3ImageUtils.display(ivRight,data.getTeamInfo().getRightBadge(),
                Xutils3ImageUtils.getConfigImageOptions(true,R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));
    }





    private class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>{


        private Context mContext;
        private NBAGameLiveDataInfo liveDataInfo;

        public HorizontalAdapter(Context mContext) {
            this.mContext = mContext;
            liveDataInfo = new NBAGameLiveDataInfo();
        }

        @Override
        public int getItemCount() {
            return liveDataInfo==null?0:liveDataInfo.getScore().getGoals().get(0).getHead().size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lineanlayout, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.linearLayout.removeAllViews();

            LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_text.height = 0;
            params_text.weight=1;
            params_text.topMargin=24;
            params_text.bottomMargin=24;
            params_text.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;

            TextView t = new TextView(mContext);
            holder.linearLayout.addView(t);
            t.setLayoutParams(params_text);
            t.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));
            t.setText(liveDataInfo.getScore().getGoals().get(0).getHead().get(position));

            View view = new View(mContext);
            holder.linearLayout.addView(view);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.height=1;
            params.width =LinearLayout.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(params);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_cc));

            TextView t1 = new TextView(mContext);
            holder.linearLayout.addView(t1);
            t1.setLayoutParams(params_text);
            t1.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));
            t1.setText(liveDataInfo.getScore().getGoals().get(0).getRows().get(0).get(position));

            TextView t2 = new TextView(mContext);
            holder.linearLayout.addView(t2);
            t2.setLayoutParams(params_text);
            t2.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));
            t2.setText(liveDataInfo.getScore().getGoals().get(0).getRows().get(1).get(position));

        }


        class MyViewHolder extends RecyclerView.ViewHolder{

            private LinearLayout linearLayout;

            public MyViewHolder(View itemView) {
                super(itemView);
                linearLayout = (LinearLayout)itemView.findViewById(R.id.ll_item_list);
            }
        }

        public void setData(NBAGameLiveDataInfo liveDataInfo){
            this.liveDataInfo = liveDataInfo;
            notifyDataSetChanged();
        }

    }


}
