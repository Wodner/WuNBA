package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBATeamRank;

/**
 * 描述：
 * 作者：Wu on 2017/2/24 20:44
 * 邮箱：wuwende@live.cn
 */

public interface NBATeamRankView extends BaseView {


    void showRanking(List<NBATeamRank> nbaTeamRankList);

}
