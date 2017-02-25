package wu.wunba.model;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/18 16:58
 * 邮箱：wuwende@live.cn
 */

public class ModelNBADataPoint {

    /**
     * code : 0
     * data : {"point":[{"serial":"1","playerId":"1962937421","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937421","playerName":"贾马尔-穆雷","playerEnName":"Jamal Murray","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1627750.png","jerseyNum":"27","teamId":"7","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=7","teamName":"掘金","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/7.png","value":"36"},{"serial":"2","playerId":"5550","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5550","playerName":"弗兰克-卡明斯基","playerEnName":"Frank Kaminsky","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626163.png","jerseyNum":"44","teamId":"30","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=30","teamName":"黄蜂","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/30.png","value":"33"},{"serial":"3","playerId":"1962937411","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937411","playerName":"巴迪-希尔德","playerEnName":"Buddy Hield","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1627741.png","jerseyNum":"24","teamId":"3","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=3","teamName":"鹈鹕","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/3.png","value":"28"},{"serial":"4","playerId":"5546","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5546","playerName":"卡尔-安东尼-唐斯","playerEnName":"Karl-Anthony Towns","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626157.png","jerseyNum":"32","teamId":"16","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=16","teamName":"森林狼","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/16.png","value":"24"},{"serial":"5","playerId":"5544","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5544","playerName":"克里斯塔普斯-波尔津吉斯","playerEnName":"Kristaps Porzingis","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/204001.png","jerseyNum":"6","teamId":"18","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18","teamName":"尼克斯","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/18.png","value":"24"},{"serial":"6","playerId":"5620","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5620","playerName":"乔纳森-西蒙斯","playerEnName":"Jonathon Simmons","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/203613.png","jerseyNum":"17","teamId":"24","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=24","teamName":"马刺","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/24.png","value":"19"},{"serial":"7","playerId":"5547","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5547","playerName":"德文-布克","playerEnName":"Devin Booker","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626164.png","jerseyNum":"1","teamId":"21","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=21","teamName":"太阳","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/21.png","value":"17"},{"serial":"8","playerId":"5323","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5323","playerName":"达里奥-萨里奇","playerEnName":"Dario Saric","playerIcon":"http://mat1.gtimg.com/sports/NBAImages/PlayerImages/260x190203967.png","jerseyNum":"9","teamId":"20","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=20","teamName":"76人","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/20.png","value":"17"},{"serial":"9","playerId":"5543","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5543","playerName":"丹吉洛-拉塞尔","playerEnName":"D'Angelo Russell","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626156.png","jerseyNum":"1","teamId":"13","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=13","teamName":"湖人","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/13.png","value":"12"},{"serial":"10","playerId":"5553","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5553","playerName":"迈尔斯-特纳","playerEnName":"Myles Turner","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626167.png","jerseyNum":"33","teamId":"11","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=11","teamName":"步行者","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/11.png","value":"12"},{"serial":"11","playerId":"5352","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5352","playerName":"尼古拉-约基奇","playerEnName":"Nikola Jokic","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/203999.png","jerseyNum":"15","teamId":"7","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=7","teamName":"掘金","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/7.png","value":"12"},{"serial":"12","playerId":"5316","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5316","playerName":"丹特-埃克萨姆","playerEnName":"Dante Exum","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/203957.png","jerseyNum":"11","teamId":"26","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=26","teamName":"爵士","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/26.png","value":"11"},{"serial":"13","playerId":"5545","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5545","playerName":"贾里尔-奥卡福","playerEnName":"Jahlil Okafor","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626143.png","jerseyNum":"8","teamId":"20","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=20","teamName":"76人","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/20.png","value":"10"},{"serial":"14","playerId":"1962937414","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937414","playerName":"多曼塔斯-萨博尼斯","playerEnName":"Domantas Sabonis","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1627734.png","jerseyNum":"3","teamId":"25","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=25","teamName":"雷霆","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/25.png","value":"10"},{"serial":"15","playerId":"1962937417","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937417","playerName":"马基斯-克里斯","playerEnName":"Marquese Chriss","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1627737.png","jerseyNum":"0","teamId":"21","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=21","teamName":"太阳","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/21.png","value":"7"},{"serial":"16","playerId":"5551","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5551","playerName":"特雷-莱尔斯","playerEnName":"Trey Lyles","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626168.png","jerseyNum":"41","teamId":"26","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=26","teamName":"爵士","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/26.png","value":"7"},{"serial":"17","playerId":"1962937412","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937412","playerName":"布兰登-英格拉姆","playerEnName":"Brandon Ingram","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1627742.png","jerseyNum":"14","teamId":"13","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=13","teamName":"湖人","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/13.png","value":"4"},{"serial":"18","playerId":"1962937442","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937442","playerName":"马尔科姆·布罗格登","playerEnName":"Malcolm Brogdon","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1627763.png","jerseyNum":"13","teamId":"15","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=15","teamName":"雄鹿","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/15.png","value":"3"},{"serial":"19","playerId":"5202","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5202","playerName":"阿莱克斯-阿布里内斯","playerEnName":"Alex Abrines","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/203518.png","jerseyNum":"8","teamId":"25","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=25","teamName":"雷霆","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/25.png","value":"3"},{"serial":"20","playerId":"5556","playerUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5556","playerName":"伊曼纽尔-穆迪埃","playerEnName":"Emmanuel Mudiay","playerIcon":"http://nbachina.qq.com/media/img/players/head/260x190/1626144.png","jerseyNum":"0","teamId":"7","teamUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=7","teamName":"掘金","teamIcon":"http://mat1.gtimg.com/sports/nba/logo/1602/7.png","value":"0"}]}
     * version : 49b56fa8ca5fcefd580cd0399c3ee37f
     */

    private int code;
    private DataBean data;
    private String version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        private List<PointBean> point;

        public List<PointBean> getPoint() {
            return point;
        }

        public void setPoint(List<PointBean> point) {
            this.point = point;
        }

        public static class PointBean {
            /**
             * serial : 1
             * playerId : 1962937421
             * playerUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=1962937421
             * playerName : 贾马尔-穆雷
             * playerEnName : Jamal Murray
             * playerIcon : http://nbachina.qq.com/media/img/players/head/260x190/1627750.png
             * jerseyNum : 27
             * teamId : 7
             * teamUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=7
             * teamName : 掘金
             * teamIcon : http://mat1.gtimg.com/sports/nba/logo/1602/7.png
             * value : 36
             */

            private String serial;
            private String playerId;
            private String playerUrl;

            private String playerName;
            private String playerEnName;
            private String playerIcon;

            private String jerseyNum;
            private String teamId;
            private String teamUrl;

            private String teamName;
            private String teamIcon;
            private String value;

            public String getSerial() {
                return serial;
            }

            public void setSerial(String serial) {
                this.serial = serial;
            }

            public String getPlayerId() {
                return playerId;
            }

            public void setPlayerId(String playerId) {
                this.playerId = playerId;
            }

            public String getPlayerUrl() {
                return playerUrl;
            }

            public void setPlayerUrl(String playerUrl) {
                this.playerUrl = playerUrl;
            }

            public String getPlayerName() {
                return playerName;
            }

            public void setPlayerName(String playerName) {
                this.playerName = playerName;
            }

            public String getPlayerEnName() {
                return playerEnName;
            }

            public void setPlayerEnName(String playerEnName) {
                this.playerEnName = playerEnName;
            }

            public String getPlayerIcon() {
                return playerIcon;
            }

            public void setPlayerIcon(String playerIcon) {
                this.playerIcon = playerIcon;
            }

            public String getJerseyNum() {
                return jerseyNum;
            }

            public void setJerseyNum(String jerseyNum) {
                this.jerseyNum = jerseyNum;
            }

            public String getTeamId() {
                return teamId;
            }

            public void setTeamId(String teamId) {
                this.teamId = teamId;
            }

            public String getTeamUrl() {
                return teamUrl;
            }

            public void setTeamUrl(String teamUrl) {
                this.teamUrl = teamUrl;
            }

            public String getTeamName() {
                return teamName;
            }

            public void setTeamName(String teamName) {
                this.teamName = teamName;
            }

            public String getTeamIcon() {
                return teamIcon;
            }

            public void setTeamIcon(String teamIcon) {
                this.teamIcon = teamIcon;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
