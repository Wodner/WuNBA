package wu.wunba.model;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 22:52
 * 邮箱：wuwende@live.cn
 */

public class NBAGameBaseInfo {


    /**
     * code : 0
     * data : {"leftId":"9","leftName":"勇士","leftBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/901.png","leftGoal":"108","leftUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=9","rightId":"27","rightName":"奇才","rightBadge":"http://mat1.gtimg.com/sports/nba/logo/1602/27.png","rightGoal":"112","rightUrl":"http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=27","matchPeriod":"2","matchType":"2","quarterDesc":"第4节 00:00","startDate":"03月01日","startHour":"08:00","venue":"威瑞森中心球馆","desc":"常规赛","hasLiveText":"1","targetId":"1600093114","updateFrequency":"1800","isPay":"0","webUrl":"http://sports.qq.com/kbsweb/kbsshare/game.htm?mid=100000:1469479","liveType":"4","ifHasChatroom":"1","leftWins":"50","leftLosses":"10","rightWins":"35","rightLosses":"23"}
     * version : d073e06fb5ca8beee8e87bdc3fdd8fdb
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
         * leftId : 9
         * leftName : 勇士
         * leftBadge : http://mat1.gtimg.com/sports/nba/logo/1602/901.png
         * leftGoal : 108
         * leftUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=9
         * rightId : 27
         * rightName : 奇才
         * rightBadge : http://mat1.gtimg.com/sports/nba/logo/1602/27.png
         * rightGoal : 112
         * rightUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=27
         * matchPeriod : 2
         * matchType : 2
         * quarterDesc : 第4节 00:00
         * startDate : 03月01日
         * startHour : 08:00
         * venue : 威瑞森中心球馆
         * desc : 常规赛
         * hasLiveText : 1
         * targetId : 1600093114
         * updateFrequency : 1800
         * isPay : 0
         * webUrl : http://sports.qq.com/kbsweb/kbsshare/game.htm?mid=100000:1469479
         * liveType : 4
         * ifHasChatroom : 1
         * leftWins : 50
         * leftLosses : 10
         * rightWins : 35
         * rightLosses : 23
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
        private String matchPeriod;
        private String matchType;
        private String quarterDesc;
        private String startDate;
        private String startHour;
        private String venue;
        private String desc;
        private String hasLiveText;
        private String targetId;
        private String updateFrequency;
        private String isPay;
        private String webUrl;
        private String liveType;
        private String ifHasChatroom;
        private String leftWins;
        private String leftLosses;
        private String rightWins;
        private String rightLosses;

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

        public String getMatchPeriod() {
            return matchPeriod;
        }

        public void setMatchPeriod(String matchPeriod) {
            this.matchPeriod = matchPeriod;
        }

        public String getMatchType() {
            return matchType;
        }

        public void setMatchType(String matchType) {
            this.matchType = matchType;
        }

        public String getQuarterDesc() {
            return quarterDesc;
        }

        public void setQuarterDesc(String quarterDesc) {
            this.quarterDesc = quarterDesc;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStartHour() {
            return startHour;
        }

        public void setStartHour(String startHour) {
            this.startHour = startHour;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getHasLiveText() {
            return hasLiveText;
        }

        public void setHasLiveText(String hasLiveText) {
            this.hasLiveText = hasLiveText;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getUpdateFrequency() {
            return updateFrequency;
        }

        public void setUpdateFrequency(String updateFrequency) {
            this.updateFrequency = updateFrequency;
        }

        public String getIsPay() {
            return isPay;
        }

        public void setIsPay(String isPay) {
            this.isPay = isPay;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getLiveType() {
            return liveType;
        }

        public void setLiveType(String liveType) {
            this.liveType = liveType;
        }

        public String getIfHasChatroom() {
            return ifHasChatroom;
        }

        public void setIfHasChatroom(String ifHasChatroom) {
            this.ifHasChatroom = ifHasChatroom;
        }

        public String getLeftWins() {
            return leftWins;
        }

        public void setLeftWins(String leftWins) {
            this.leftWins = leftWins;
        }

        public String getLeftLosses() {
            return leftLosses;
        }

        public void setLeftLosses(String leftLosses) {
            this.leftLosses = leftLosses;
        }

        public String getRightWins() {
            return rightWins;
        }

        public void setRightWins(String rightWins) {
            this.rightWins = rightWins;
        }

        public String getRightLosses() {
            return rightLosses;
        }

        public void setRightLosses(String rightLosses) {
            this.rightLosses = rightLosses;
        }
    }
}
