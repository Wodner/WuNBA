package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import wu.wunba.R;
import wu.wunba.model.NBAGameLvieStatisticsItem;

/**
 * 描述：
 * 作者：Wu on 2017/3/3 17:12
 * 邮箱：wuwende@live.cn
 */

public class NBAGameLiveStatisticsAdapter extends RecyclerView.Adapter<NBAGameLiveStatisticsAdapter.MyViewHolder>{

    private Context mContext;

    private NBAGameLvieStatisticsItem statisticsItem;

    public NBAGameLiveStatisticsAdapter(Context mContext) {
        this.mContext = mContext;
        statisticsItem = new NBAGameLvieStatisticsItem();
    }

    @Override
    public int getItemCount() {
        return statisticsItem.getRow()==null?0:statisticsItem.getRow().get(0).size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_player_data_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.linearLayout.removeAllViews();
        for (int i=0;i<statisticsItem.getRow().size();i++){
            TextView t = new TextView(mContext);
            t.setLayoutParams(getLinearLayoutTextViewParms(10, 10, 30, 30));
            holder.linearLayout.addView(t);
            t.setText(statisticsItem.getRow().get(i).get(position));
            if(i==0 || i==statisticsItem.getRow().size()-2 ||i==5){
                View view = new View(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height=1;
                params.width =LinearLayout.LayoutParams.MATCH_PARENT;
                view.setLayoutParams(params);
                view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_cc));
                holder.linearLayout.addView(view);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_list);
        }
    }


    public void setData(NBAGameLvieStatisticsItem  statisticsItem ){
        this.statisticsItem = statisticsItem;
        notifyDataSetChanged();
    }

    /**
     * @param t
     * @param b
     * @param l
     * @param r
     * @return
     */
    private LinearLayout.LayoutParams getLinearLayoutTextViewParms(int t, int b, int l, int r) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = t;
        params.bottomMargin = b;
        params.leftMargin = l;
        params.rightMargin = r;
        return params;
    }

}
