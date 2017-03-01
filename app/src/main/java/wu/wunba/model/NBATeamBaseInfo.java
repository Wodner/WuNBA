package wu.wunba.model;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 12:58
 * 邮箱：wuwende@live.cn
 */

public class NBATeamBaseInfo {


    /**
     * code : 0
     * data : {"baseInfo":{"teamId":"9","teamName":"勇士","teamLogo":"http://mat1.gtimg.com/sports/nba/logo/1602/901.png","coach":"史蒂夫-科尔","introduction":"1946年，球队诞生于费城，为表达对美国独立战争中牺牲勇士的敬意，故队名为\u201c费城武士队\u201d，1962年移师旧金山后改为\u201c金州勇士队\u201d。2015年6月17日，勇士总比分4：2战胜骑士，夺得球队在1974\u201475赛季以来的第一个冠军。在NBA历史上，球队曾三次夺过总冠军，NBA的第一次总冠军得主就是勇士队。","shopUrl":"http://www.nbastore.cn/warriors"},"rankInfo":{"wins":"50","losses":"10","conferenceRank":"西部联盟第1名","streak":"1负"},"stats":{"point":"118.2","steal":"9.7","rebound":"44.8","block":"6.7","assist":"31.0","oppPoint":"105.4"},"statsRank":{"point":"1","steal":"1","rebound":"6","block":"1","assist":"1","oppPoint":"14"},"isUserAttend":"0","canUserAttend":"0"}
     * version : 05305063972f136c413fdc289eb75652
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
        /**
         * baseInfo : {"teamId":"9","teamName":"勇士","teamLogo":"http://mat1.gtimg.com/sports/nba/logo/1602/901.png","coach":"史蒂夫-科尔","introduction":"1946年，球队诞生于费城，为表达对美国独立战争中牺牲勇士的敬意，故队名为\u201c费城武士队\u201d，1962年移师旧金山后改为\u201c金州勇士队\u201d。2015年6月17日，勇士总比分4：2战胜骑士，夺得球队在1974\u201475赛季以来的第一个冠军。在NBA历史上，球队曾三次夺过总冠军，NBA的第一次总冠军得主就是勇士队。","shopUrl":"http://www.nbastore.cn/warriors"}
         * rankInfo : {"wins":"50","losses":"10","conferenceRank":"西部联盟第1名","streak":"1负"}
         * stats : {"point":"118.2","steal":"9.7","rebound":"44.8","block":"6.7","assist":"31.0","oppPoint":"105.4"}
         * statsRank : {"point":"1","steal":"1","rebound":"6","block":"1","assist":"1","oppPoint":"14"}
         * isUserAttend : 0
         * canUserAttend : 0
         */

        private BaseInfoBean baseInfo;
        private RankInfoBean rankInfo;
        private StatsBean stats;
        private StatsRankBean statsRank;
        private String isUserAttend;
        private String canUserAttend;

        public BaseInfoBean getBaseInfo() {
            return baseInfo;
        }

        public void setBaseInfo(BaseInfoBean baseInfo) {
            this.baseInfo = baseInfo;
        }

        public RankInfoBean getRankInfo() {
            return rankInfo;
        }

        public void setRankInfo(RankInfoBean rankInfo) {
            this.rankInfo = rankInfo;
        }

        public StatsBean getStats() {
            return stats;
        }

        public void setStats(StatsBean stats) {
            this.stats = stats;
        }

        public StatsRankBean getStatsRank() {
            return statsRank;
        }

        public void setStatsRank(StatsRankBean statsRank) {
            this.statsRank = statsRank;
        }

        public String getIsUserAttend() {
            return isUserAttend;
        }

        public void setIsUserAttend(String isUserAttend) {
            this.isUserAttend = isUserAttend;
        }

        public String getCanUserAttend() {
            return canUserAttend;
        }

        public void setCanUserAttend(String canUserAttend) {
            this.canUserAttend = canUserAttend;
        }

        public static class BaseInfoBean {
            /**
             * teamId : 9
             * teamName : 勇士
             * teamLogo : http://mat1.gtimg.com/sports/nba/logo/1602/901.png
             * coach : 史蒂夫-科尔
             * introduction : 1946年，球队诞生于费城，为表达对美国独立战争中牺牲勇士的敬意，故队名为“费城武士队”，1962年移师旧金山后改为“金州勇士队”。2015年6月17日，勇士总比分4：2战胜骑士，夺得球队在1974—75赛季以来的第一个冠军。在NBA历史上，球队曾三次夺过总冠军，NBA的第一次总冠军得主就是勇士队。
             * shopUrl : http://www.nbastore.cn/warriors
             */

            private String teamId;
            private String teamName;
            private String teamLogo;
            private String coach;
            private String introduction;
            private String shopUrl;

            public String getTeamId() {
                return teamId;
            }

            public void setTeamId(String teamId) {
                this.teamId = teamId;
            }

            public String getTeamName() {
                return teamName;
            }

            public void setTeamName(String teamName) {
                this.teamName = teamName;
            }

            public String getTeamLogo() {
                return teamLogo;
            }

            public void setTeamLogo(String teamLogo) {
                this.teamLogo = teamLogo;
            }

            public String getCoach() {
                return coach;
            }

            public void setCoach(String coach) {
                this.coach = coach;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getShopUrl() {
                return shopUrl;
            }

            public void setShopUrl(String shopUrl) {
                this.shopUrl = shopUrl;
            }
        }

        public static class RankInfoBean {
            /**
             * wins : 50
             * losses : 10
             * conferenceRank : 西部联盟第1名
             * streak : 1负
             */

            private String wins;
            private String losses;
            private String conferenceRank;
            private String streak;

            public String getWins() {
                return wins;
            }

            public void setWins(String wins) {
                this.wins = wins;
            }

            public String getLosses() {
                return losses;
            }

            public void setLosses(String losses) {
                this.losses = losses;
            }

            public String getConferenceRank() {
                return conferenceRank;
            }

            public void setConferenceRank(String conferenceRank) {
                this.conferenceRank = conferenceRank;
            }

            public String getStreak() {
                return streak;
            }

            public void setStreak(String streak) {
                this.streak = streak;
            }
        }

        public static class StatsBean {
            /**
             * point : 118.2
             * steal : 9.7
             * rebound : 44.8
             * block : 6.7
             * assist : 31.0
             * oppPoint : 105.4
             */

            private String point;
            private String steal;
            private String rebound;
            private String block;
            private String assist;
            private String oppPoint;

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getSteal() {
                return steal;
            }

            public void setSteal(String steal) {
                this.steal = steal;
            }

            public String getRebound() {
                return rebound;
            }

            public void setRebound(String rebound) {
                this.rebound = rebound;
            }

            public String getBlock() {
                return block;
            }

            public void setBlock(String block) {
                this.block = block;
            }

            public String getAssist() {
                return assist;
            }

            public void setAssist(String assist) {
                this.assist = assist;
            }

            public String getOppPoint() {
                return oppPoint;
            }

            public void setOppPoint(String oppPoint) {
                this.oppPoint = oppPoint;
            }
        }

        public static class StatsRankBean {
            /**
             * point : 1
             * steal : 1
             * rebound : 6
             * block : 1
             * assist : 1
             * oppPoint : 14
             */

            private String point;
            private String steal;
            private String rebound;
            private String block;
            private String assist;
            private String oppPoint;

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getSteal() {
                return steal;
            }

            public void setSteal(String steal) {
                this.steal = steal;
            }

            public String getRebound() {
                return rebound;
            }

            public void setRebound(String rebound) {
                this.rebound = rebound;
            }

            public String getBlock() {
                return block;
            }

            public void setBlock(String block) {
                this.block = block;
            }

            public String getAssist() {
                return assist;
            }

            public void setAssist(String assist) {
                this.assist = assist;
            }

            public String getOppPoint() {
                return oppPoint;
            }

            public void setOppPoint(String oppPoint) {
                this.oppPoint = oppPoint;
            }
        }
    }
}
