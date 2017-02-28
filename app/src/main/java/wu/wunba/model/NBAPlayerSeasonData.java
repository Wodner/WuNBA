package wu.wunba.model;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 14:12
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerSeasonData {

    /**
     * code : 0
     * data : {"lastMatches":{"head":["日期","对手","分钟","得分","篮板","助攻","盖帽","抢断","失误","投篮","命中%","三分球","三分%","罚球","罚球%"],"rows":[["2017/02/27","鹈鹕","36","41","11","11","0","1","9","14/29","48.3","2/8","25","11/12","91.7"],["2017/02/25","湖人","35","17","18","17","0","3","6","4/18","22.2","1/5","20","8/9","88.9"],["2017/02/20","东部明星","19","41","5","7","0","1","2","16/26","61.5","7/13","53.8","2/2","100"],["2017/02/16","尼克斯","37","38","14","12","1","3","6","13/22","59.1","3/5","60","9/13","69.2"],["2017/02/14","奇才","24","17","4","4","0","2","2","5/19","26.3","0/4","0","7/7","100"]]},"stats":{"head":["年度","球队","赛季","场数","先发","分钟","命中%","三分%","场均篮板","场均助攻","场均盖帽","场均抢断","失误","犯规","场均得分"],"rows":[["16/17","雷霆","常规赛","59","59","34.7","42.1","33.4","10.6","10.3","0.4","1.6","5.6","2.4","31.0"],["16/17","雷霆","季前赛","6","6","22.0","42.3","41.2","2.7","6.5","0.0","1.3","2.3","2.0","16.2"]]}}
     * version : 12e29748e4839ba64482d0371b009b45
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
         * lastMatches : {"head":["日期","对手","分钟","得分","篮板","助攻","盖帽","抢断","失误","投篮","命中%","三分球","三分%","罚球","罚球%"],"rows":[["2017/02/27","鹈鹕","36","41","11","11","0","1","9","14/29","48.3","2/8","25","11/12","91.7"],["2017/02/25","湖人","35","17","18","17","0","3","6","4/18","22.2","1/5","20","8/9","88.9"],["2017/02/20","东部明星","19","41","5","7","0","1","2","16/26","61.5","7/13","53.8","2/2","100"],["2017/02/16","尼克斯","37","38","14","12","1","3","6","13/22","59.1","3/5","60","9/13","69.2"],["2017/02/14","奇才","24","17","4","4","0","2","2","5/19","26.3","0/4","0","7/7","100"]]}
         * stats : {"head":["年度","球队","赛季","场数","先发","分钟","命中%","三分%","场均篮板","场均助攻","场均盖帽","场均抢断","失误","犯规","场均得分"],"rows":[["16/17","雷霆","常规赛","59","59","34.7","42.1","33.4","10.6","10.3","0.4","1.6","5.6","2.4","31.0"],["16/17","雷霆","季前赛","6","6","22.0","42.3","41.2","2.7","6.5","0.0","1.3","2.3","2.0","16.2"]]}
         */

        public LastMatchesBean lastMatches;
        public StatsBean stats;

        public LastMatchesBean getLastMatches() {
            return lastMatches;
        }

        public void setLastMatches(LastMatchesBean lastMatches) {
            this.lastMatches = lastMatches;
        }

        public StatsBean getStats() {
            return stats;
        }

        public void setStats(StatsBean stats) {
            this.stats = stats;
        }

        public static class LastMatchesBean {
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

        public static class StatsBean {
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
}
