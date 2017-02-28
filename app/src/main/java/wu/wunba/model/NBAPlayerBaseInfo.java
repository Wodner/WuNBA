package wu.wunba.model;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 12:29
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerBaseInfo {


    /**
     * code : 0
     * data : {"cnName":"丹尼斯-施罗德","enName":"Dennis Schroder","teamName":"老鹰","teamLogo":"http://mat1.gtimg.com/sports/nba/logo/1602/1.png","jerseyNum":"17","position":"后卫","weight":"78.0kg","height":"185cm","logo":"http://nbachina.qq.com/media/img/players/head/260x190/203471.png","birthDate":"1993-09-15","draftYear":"2013","stats":{"points":"17.4","assists":"6.3","blocks":"0.2","steals":"0.8","rebounds":"2.9"}}
     * version : 5aca673724b20c57a3f4d6c3a62a7155
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
         * cnName : 丹尼斯-施罗德
         * enName : Dennis Schroder
         * teamName : 老鹰
         * teamLogo : http://mat1.gtimg.com/sports/nba/logo/1602/1.png
         * jerseyNum : 17
         * position : 后卫
         * weight : 78.0kg
         * height : 185cm
         * logo : http://nbachina.qq.com/media/img/players/head/260x190/203471.png
         * birthDate : 1993-09-15
         * draftYear : 2013
         * stats : {"points":"17.4","assists":"6.3","blocks":"0.2","steals":"0.8","rebounds":"2.9"}
         */

        private String cnName;
        private String enName;
        private String teamName;
        private String teamLogo;
        private String jerseyNum;
        private String position;
        private String weight;
        private String height;
        private String logo;
        private String birthDate;
        private String draftYear;
        private StatsBean stats;

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
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

        public String getJerseyNum() {
            return jerseyNum;
        }

        public void setJerseyNum(String jerseyNum) {
            this.jerseyNum = jerseyNum;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getDraftYear() {
            return draftYear;
        }

        public void setDraftYear(String draftYear) {
            this.draftYear = draftYear;
        }

        public StatsBean getStats() {
            return stats;
        }

        public void setStats(StatsBean stats) {
            this.stats = stats;
        }

        public static class StatsBean {
            /**
             * points : 17.4
             * assists : 6.3
             * blocks : 0.2
             * steals : 0.8
             * rebounds : 2.9
             */

            private String points;
            private String assists;
            private String blocks;
            private String steals;
            private String rebounds;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getAssists() {
                return assists;
            }

            public void setAssists(String assists) {
                this.assists = assists;
            }

            public String getBlocks() {
                return blocks;
            }

            public void setBlocks(String blocks) {
                this.blocks = blocks;
            }

            public String getSteals() {
                return steals;
            }

            public void setSteals(String steals) {
                this.steals = steals;
            }

            public String getRebounds() {
                return rebounds;
            }

            public void setRebounds(String rebounds) {
                this.rebounds = rebounds;
            }
        }
    }
}
