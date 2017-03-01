package wu.wunba.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import wu.wunba.R;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBANews;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/23 00:42
 * 邮箱：wuwende@live.cn
 */

public class NBANewsAdapter extends RecyclerView.Adapter<NBANewsAdapter.NewsViewHolder>{


    private Context mContext;
    private List<NBANews> nbaNewsList;

    public NBANewsAdapter(Context mContext) {
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
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nba_news_list,parent,false);
        NewsViewHolder holder = new NewsViewHolder(view);
        x.view().inject(holder,view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        if(nbaNewsList.get(position).getAtype().equals("0")){//图片类型
            holder.videoPlayer.setVisibility(View.GONE);
            holder.ivNewCover.setVisibility(View.VISIBLE);
            holder.tvImageNum.setVisibility(View.GONE);
            holder.tvNewTime.setText(nbaNewsList.get(position).getPub_time_new());
            holder.tvNewTitle.setText(nbaNewsList.get(position).getTitle());
            holder.tvFavNum.setText(nbaNewsList.get(position).getUpNum());
            holder.tvCommNum.setText(nbaNewsList.get(position).getCommentNum());
            holder.ivNewCover.setTag(nbaNewsList.get(position).getImgurl());
            if(holder.ivNewCover.getTag().equals(nbaNewsList.get(position).getImgurl())){
                Xutils3ImageUtils.display(holder.ivNewCover,nbaNewsList.get(position).getImgurl(),
                        Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_head_loading_720px,R.mipmap.latest_pic_head_loading_720px));
            }
        }else if(nbaNewsList.get(position).getAtype().equals("2")){//视频
            holder.videoPlayer.setVisibility(View.VISIBLE);
            holder.ivNewCover.setVisibility(View.GONE);
            holder.tvImageNum.setVisibility(View.GONE);
            holder.tvNewTime.setText(nbaNewsList.get(position).getPub_time_new());
            holder.tvNewTitle.setText(nbaNewsList.get(position).getTitle());
            holder.tvFavNum.setText(nbaNewsList.get(position).getUpNum());
            holder.tvCommNum.setText(nbaNewsList.get(position).getCommentNum());
            holder.videoPlayer.thumbImageView.setTag(nbaNewsList.get(position).getImgurl());
            if(holder.videoPlayer.thumbImageView.getTag().equals(nbaNewsList.get(position).getImgurl())){
                Xutils3ImageUtils.display(holder.videoPlayer.thumbImageView,nbaNewsList.get(position).getImgurl(),
                        Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_head_loading_720px,R.mipmap.latest_pic_head_loading_720px));
            }
            NBAApiRequest.getNBAVideoRealUrl(nbaNewsList.get(position).getVid(), new RequestCallBack<String>() {
                @Override
                public void onSuccess(String url) {
                    holder.videoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                }

                @Override
                public void onFailure(String errorMsg) {

                }
            });
        }else if(nbaNewsList.get(position).getAtype().equals("1")){
            holder.videoPlayer.setVisibility(View.GONE);
            holder.ivNewCover.setVisibility(View.VISIBLE);
            holder.tvImageNum.setVisibility(View.VISIBLE);
            holder.tvImageNum.setText(nbaNewsList.get(position).getFooter());
            holder.tvNewTime.setText(nbaNewsList.get(position).getPub_time_new());
            holder.tvNewTitle.setText(nbaNewsList.get(position).getTitle());
            holder.tvFavNum.setText(nbaNewsList.get(position).getUpNum());
            holder.tvCommNum.setText(nbaNewsList.get(position).getCommentNum());
            holder.ivNewCover.setTag(nbaNewsList.get(position).getImgurl());
            if(holder.ivNewCover.getTag().equals(nbaNewsList.get(position).getImgurl())){
                Xutils3ImageUtils.display(holder.ivNewCover,nbaNewsList.get(position).getImgurl(),
                        Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_head_loading_720px,R.mipmap.latest_pic_head_loading_720px));
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



    class NewsViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.cardview)
        CardView linearLayout;
        @ViewInject(R.id.iv_new_cover)
        ImageView ivNewCover;
        @ViewInject(R.id.videoplayer)
        JCVideoPlayerStandard videoPlayer;
        @ViewInject(R.id.tv_new_time)
        TextView tvNewTime;
        @ViewInject(R.id.tv_news_title)
        TextView tvNewTitle;
        @ViewInject(R.id.tv_fav_num)
        TextView tvFavNum;
        @ViewInject(R.id.tv_comm_num)
        TextView tvCommNum;

        @ViewInject(R.id.tv_imager_num)
        TextView tvImageNum;

        public NewsViewHolder(View view){
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
//        Logger.d("注册点击事件 -----------");
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
