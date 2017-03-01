package wu.wunba.model;

/**
 * 描述：
 * 作者：Wu on 2017/2/25 00:05
 * 邮箱：wuwende@live.cn
 */

public class NBATeam {


    /**
     * teamId : 6
     * teamName : 小牛
     * fullCnName : 达拉斯小牛
     * logo : http://mat1.gtimg.com/sports/nba/logo/1602/6.png
     * detailUrl : http://sports.qq.com/kbsweb/kbsshare/team.htm?ref=nbaapp&cid=100000&tid=6
     * city : 达拉斯
     */

    private String teamId;
    private String teamName;
    private String fullCnName;
    private String logo;
    private String detailUrl;
    private String city;

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

    public String getFullCnName() {
        return fullCnName;
    }

    public void setFullCnName(String fullCnName) {
        this.fullCnName = fullCnName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
