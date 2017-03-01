package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBAPlayer;
import wu.wunba.model.NBATeamBaseInfo;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 12:57
 * 邮箱：wuwende@live.cn
 */

public interface NBATeamBaseInfoView extends BaseView{

    void showTeamBaseInfo(NBATeamBaseInfo baseInfo);
    void showTeamPlayers(List<NBAPlayer> playerList);
}
