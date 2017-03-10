package wu.wunba.model;

import java.util.List;

/**
 * 功能： 比赛数据
 * 作者： Administrator
 * 日期： 2017/3/9 17:54
 * 邮箱： descriable
 */
public class NBAGameLiveDataInfo {

    private String matchPeriod ;//1：直播 2：比赛结束
    public TeamInfo teamInfo;
    public Score score;
    public MaxPlayer maxPlayer;
    public TeamStats teamStats;
    public PlayerStats playerStats;


    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }

    public String getMatchPeriod() {
        return matchPeriod;
    }

    public void setMatchPeriod(String matchPeriod) {
        this.matchPeriod = matchPeriod;
    }

    public TeamStats getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(TeamStats teamStats) {
        this.teamStats = teamStats;
    }

    public TeamInfo getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(TeamInfo teamInfo) {
        this.teamInfo = teamInfo;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public MaxPlayer getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(MaxPlayer maxPlayer) {
        this.maxPlayer = maxPlayer;
    }





    public class TeamInfo{

        /**
         * leftId : 17
         * leftName : 篮网
         * leftBadge : http://mat1.gtimg.com/sports/nba/logo/1602/17.png
         * leftGoal : 105
         * leftUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=17
         * rightId : 1
         * rightName : 老鹰
         * rightBadge : http://mat1.gtimg.com/sports/nba/logo/1602/1.png
         * rightGoal : 110
         * rightUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=1
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

    public class Score{

        /**
         * type : 12
         * text : 比分
         * goals : [{"head":["1st","2nd","3rd","4th","总分"],"rows":[["27","23","30","25","105"],["23","23","35","29","110"]]}]
         */

        private String type;
        private String text;
        private List<GoalsBean> goals;

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

        public List<GoalsBean> getGoals() {
            return goals;
        }

        public void setGoals(List<GoalsBean> goals) {
            this.goals = goals;
        }

        public class GoalsBean {
            private List<String> head;
            private List<List<String>> rows;

            public List<String> getHead() {
                return head;
            }

            public void setHead(List<String> head) {
                this.head = head;
            }

            public List<List<String>> getRows() {
                return rows;
            }

            public void setRows(List<List<String>> rows) {
                this.rows = rows;
            }
        }
    }

    public class MaxPlayer{

        /**
         * type : 13
         * text : 本场最佳
         * maxPlayers : [{"leftVal":"27","rightVal":"31","leftPlayer":{"playerId":"5535","name":"基尔帕特里克","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203930.png","position":"后卫","jerseyNum":"6","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5535"},"rightPlayer":{"playerId":"5187","name":"施罗德","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203471.png","position":"后卫","jerseyNum":"17","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5187"},"text":"得分"},{"leftVal":"8","rightVal":"5","leftPlayer":{"playerId":"4795","name":"林书豪","icon":"http://nbachina.qq.com/media/img/players/head/260x190/202391.png","position":"后卫","jerseyNum":"7","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4795"},"rightPlayer":{"playerId":"5187","name":"施罗德","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203471.png","position":"后卫","jerseyNum":"17","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5187"},"text":"助攻"},{"leftVal":"8","rightVal":"14","leftPlayer":{"playerId":"4754","name":"布克","icon":"http://nbachina.qq.com/media/img/players/head/260x190/202344.png","position":"前锋","jerseyNum":"35","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=4754"},"rightPlayer":{"playerId":"3818","name":"霍华德","icon":"http://nbachina.qq.com/media/img/players/head/260x190/2730.png","position":"中锋","jerseyNum":"8","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=3818"},"text":"篮板"}]
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
             * leftVal : 27
             * rightVal : 31
             * leftPlayer : {"playerId":"5535","name":"基尔帕特里克","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203930.png","position":"后卫","jerseyNum":"6","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5535"}
             * rightPlayer : {"playerId":"5187","name":"施罗德","icon":"http://nbachina.qq.com/media/img/players/head/260x190/203471.png","position":"后卫","jerseyNum":"17","detailUrl":"http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5187"}
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

            public  class LeftPlayerBean {
                /**
                 * playerId : 5535
                 * name : 基尔帕特里克
                 * icon : http://nbachina.qq.com/media/img/players/head/260x190/203930.png
                 * position : 后卫
                 * jerseyNum : 6
                 * detailUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5535
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

            public  class RightPlayerBean {
                /**
                 * playerId : 5187
                 * name : 施罗德
                 * icon : http://nbachina.qq.com/media/img/players/head/260x190/203471.png
                 * position : 后卫
                 * jerseyNum : 17
                 * detailUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=5187
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

    public class TeamStats{

        /**
         * type : 14
         * text : 球队统计
         * teamStats : [{"text":"篮板","leftVal":"40","rightVal":"47"},{"text":"助攻","leftVal":"28","rightVal":"19"},{"text":"抢断","leftVal":"7","rightVal":"12"},{"text":"盖帽","leftVal":"11","rightVal":"7"},{"text":"失误","leftVal":"22","rightVal":"15"},{"text":"罚球","leftVal":"20","rightVal":"24"},{"text":"三分","leftVal":"11","rightVal":"4"},{"text":"犯规","leftVal":"25","rightVal":"22"}]
         */

        private String type;
        private String text;
        private List<TeamStatsBean> teamStats;

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

        public List<TeamStatsBean> getTeamStats() {
            return teamStats;
        }

        public void setTeamStats(List<TeamStatsBean> teamStats) {
            this.teamStats = teamStats;
        }

        public class TeamStatsBean {
            /**
             * text : 篮板
             * leftVal : 40
             * rightVal : 47
             */

            private String text;
            private String leftVal;
            private String rightVal;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

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
        }
    }




    public class PlayerStats{

        public String text;
        public String type;
        public Left left;
        public Right right;

        public class Left{

            public List<String> head;

            public List<PlayerInfo> getPlayerInfoList() {
                return playerInfoList;
            }

            public void setPlayerInfoList(List<PlayerInfo> playerInfoList) {
                this.playerInfoList = playerInfoList;
            }

            public List<String> getHead() {
                return head;
            }

            public void setHead(List<String> head) {
                this.head = head;
            }

            public List<PlayerInfo> playerInfoList;


            public class PlayerInfo{

                /**
                 * playerId : 3704
                 * playerName : 詹姆斯
                 * playerIcon : http://nbachina.qq.com/media/img/players/head/260x190/2544.png
                 * playerJerseyNum : 23
                 * detailUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=3704
                 * row : ["38","29","13","10","12/22","0/6","5/8","2","0","3","1"]
                 */

                private String playerId;
                private String playerName;
                private String playerIcon;
                private String playerJerseyNum;
                private String detailUrl;
                private List<String> row;

                public String getPlayerId() {
                    return playerId;
                }

                public void setPlayerId(String playerId) {
                    this.playerId = playerId;
                }

                public String getPlayerName() {
                    return playerName;
                }

                public void setPlayerName(String playerName) {
                    this.playerName = playerName;
                }

                public String getPlayerIcon() {
                    return playerIcon;
                }

                public void setPlayerIcon(String playerIcon) {
                    this.playerIcon = playerIcon;
                }

                public String getPlayerJerseyNum() {
                    return playerJerseyNum;
                }

                public void setPlayerJerseyNum(String playerJerseyNum) {
                    this.playerJerseyNum = playerJerseyNum;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public List<String> getRow() {
                    return row;
                }

                public void setRow(List<String> row) {
                    this.row = row;
                }
            }



        }
        public class Right{

            public List<String> head;

            public List<PlayerInfo> getPlayerInfoList() {
                return playerInfoList;
            }

            public void setPlayerInfoList(List<PlayerInfo> playerInfoList) {
                this.playerInfoList = playerInfoList;
            }

            public List<String> getHead() {
                return head;
            }

            public void setHead(List<String> head) {
                this.head = head;
            }

            public List<PlayerInfo> playerInfoList;


            public class PlayerInfo{

                /**
                 * playerId : 3704
                 * playerName : 詹姆斯
                 * playerIcon : http://nbachina.qq.com/media/img/players/head/260x190/2544.png
                 * playerJerseyNum : 23
                 * detailUrl : http://sports.qq.com/kbsweb/kbsshare/player.htm?ref=nbaapp&pid=3704
                 * row : ["38","29","13","10","12/22","0/6","5/8","2","0","3","1"]
                 */

                private String playerId;
                private String playerName;
                private String playerIcon;
                private String playerJerseyNum;
                private String detailUrl;
                private List<String> row;

                public String getPlayerId() {
                    return playerId;
                }

                public void setPlayerId(String playerId) {
                    this.playerId = playerId;
                }

                public String getPlayerName() {
                    return playerName;
                }

                public void setPlayerName(String playerName) {
                    this.playerName = playerName;
                }

                public String getPlayerIcon() {
                    return playerIcon;
                }

                public void setPlayerIcon(String playerIcon) {
                    this.playerIcon = playerIcon;
                }

                public String getPlayerJerseyNum() {
                    return playerJerseyNum;
                }

                public void setPlayerJerseyNum(String playerJerseyNum) {
                    this.playerJerseyNum = playerJerseyNum;
                }

                public String getDetailUrl() {
                    return detailUrl;
                }

                public void setDetailUrl(String detailUrl) {
                    this.detailUrl = detailUrl;
                }

                public List<String> getRow() {
                    return row;
                }

                public void setRow(List<String> row) {
                    this.row = row;
                }
            }
        }
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Right getRight() {
            return right;
        }

        public void setRight(Right right) {
            this.right = right;
        }

        public Left getLeft() {
            return left;
        }

        public void setLeft(Left left) {
            this.left = left;
        }
    }





}
