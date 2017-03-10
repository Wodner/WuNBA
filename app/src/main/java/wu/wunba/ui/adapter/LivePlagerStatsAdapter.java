package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import wu.wunba.R;
import wu.wunba.model.NBAGameLiveDataInfo;

/**
 * 描述：
 * 作者：Wu on 2017/3/10 13:34
 * 邮箱：wuwende@live.cn
 */

public class LivePlagerStatsAdapter extends BaseHolder<NBAGameLiveDataInfo>  {


    private Context mContext;
    private TextView tvTitle;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;

    public LivePlagerStatsAdapter(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerview);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.ll_side_left);
    }

    @Override
    public void refreshData(NBAGameLiveDataInfo data, int position) {
        tvTitle.setText(data.getPlayerStats().getText());
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
        params_text.topMargin=16;
        params_text.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        t.setLayoutParams(params_text);
        t.setText("球员");


        for(int i=0;i<data.getPlayerStats().getLeft().getPlayerInfoList().size();i++){
            TextView t1 = new TextView(mContext);
            linearLayout.addView(t1);
            t1.setLayoutParams(params_text);
            t1.setText(data.getPlayerStats().getLeft().getPlayerInfoList().get(i).getPlayerName());

        }


    }



    private class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>{


        private Context mContext;
        private NBAGameLiveDataInfo liveDataInfo;

        public HorizontalAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getItemCount() {
            return liveDataInfo==null?0:liveDataInfo.getPlayerStats().getLeft().getHead().size();
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
            params_text.topMargin=16;
            params_text.gravity = Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;

            TextView t = new TextView(mContext);
            holder.linearLayout.addView(t);
            t.setLayoutParams(params_text);
            t.setText(liveDataInfo.getPlayerStats().getLeft().getHead().get(position));

            for (int i=0;i<liveDataInfo.getPlayerStats().getLeft().getPlayerInfoList().size();i++){
                TextView t1 = new TextView(mContext);
                holder.linearLayout.addView(t1);
                t1.setLayoutParams(params_text);
                t1.setText(liveDataInfo.getPlayerStats().getLeft().getPlayerInfoList().get(i).getRow().get(position));
            }


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
