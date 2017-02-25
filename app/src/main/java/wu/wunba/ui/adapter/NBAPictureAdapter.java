package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.R;
import wu.wunba.model.NBANews;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/23 14:41
 * 邮箱：wuwende@live.cn
 */

public class NBAPictureAdapter extends RecyclerView.Adapter<NBAPictureAdapter.NewsPicViewHolder>{

    private Context mContext;
    private List<NBANews> nbaNewsList;


    public NBAPictureAdapter(Context mContext) {
        this.mContext = mContext;
        nbaNewsList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return nbaNewsList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public NewsPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_picture_list,parent,false);
        NewsPicViewHolder holder = new NewsPicViewHolder(view);
        x.view().inject(holder,view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final NewsPicViewHolder holder, final int position) {
        holder.tvFavNum.setText(nbaNewsList.get(position).getUpNum());
        holder.tvCommNum.setText(nbaNewsList.get(position).getCommentNum());
        holder.tvPubTime.setText(nbaNewsList.get(position).getPub_time_new());
        holder.tvpicTitle.setText(nbaNewsList.get(position).getTitle());

        if(nbaNewsList.get(position).getImages_3().size()>0){
            holder.ivPic_1.setTag(nbaNewsList.get(position).getImages_3().get(0));

            if(holder.ivPic_1.getTag().equals(nbaNewsList.get(position).getImages_3().get(0))){
                Xutils3ImageUtils.display(holder.ivPic_1,nbaNewsList.get(position).getImages_3().get(0),
                        Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));
            }
            holder.ivPic_2.setTag(nbaNewsList.get(position).getImages_3().get(1));
            if(holder.ivPic_2.getTag().equals(nbaNewsList.get(position).getImages_3().get(1))){
                Xutils3ImageUtils.display(holder.ivPic_2,nbaNewsList.get(position).getImages_3().get(1),
                        Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));
            }
            holder.ivPic_3.setTag(nbaNewsList.get(position).getImages_3().get(2));
            if(holder.ivPic_3.getTag().equals(nbaNewsList.get(position).getImages_3().get(2))){
                Xutils3ImageUtils.display(holder.ivPic_3,nbaNewsList.get(position).getImages_3().get(2),
                        Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_social_loading150_150px,R.mipmap.latest_pic_social_loading150_150px));
            }
        }
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v ,nbaNewsList.get(position),position);
                }
            });
        }

    }

    class NewsPicViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.ll_item_list)
        LinearLayout linearLayout;
        @ViewInject(R.id.iv_nbapic_1)
        ImageView ivPic_1;
        @ViewInject(R.id.iv_nbapic_2)
        ImageView ivPic_2;
        @ViewInject(R.id.iv_nbapic_3)
        ImageView ivPic_3;
        @ViewInject(R.id.tv_pic_title)
        TextView tvpicTitle;
        @ViewInject(R.id.tv_pub_time)
        TextView tvPubTime;
        @ViewInject(R.id.tv_fav_num)
        TextView tvFavNum;
        @ViewInject(R.id.tv_comm_num)
        TextView tvCommNum;

        public NewsPicViewHolder(View view){
            super(view);
        }
    }

    /**
     * @param newsList
     */
    public void setData(List<NBANews> newsList){
        nbaNewsList.clear();
        nbaNewsList .addAll(newsList);
        notifyDataSetChanged();
    }


    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        Logger.d("注册点击事件 -----------");
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, NBANews nbaNews, int postion);
    }

    public void setOnLongRecyclerViewItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }

    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, NBANews nbaNews,int postion);
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;


}

