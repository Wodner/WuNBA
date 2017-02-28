package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import wu.wunba.R;
import wu.wunba.model.NBAPlayerData;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 16:54
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerViewHolder extends RecyclerView.ViewHolder {

    private TextView tvDataType;
    private RecyclerView recyclerView;
    private Context mContext;
    private NBAPlayerData nbaPlayerData;


    public NBAPlayerViewHolder(View itemView) {
        super(itemView);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerview);
        tvDataType = (TextView)itemView.findViewById(R.id.tv_player_data_type);
    }



    public void refreshData(Context context,NBAPlayerData playerData){
        nbaPlayerData = playerData;
        mContext = context;
        tvDataType.setText(playerData.getDataType());
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new HorizontalAdapter());

    }


    private class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        @Override
        public int getItemCount() {
            return nbaPlayerData.getHead().size();
        }


        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_data_list_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.linearLayout.removeAllViews();
            TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_textview, null);
            textView.setLayoutParams(getLinearLayoutTextViewParms(10, 10, 30, 30));
            holder.linearLayout.addView(textView);
            textView.setText(nbaPlayerData.getHead().get(position));
            for (int i = 0; i < nbaPlayerData.getRows().size(); i++) {
                TextView t = new TextView(mContext);
                t.setLayoutParams(getLinearLayoutTextViewParms(10, 10, 30, 30));
                holder.linearLayout.addView(t);
                t.setText(nbaPlayerData.getRows().get(i).get(position));
            }

            Logger.e("------ " + holder.linearLayout.getChildCount());

        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout linearLayout;

            public MyViewHolder(View itemView) {
                super(itemView);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_list);
            }
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





}
