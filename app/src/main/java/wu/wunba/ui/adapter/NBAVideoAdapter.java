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
import wu.wunba.model.NBAVideo;
import wu.wunba.utils.MyUtils;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：NBA视频适配器
 * 作者：Wu on 2017/2/21 11:39
 * 邮箱：wuwende@live.cn
 */

public class NBAVideoAdapter extends RecyclerView.Adapter<NBAVideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<NBAVideo> modeNBAVideoList;

    public NBAVideoAdapter(Context context){
        this.mContext = context;
        modeNBAVideoList = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        return modeNBAVideoList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_nba_video_list, parent, false);
        VideoViewHolder vh = new VideoViewHolder(v);
        x.view().inject(vh,v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {
        holder.tvVideoTitle.setText(modeNBAVideoList.get(position).getTitle());
        NBAApiRequest.getNBAVideoRealUrl(modeNBAVideoList.get(position).getVid(), new RequestCallBack<String>() {
            @Override
            public void onSuccess(String url) {
                holder.videoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
        holder.videoPlayer.thumbImageView.setTag(modeNBAVideoList.get(position).getImgurl());
        if(holder.videoPlayer.thumbImageView.getTag().equals(modeNBAVideoList.get(position).getImgurl())){
            Xutils3ImageUtils.display(holder.videoPlayer.thumbImageView,modeNBAVideoList.get(position).getImgurl(),
                    Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_head_loading_720px,R.mipmap.latest_pic_head_loading_720px));
        }
        holder.tvPubTime.setText(modeNBAVideoList.get(position).getPub_time_new());
        holder.tvCommNum.setText(modeNBAVideoList.get(position).getCommentNum());
        holder.tvFavNum.setText(modeNBAVideoList.get(position).getUpNum());
        holder.tvDuration.setText(modeNBAVideoList.get(position).getDuration());

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.showShare(mContext,
                        modeNBAVideoList.get(position).getImgurl(),

                        modeNBAVideoList.get(position).getUrl(),

                        modeNBAVideoList.get(position).getTitle(),
                        modeNBAVideoList.get(position).getAbstractX());
            }
        });
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.cardview)
        CardView linearLayout;
        @ViewInject(R.id.videoplayer)
        JCVideoPlayerStandard videoPlayer;
        @ViewInject(R.id.tv_video_title)
        TextView tvVideoTitle;
        @ViewInject(R.id.tv_pub_time)
        TextView tvPubTime;
        @ViewInject(R.id.tv_fav_num)
        TextView tvFavNum;
        @ViewInject(R.id.tv_comm_num)
        TextView tvCommNum;
        @ViewInject(R.id.iv_share)
        ImageView ivShare;
        @ViewInject(R.id.tv_video_duration)
        TextView tvDuration;

        public VideoViewHolder (View view){
            super(view);
        }
    }


    /**
     * @param videoList
     */
    public void setData(List<NBAVideo> videoList){
        modeNBAVideoList.clear();
        modeNBAVideoList .addAll(videoList);
        notifyDataSetChanged();
    }
}
