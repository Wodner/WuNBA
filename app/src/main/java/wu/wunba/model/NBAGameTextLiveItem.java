package wu.wunba.model;

/**
 * 描述：
 * 作者：Wu on 2017/3/2 12:47
 * 邮箱：wuwende@live.cn
 */

public class NBAGameTextLiveItem {


    /**
     * ctype : 2
     * content :  佩顿 篮板球 (进攻篮板:0 防守篮板:2)
     * type : 1
     * quarter : 第2节
     * time : 02:41
     * teamId : 19
     * plus :
     * player4NBAApp : {"id":"5321","pic":"http://nbachina.qq.com/media/img/players/head/260x190/203901.png"}
     * sendTime : 2017-03-02 09:04:09
     * topIndex : 0
     * version : 538811881
     * leftGoal : 52
     * rightGoal : 47
     * teamName : 魔术
     */

    private String ctype;
    private String content;
    private String type;
    private String quarter;
    private String time;
    private String teamId;
    private String plus;
    private Player4NBAAppBean player4NBAApp;
    private String sendTime;
    private String topIndex;
    private String version;
    private String leftGoal;
    private String rightGoal;
    private String teamName;

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public Player4NBAAppBean getPlayer4NBAApp() {
        return player4NBAApp;
    }

    public void setPlayer4NBAApp(Player4NBAAppBean player4NBAApp) {
        this.player4NBAApp = player4NBAApp;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTopIndex() {
        return topIndex;
    }

    public void setTopIndex(String topIndex) {
        this.topIndex = topIndex;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLeftGoal() {
        return leftGoal;
    }

    public void setLeftGoal(String leftGoal) {
        this.leftGoal = leftGoal;
    }

    public String getRightGoal() {
        return rightGoal;
    }

    public void setRightGoal(String rightGoal) {
        this.rightGoal = rightGoal;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public static class Player4NBAAppBean {
        /**
         * id : 5321
         * pic : http://nbachina.qq.com/media/img/players/head/260x190/203901.png
         */

        private String id;
        private String pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
