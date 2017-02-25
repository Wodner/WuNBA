package wu.wunba.model;

/**
 * 描述：
 * 作者：Wu on 2017/2/24 20:41
 * 邮箱：wuwende@live.cn
 */

public class NBATeamRank {



    private String title;
    private String teamId;
    private String name;
    private String badge;
    private String serial;
    private String color;
    private String detailUrl;
    private String win;
    private String lose;
    private String winProbility;
    private String winBind;


    private String rankNum;

    @Override
    public String toString() {
        return "NBATeamRank{" +
                "title='" + title + '\'' +
                ", teamId='" + teamId + '\'' +
                ", name='" + name + '\'' +
                ", badge='" + badge + '\'' +
                ", serial='" + serial + '\'' +
                ", color='" + color + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", win='" + win + '\'' +
                ", lose='" + lose + '\'' +
                ", winProbility='" + winProbility + '\'' +
                ", winBind='" + winBind + '\'' +
                '}';
    }

    public String getRankNum() {
        return rankNum;
    }

    public void setRankNum(String rankNum) {
        this.rankNum = rankNum;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getWinProbility() {
        return winProbility;
    }

    public void setWinProbility(String winProbility) {
        this.winProbility = winProbility;
    }

    public String getWinBind() {
        return winBind;
    }

    public void setWinBind(String winBind) {
        this.winBind = winBind;
    }

}
