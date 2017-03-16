package wu.wunba.model;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/3/10 17:42
 * 邮箱：wuwende@live.cn
 */

public class NBAGamePreviewInfo {

    public TeamInfo teamInfo;
    public MaxPlager maxPlager;
    public VsHistory vsHistory;

    public VsHistory getVsHistory() {
        return vsHistory;
    }

    public void setVsHistory(VsHistory vsHistory) {
        this.vsHistory = vsHistory;
    }

    public MaxPlager getMaxPlager() {
        return maxPlager;
    }

    public void setMaxPlager(MaxPlager maxPlager) {
        this.maxPlager = maxPlager;
    }

    public TeamInfo getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(TeamInfo teamInfo) {
        this.teamInfo = teamInfo;
    }


    public class TeamInfo{

        /**
         * leftId : 18
         * leftName : 尼克斯
         * leftBadge : http://mat1.gtimg.com/sports/nba/logo/1602/18.png
         * leftGoal : 101
         * leftUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18
         * rightId : 19
         * rightName : 魔术
         * rightBadge : http://mat1.gtimg.com/sports/nba/logo/1602/19.png
         * rightGoal : 90
         * rightUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19
         * quarterDesc : 第4节 00:00
         */

        private String leftId;
        private String leftName;
        private String leftBadge;
        private String leftGoal;
        private String leftUrl;
        private String rightId;
        private String rightName;
        private String rightBadge;
        private String rightGoal;
        private String rightUrl;
        private String quarterDesc;

        public String getLeftId() {
            return leftId;
        }

        public void setLeftId(String leftId) {
            this.leftId = leftId;
        }

        public String getLeftName() {
            return leftName;
        }

        public void setLeftName(String leftName) {
            this.leftName = leftName;
        }

        public String getLeftBadge() {
            return leftBadge;
        }

        public void setLeftBadge(String leftBadge) {
            this.leftBadge = leftBadge;
        }

        public String getLeftGoal() {
            return leftGoal;
        }

        public void setLeftGoal(String leftGoal) {
            this.leftGoal = leftGoal;
        }

        public String getLeftUrl() {
            return leftUrl;
        }

        public void setLeftUrl(String leftUrl) {
            this.leftUrl = leftUrl;
        }

        public String getRightId() {
            return rightId;
        }

        public void setRightId(String rightId) {
            this.rightId = rightId;
        }

        public String getRightName() {
            return rightName;
        }

        public void setRightName(String rightName) {
            this.rightName = rightName;
        }

        public String getRightBadge() {
            return rightBadge;
        }

        public void setRightBadge(String rightBadge) {
            this.rightBadge = rightBadge;
        }

        public String getRightGoal() {
            return rightGoal;
        }

        public void setRightGoal(String rightGoal) {
            this.rightGoal = rightGoal;
        }

        public String getRightUrl() {
            return rightUrl;
        }

        public void setRightUrl(String rightUrl) {
            this.rightUrl = rightUrl;
        }

        public String getQuarterDesc() {
            return quarterDesc;
        }

        public void setQuarterDesc(String quarterDesc) {
            this.quarterDesc = quarterDesc;
        }
    }


    public class MaxPlager{

        /**
         * type : 13
         * text : 球队数据王
         * maxPlayers : [{"leftVal":"23.1","rightVal":"16.9","leftPlayer":{"playerId":"3706","name":"安东尼","icon":"http://nbachina.qq.com/media/img/players/head/260x190/2546.png","position":"前锋","jerseyNum":"7","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=3706"},"rightPlayer":{"playerId":"5054","name":"福尼耶","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203095.png","position":"后卫","jerseyNum":"10","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5054"},"text":"得分"},{"leftVal":"8.8","rightVal":"10.1","leftPlayer":{"playerId":"4287","name":"诺阿","icon":"http://nbachina.qq.com/media/img/players/head/260x190/201149.png","position":"中锋","jerseyNum":"13","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4287"},"rightPlayer":{"playerId":"4897","name":"武切维奇","icon":"http://nbachina.qq.com/media/img/players/head/260x190/202696.png","position":"中锋","jerseyNum":"9","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4897"},"text":"篮板"},{"leftVal":"4.5","rightVal":"5.8","leftPlayer":{"playerId":"4387","name":"罗斯","icon":"http://nbachina.qq.com/media/img/players/head/260x190/201565.png","position":"后卫","jerseyNum":"25","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4387"},"rightPlayer":{"playerId":"5321","name":"佩顿","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203901.png","position":"后卫","jerseyNum":"4","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5321"},"text":"助攻"},{"leftVal":"1.9","rightVal":"1.2","leftPlayer":{"playerId":"5544","name":"波尔津吉斯","icon":"http://nbachina.qq.com/media/img/players/head/260x190/204001.png","position":"前锋","jerseyNum":"6","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5544"},"rightPlayer":{"playerId":"4888","name":"比永博","icon":"http://nbachina.qq.com/media/img/players/head/260x190/202687.png","position":"中锋-前锋","jerseyNum":"11","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4888"},"text":"盖帽"},{"leftVal":"1.0","rightVal":"1.2","leftPlayer":{"playerId":"4484","name":"考特尼-李","icon":"http://nbachina.qq.com/media/img/players/head/260x190/201584.png","position":"后卫","jerseyNum":"5","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4484"},"rightPlayer":{"playerId":"4646","name":"米克斯","icon":"http://nbachina.qq.com/media/img/players/head/260x190/201975.png","position":"后卫","jerseyNum":"20","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4646"},"text":"抢断"}]
         */

        private String type;
        private String text;
        private List<MaxPlayersBean> maxPlayers;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<MaxPlayersBean> getMaxPlayers() {
            return maxPlayers;
        }

        public void setMaxPlayers(List<MaxPlayersBean> maxPlayers) {
            this.maxPlayers = maxPlayers;
        }

        public class MaxPlayersBean {
            /**
             * leftVal : 23.1
             * rightVal : 16.9
             * leftPlayer : {"playerId":"3706","name":"安东尼","icon":"http://nbachina.qq.com/media/img/players/head/260x190/2546.png","position":"前锋","jerseyNum":"7","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=3706"}
             * rightPlayer : {"playerId":"5054","name":"福尼耶","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203095.png","position":"后卫","jerseyNum":"10","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5054"}
             * text : 得分
             */

            private String leftVal;
            private String rightVal;
            private LeftPlayerBean leftPlayer;
            private RightPlayerBean rightPlayer;
            private String text;

            public String getLeftVal() {
                return leftVal;
            }

            public void setLeftVal(String leftVal) {
                this.leftVal = leftVal;
            }

            public String getRightVal() {
                return rightVal;
            }

            public void setRightVal(String rightVal) {
                this.rightVal = rightVal;
            }

            public LeftPlayerBean getLeftPlayer() {
                return leftPlayer;
            }

            public void setLeftPlayer(LeftPlayerBean leftPlayer) {
                this.leftPlayer = leftPlayer;
            }

            public RightPlayerBean getRightPlayer() {
                return rightPlayer;
            }

            public void setRightPlayer(RightPlayerBean rightPlayer) {
                this.rightPlayer = rightPlayer;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public class LeftPlayerBean {
                /**
                 * playerId : 3706
                 * name : 安东尼
                 * icon : http://nbachina.qq.com/media/img/players/head/260x190/2546.png
                 * position : 前锋
                 * jerseyNum : 7
                 * detailUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=3706
                 */

                private String playerId;
                private String name;
                private String icon;
                private String position;
                private String jerseyNum;
                private String detailUrl;

                public String getPlayerId() {
                    return playerId;
                }

                public void setPlayerId(String playerId) {
                    this.playerId = playerId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
                }

                public String getJerseyNum() {
                    return jerseyNum;
                }

                public void setJerseyNum(String jerseyNum) {
                    this.jerseyNum = jerseyNum;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }
            }

            public class RightPlayerBean {
                /**
                 * playerId : 5054
                 * name : 福尼耶
                 * icon : http://nbachina.qq.com/media/img/players/head/260x190/203095.png
                 * position : 后卫
                 * jerseyNum : 10
                 * detailUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5054
                 */

                private String playerId;
                private String name;
                private String icon;
                private String position;
                private String jerseyNum;
                private String detailUrl;

                public String getPlayerId() {
                    return playerId;
                }

                public void setPlayerId(String playerId) {
                    this.playerId = playerId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
                }

                public String getJerseyNum() {
                    return jerseyNum;
                }

                public void setJerseyNum(String jerseyNum) {
                    this.jerseyNum = jerseyNum;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }
            }
        }
    }


    public class VsHistory{

        /**
         * type : 1
         * text : 历史对阵
         * vs : [{"matchId":"1469525","startTime":"2017年03月07日","leftName":"尼克斯","leftGoal":"113","leftBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/18.png","leftUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18","rightName":"魔术","rightGoal":"105","rightBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/19.png","rightUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19","matchDesc":"NBA常规赛"},{"matchId":"1469485","startTime":"2017年03月02日","leftName":"尼克斯","leftGoal":"101","leftBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/18.png","leftUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18","rightName":"魔术","rightGoal":"90","rightBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/19.png","rightUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19","matchDesc":"NBA常规赛"},{"matchId":"1468892","startTime":"2017年01月03日","leftName":"魔术","leftGoal":"115","leftBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/19.png","leftUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19","rightName":"尼克斯","rightGoal":"103","rightBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/18.png","rightUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18","matchDesc":"NBA常规赛"},{"matchId":"1469615","startTime":"2016年12月23日","leftName":"魔术","leftGoal":"95","leftBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/19.png","leftUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19","rightName":"尼克斯","rightGoal":"106","rightBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/18.png","rightUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18","matchDesc":"NBA常规赛"},{"matchId":"1467455","startTime":"2016年02月27日","leftName":"魔术","leftGoal":"95","leftBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/19.png","leftUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19","rightName":"尼克斯","rightGoal":"108","rightBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/18.png","rightUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18","matchDesc":"NBA常规赛"}]
         */

        private String type;
        private String text;
        private List<VsBean> vs;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<VsBean> getVs() {
            return vs;
        }

        public void setVs(List<VsBean> vs) {
            this.vs = vs;
        }

        public class VsBean {
            /**
             * matchId : 1469525
             * startTime : 2017年03月07日
             * leftName : 尼克斯
             * leftGoal : 113
             * leftBadge : http://mat1.gtimg.com/sports/nba/logo/1602/18.png
             * leftUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=18
             * rightName : 魔术
             * rightGoal : 105
             * rightBadge : http://mat1.gtimg.com/sports/nba/logo/1602/19.png
             * rightUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=19
             * matchDesc : NBA常规赛
             */

            private String matchId;
            private String startTime;
            private String leftName;
            private String leftGoal;
            private String leftBadge;
            private String leftUrl;
            private String rightName;
            private String rightGoal;
            private String rightBadge;
            private String rightUrl;
            private String matchDesc;

            public String getMatchId() {
                return matchId;
            }

            public void setMatchId(String matchId) {
                this.matchId = matchId;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getLeftName() {
                return leftName;
            }

            public void setLeftName(String leftName) {
                this.leftName = leftName;
            }

            public String getLeftGoal() {
                return leftGoal;
            }

            public void setLeftGoal(String leftGoal) {
                this.leftGoal = leftGoal;
            }

            public String getLeftBadge() {
                return leftBadge;
            }

            public void setLeftBadge(String leftBadge) {
                this.leftBadge = leftBadge;
            }

            public String getLeftUrl() {
                return leftUrl;
            }

            public void setLeftUrl(String leftUrl) {
                this.leftUrl = leftUrl;
            }

            public String getRightName() {
                return rightName;
            }

            public void setRightName(String rightName) {
                this.rightName = rightName;
            }

            public String getRightGoal() {
                return rightGoal;
            }

            public void setRightGoal(String rightGoal) {
                this.rightGoal = rightGoal;
            }

            public String getRightBadge() {
                return rightBadge;
            }

            public void setRightBadge(String rightBadge) {
                this.rightBadge = rightBadge;
            }

            public String getRightUrl() {
                return rightUrl;
            }

            public void setRightUrl(String rightUrl) {
                this.rightUrl = rightUrl;
            }

            public String getMatchDesc() {
                return matchDesc;
            }

            public void setMatchDesc(String matchDesc) {
                this.matchDesc = matchDesc;
            }
        }
    }


}
