package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBAGameTextLiveItem;

/**
 * 描述：
 * 作者：Wu on 2017/3/2 11:13
 * 邮箱：wuwende@live.cn
 */

public interface NBAGameTextLiveView extends BaseView{
    void showGameTextLive(List<NBAGameTextLiveItem> nbaGameTextLiveItemList);
}
