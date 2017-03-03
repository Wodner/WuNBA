package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBAGameLvieStatisticsItem;

/**
 * 描述：
 * 作者：Wu on 2017/3/3 14:53
 * 邮箱：wuwende@live.cn
 */

public interface NBAGameLiveStatisticsView extends BaseView {

    void showLefeStatistics(List<String> leftSide,NBAGameLvieStatisticsItem leftStatisticsItems);
    void showRightStatistics(List<String> rightSide,NBAGameLvieStatisticsItem rightStatisticsItems);

}
