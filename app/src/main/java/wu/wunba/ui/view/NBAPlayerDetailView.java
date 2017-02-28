package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBAPlayerBaseInfo;
import wu.wunba.model.NBAPlayerData;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 11:39
 * 邮箱：wuwende@live.cn
 */

public interface NBAPlayerDetailView extends BaseView {

    void showPlayerBaseInfo( NBAPlayerBaseInfo baseInfo);
    void showPlayerData(List<NBAPlayerData> playerDataList);
}
