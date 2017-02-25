package wu.wunba.ui.view;

import java.util.List;

import wu.wunba.model.NBANews;

/**
 * 描述：
 * 作者：Wu on 2017/2/22 21:36
 * 邮箱：wuwende@live.cn
 */

public interface NBANewsView extends BaseView {

    void showNews(List<NBANews> nbaNewsList);
//    void onNewsError(String err);


}
