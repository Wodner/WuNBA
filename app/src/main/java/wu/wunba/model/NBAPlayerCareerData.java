package wu.wunba.model;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 14:13
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerCareerData {


    /**
     * code : 0
     * data : {"reg":{"head":["年度","球队","赛季","场数","先发","分钟","命中%","三分%","场均篮板","场均助攻","场均盖帽","场均抢断","失误","犯规","场均得分"],"rows":[["16/17","雷霆","常规赛","59","59","34.7","42.1","33.4","10.6","10.3","0.4","1.6","5.6","2.4","31.0"],["15/16","雷霆","常规赛","80","80","34.4","45.4","29.6","7.8","10.4","0.3","2.0","4.3","2.5","23.5"],["14/15","雷霆","常规赛","67","67","34.4","42.6","29.9","7.3","8.6","0.2","2.1","4.4","2.7","28.1"]]},"playoff":{"head":["年度","球队","赛季","场数","先发","分钟","命中%","三分%","场均篮板","场均助攻","场均盖帽","场均抢断","失误","犯规","场均得分"],"rows":[["15/16","雷霆","季后赛","18","18","37.4","40.5","32.4","6.9","11.0","0.1","2.6","4.3","2.4","26.0"]]}}
     * version : 076564d8dcdbacbc45517c064e78991e
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
         * reg : {"head":["年度","球队","赛季","场数","先发","分钟","命中%","三分%","场均篮板","场均助攻","场均盖帽","场均抢断","失误","犯规","场均得分"],"rows":[["16/17","雷霆","常规赛","59","59","34.7","42.1","33.4","10.6","10.3","0.4","1.6","5.6","2.4","31.0"],["15/16","雷霆","常规赛","80","80","34.4","45.4","29.6","7.8","10.4","0.3","2.0","4.3","2.5","23.5"],["14/15","雷霆","常规赛","67","67","34.4","42.6","29.9","7.3","8.6","0.2","2.1","4.4","2.7","28.1"]]}
         * playoff : {"head":["年度","球队","赛季","场数","先发","分钟","命中%","三分%","场均篮板","场均助攻","场均盖帽","场均抢断","失误","犯规","场均得分"],"rows":[["15/16","雷霆","季后赛","18","18","37.4","40.5","32.4","6.9","11.0","0.1","2.6","4.3","2.4","26.0"]]}
         */

        public RegBean reg;
        public PlayoffBean playoff;

        public RegBean getReg() {
            return reg;
        }

        public void setReg(RegBean reg) {
            this.reg = reg;
        }

        public PlayoffBean getPlayoff() {
            return playoff;
        }

        public void setPlayoff(PlayoffBean playoff) {
            this.playoff = playoff;
        }

        public static class RegBean {
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

        public static class PlayoffBean {
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
