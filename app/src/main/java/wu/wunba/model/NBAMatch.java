package wu.wunba.model;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/19 01:16
 * 邮箱：wuwende@live.cn
 */

public class NBAMatch {

    /**
     * code : 0
     * data : {"matches":[{"updateFrequency":"1800","matchInfo":{"matchType":"2","mid":"100000:1469969","leftId":"47","leftName":"东部明星","leftBadge":"http://img1.gtimg.com/sports/pics/hv1/76/97/2185/142104436.png","leftGoal":"88","leftHasUrl":"0","leftDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=47","rightId":"48","rightDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=48","rightHasUrl":"0","rightName":"西部明星","rightBadge":"http://img1.gtimg.com/sports/pics/hv1/77/97/2185/142104437.png","rightGoal":"59","matchDesc":"NBA常规赛","startTime":"2017-02-18 08:00:00","title":"","logo":"","matchPeriod":"2","quarter":"","quarterTime":"","liveType":"4","tabs":[{"type":"3","desc":"技术统计"},{"type":"5","desc":"精彩视频"}],"broadcasters":["NBA联盟通","BesTV"],"isBook":"-1"}},{"updateFrequency":"1800","matchInfo":{"matchType":"2","mid":"100000:1469970","leftId":"41","leftName":"世界联队","leftBadge":"http://img1.gtimg.com/sports/pics/hv1/81/97/2185/142104441.png","leftGoal":"150","leftHasUrl":"0","leftDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=41","rightId":"40","rightDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=40","rightHasUrl":"0","rightName":"美国队","rightBadge":"http://img1.gtimg.com/sports/pics/hv1/80/97/2185/142104440.png","rightGoal":"141","matchDesc":"NBA常规赛","startTime":"2017-02-18 10:00:00","title":"","logo":"","matchPeriod":"2","quarter":"第2节","quarterTime":"00:00","liveType":"4","tabs":[{"type":"3","desc":"技术统计"},{"type":"5","desc":"精彩视频"}],"broadcasters":["NBA联盟通","BesTV"],"isBook":"-1"}}],"updateFrequency":"1800"}
     * version : f1e80485d96be531f2f994fb3aaf2903
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
         * matches : [{"updateFrequency":"1800","matchInfo":{"matchType":"2","mid":"100000:1469969","leftId":"47","leftName":"东部明星","leftBadge":"http://img1.gtimg.com/sports/pics/hv1/76/97/2185/142104436.png","leftGoal":"88","leftHasUrl":"0","leftDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=47","rightId":"48","rightDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=48","rightHasUrl":"0","rightName":"西部明星","rightBadge":"http://img1.gtimg.com/sports/pics/hv1/77/97/2185/142104437.png","rightGoal":"59","matchDesc":"NBA常规赛","startTime":"2017-02-18 08:00:00","title":"","logo":"","matchPeriod":"2","quarter":"","quarterTime":"","liveType":"4","tabs":[{"type":"3","desc":"技术统计"},{"type":"5","desc":"精彩视频"}],"broadcasters":["NBA联盟通","BesTV"],"isBook":"-1"}},{"updateFrequency":"1800","matchInfo":{"matchType":"2","mid":"100000:1469970","leftId":"41","leftName":"世界联队","leftBadge":"http://img1.gtimg.com/sports/pics/hv1/81/97/2185/142104441.png","leftGoal":"150","leftHasUrl":"0","leftDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=41","rightId":"40","rightDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=40","rightHasUrl":"0","rightName":"美国队","rightBadge":"http://img1.gtimg.com/sports/pics/hv1/80/97/2185/142104440.png","rightGoal":"141","matchDesc":"NBA常规赛","startTime":"2017-02-18 10:00:00","title":"","logo":"","matchPeriod":"2","quarter":"第2节","quarterTime":"00:00","liveType":"4","tabs":[{"type":"3","desc":"技术统计"},{"type":"5","desc":"精彩视频"}],"broadcasters":["NBA联盟通","BesTV"],"isBook":"-1"}}]
         * updateFrequency : 1800
         */

        private String updateFrequency;
        private List<MatchesBean> matches;

        public String getUpdateFrequency() {
            return updateFrequency;
        }

        public void setUpdateFrequency(String updateFrequency) {
            this.updateFrequency = updateFrequency;
        }

        public List<MatchesBean> getMatches() {
            return matches;
        }

        public void setMatches(List<MatchesBean> matches) {
            this.matches = matches;
        }

        public static class MatchesBean {
            /**
             * updateFrequency : 1800
             * matchInfo : {"matchType":"2","mid":"100000:1469969","leftId":"47","leftName":"东部明星","leftBadge":"http://img1.gtimg.com/sports/pics/hv1/76/97/2185/142104436.png","leftGoal":"88","leftHasUrl":"0","leftDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=47","rightId":"48","rightDetailUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=48","rightHasUrl":"0","rightName":"西部明星","rightBadge":"http://img1.gtimg.com/sports/pics/hv1/77/97/2185/142104437.png","rightGoal":"59","matchDesc":"NBA常规赛","startTime":"2017-02-18 08:00:00","title":"","logo":"","matchPeriod":"2","quarter":"","quarterTime":"","liveType":"4","tabs":[{"type":"3","desc":"技术统计"},{"type":"5","desc":"精彩视频"}],"broadcasters":["NBA联盟通","BesTV"],"isBook":"-1"}
             */

            private String updateFrequency;
            private MatchInfoBean matchInfo;

            public String getUpdateFrequency() {
                return updateFrequency;
            }

            public void setUpdateFrequency(String updateFrequency) {
                this.updateFrequency = updateFrequency;
            }

            public MatchInfoBean getMatchInfo() {
                return matchInfo;
            }

            public void setMatchInfo(MatchInfoBean matchInfo) {
                this.matchInfo = matchInfo;
            }

            public static class MatchInfoBean {
                /**
                 * matchType : 2
                 * mid : 100000:1469969
                 * leftId : 47
                 * leftName : 东部明星
                 * leftBadge : http://img1.gtimg.com/sports/pics/hv1/76/97/2185/142104436.png
                 * leftGoal : 88
                 * leftHasUrl : 0
                 * leftDetailUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=47
                 * rightId : 48
                 * rightDetailUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=48
                 * rightHasUrl : 0
                 * rightName : 西部明星
                 * rightBadge : http://img1.gtimg.com/sports/pics/hv1/77/97/2185/142104437.png
                 * rightGoal : 59
                 * matchDesc : NBA常规赛
                 * startTime : 2017-02-18 08:00:00
                 * title :
                 * logo :
                 * matchPeriod : 2
                 * quarter :
                 * quarterTime :
                 * liveType : 4
                 * tabs : [{"type":"3","desc":"技术统计"},{"type":"5","desc":"精彩视频"}]
                 * broadcasters : ["NBA联盟通","BesTV"]
                 * isBook : -1
                 */

                private String matchType;
                private String mid;
                private String leftId;
                private String leftName;
                private String leftBadge;
                private String leftGoal;
                private String leftHasUrl;
                private String leftDetailUrl;
                private String rightId;
                private String rightDetailUrl;
                private String rightHasUrl;
                private String rightName;
                private String rightBadge;
                private String rightGoal;
                private String matchDesc;
                private String startTime;
                private String title;
                private String logo;
                private String matchPeriod;
                private String quarter;
                private String quarterTime;
                private String liveType;
                private String isBook;
                private List<TabsBean> tabs;
                private List<String> broadcasters;

                public String getMatchType() {
                    return matchType;
                }

                public void setMatchType(String matchType) {
                    this.matchType = matchType;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

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

                public String getLeftHasUrl() {
                    return leftHasUrl;
                }

                public void setLeftHasUrl(String leftHasUrl) {
                    this.leftHasUrl = leftHasUrl;
                }

                public String getLeftDetailUrl() {
                    return leftDetailUrl;
                }

                public void setLeftDetailUrl(String leftDetailUrl) {
                    this.leftDetailUrl = leftDetailUrl;
                }

                public String getRightId() {
                    return rightId;
                }

                public void setRightId(String rightId) {
                    this.rightId = rightId;
                }

                public String getRightDetailUrl() {
                    return rightDetailUrl;
                }

                public void setRightDetailUrl(String rightDetailUrl) {
                    this.rightDetailUrl = rightDetailUrl;
                }

                public String getRightHasUrl() {
                    return rightHasUrl;
                }

                public void setRightHasUrl(String rightHasUrl) {
                    this.rightHasUrl = rightHasUrl;
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

                public String getMatchDesc() {
                    return matchDesc;
                }

                public void setMatchDesc(String matchDesc) {
                    this.matchDesc = matchDesc;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getMatchPeriod() {
                    return matchPeriod;
                }

                public void setMatchPeriod(String matchPeriod) {
                    this.matchPeriod = matchPeriod;
                }

                public String getQuarter() {
                    return quarter;
                }

                public void setQuarter(String quarter) {
                    this.quarter = quarter;
                }

                public String getQuarterTime() {
                    return quarterTime;
                }

                public void setQuarterTime(String quarterTime) {
                    this.quarterTime = quarterTime;
                }

                public String getLiveType() {
                    return liveType;
                }

                public void setLiveType(String liveType) {
                    this.liveType = liveType;
                }

                public String getIsBook() {
                    return isBook;
                }

                public void setIsBook(String isBook) {
                    this.isBook = isBook;
                }

                public List<TabsBean> getTabs() {
                    return tabs;
                }

                public void setTabs(List<TabsBean> tabs) {
                    this.tabs = tabs;
                }

                public List<String> getBroadcasters() {
                    return broadcasters;
                }

                public void setBroadcasters(List<String> broadcasters) {
                    this.broadcasters = broadcasters;
                }

                public static class TabsBean {
                    /**
                     * type : 3
                     * desc : 技术统计
                     */

                    private String type;
                    private String desc;

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }
            }
        }
    }
}
