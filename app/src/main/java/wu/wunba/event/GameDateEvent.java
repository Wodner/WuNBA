package wu.wunba.event;

/**
 * 描述：选取比赛日期返回更新日期
 * 作者：Wu on 2017/2/19 15:46
 * 邮箱：wuwende@live.cn
 */

public class GameDateEvent {

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public GameDateEvent(String date) {
        this.date = date;
    }
}
