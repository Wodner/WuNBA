package wu.wunba.model;

import java.util.List;

/**
 * 描述：比赛统计
 * 作者：Wu on 2017/3/3 15:12
 * 邮箱：wuwende@live.cn
 */

public class NBAGameLvieStatisticsItem {


    private String subText;
    private String playerId;
    private String started;
    private String detailUrl;
    private String position;
    private List<String> head;
    private List<List<String>> row;


    public List<List<String>> getRow() {
        return row;
    }

    public void setRow(List<List<String>> row) {
        this.row = row;
    }







    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getHead() {
        return head;
    }

    public void setHead(List<String> head) {
        this.head = head;
    }




}
