package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBATeam;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 09:39
 * 邮箱：wuwende@live.cn
 */

public interface NBATeamView extends BaseView{

    void showTeam(List<NBATeam> nbaTeamList);
}
