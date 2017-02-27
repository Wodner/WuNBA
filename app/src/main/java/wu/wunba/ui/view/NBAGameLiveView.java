package wu.wunba.ui.view;

import wu.wunba.model.NBAMatch;

/**
 * 描述：
 * 作者：Wu on 2017/2/27 10:18
 * 邮箱：wuwende@live.cn
 */

public interface NBAGameLiveView extends BaseView{


    void showNBAGameLive(NBAMatch nbaMatch);
    void showNBAGameIsLive(boolean isLive);
    void noNBAGame(boolean noGame);

}
