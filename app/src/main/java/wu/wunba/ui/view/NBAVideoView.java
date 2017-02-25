package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBAVideo;

/**
 * 描述：
 * 作者：Wu on 2017/2/21 18:22
 * 邮箱：wuwende@live.cn
 */

public interface NBAVideoView  extends BaseView{

    void showMatchVideo(List<NBAVideo> nbaVideolist);
//    void showMatchVideoError(String string);

}
