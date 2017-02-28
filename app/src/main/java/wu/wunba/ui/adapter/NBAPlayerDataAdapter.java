package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.R;
import wu.wunba.model.NBAPlayerData;

/**
 * 描述：球员详细信息适配器
 * 作者：Wu on 2017/2/28 16:51
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerDataAdapter extends RecyclerView.Adapter<NBAPlayerViewHolder> {

    private Context mContext;
    private List<NBAPlayerData> playerDataList;

    public NBAPlayerDataAdapter(Context mContext) {
        this.mContext = mContext;
        playerDataList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return playerDataList.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public NBAPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_player_data_list, parent, false);
        NBAPlayerViewHolder vh = new NBAPlayerViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(NBAPlayerViewHolder holder, int position) {
        holder.refreshData(mContext,playerDataList.get(position));
    }


    public void setData(List<NBAPlayerData> dataList){
        playerDataList.clear();
        playerDataList.addAll(dataList);
        notifyDataSetChanged();
    }
}
