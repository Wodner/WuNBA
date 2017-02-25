package wu.wunba.event;

/**
 * 描述：首页比赛比分数据更新事件
 * 作者：Wu on 2017/2/20 10:28
 * 邮箱：wuwende@live.cn
 */

public class GameLiveDataEvent {

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result;

    public GameLiveDataEvent(String result) {
        this.result = result;
    }
}
