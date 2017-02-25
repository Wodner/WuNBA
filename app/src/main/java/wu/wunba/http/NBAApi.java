package wu.wunba.http;

/**
 * 描述：
 * 作者：Wu on 2017/2/18 13:43
 * 邮箱：wuwende@live.cn
 */

public class NBAApi {

    /**NBA数据类型：每日 or 赛季*/
    public static final String NBA_DATA_TABTYPE_DAY = "1";
    public static final String NBA_DATA_TABTYPE_SEASON = "3";
    /**NBA 每日 or 赛季数据类型：篮板  助攻 盖帽 抢断 得分*/
    public static final String NBA_DATA_STATTYPE_REBOUND = "rebound";
    public static final String NBA_DATA_STATTYPE_ASSIST = "assist";
    public static final String NBA_DATA_STATTYPE_BLOCK = "block";
    public static final String NBA_DATA_STATTYPE_STEAL = "steal";
    public static final String NBA_DATA_STATTYPE_POINT = "point";
    /** BASE URL*/
    public static final String BaseUrl = "http://sportsnba.qq.com/";
    /**每条请求后面都要带的信息 也可以不带*/
    public static final String DefaulttUrl = "&appver=2.2.1&appvid=2.2.1&deviceId=07C5097900E081E142ED046B56925CC0&from=app&guid=07C5097900E081E142ED046B56925CC0&height=1830&network=WIFI&os=Android&osvid=6.0.1&width=1080";
    /**获取视频真正地址的*/
    public static final String BaseVideoUrl = "http://h5vv.video.qq.com/";



    /**
     * @param tabType 每日:1 or 赛季:3
     * @param statType 篮板:rebound  助攻:assist 盖帽:block 抢断:steal 得分:point
     * @param seasonId 2016
     * @return
     */
    public static String getNBAPDataSort(String tabType,String statType,String seasonId){
        return BaseUrl + "player/statsRank?statType=" + statType + "&num=25&tabType=" + tabType + "&seasonId=" + seasonId + DefaulttUrl;
    }

    /**
     * @param date 比赛日期 如 2017-02-19
     * @return
     */
    public static String getNBAGameTime(String date){
        return BaseUrl + "match/listByDate?date=" + date + DefaulttUrl;
    }

    /**
     * @return 获取集锦视频 articleId 这里会返回二百条
     */
    public static String getNBAVideoArticleId(){
        return BaseUrl + "news/index?column=videos"+ DefaulttUrl;
    }

    /**
     * @param articleIds 根据articleId 来获取视频集锦 每次获取二十条
     * @return
     */
    public static String getNBAVideos(String articleIds){
        return BaseUrl + "news/item?column=videos&articleIds=" + articleIds + DefaulttUrl;
    }



    /**
     * @return 获取最佳视频 articleId 这里会返回二百条
     */
    public static String getNBAVideoBestArticleId(){
        return BaseUrl + "news/index?column=depth"+ DefaulttUrl;
    }

    /**
     * @param articleIds 根据articleId 来获取最佳视频 每次获取二十条
     * @return
     */
    public static String getNBAVideoBest(String articleIds){
        return BaseUrl + "news/item?column=depth&articleIds=" + articleIds + DefaulttUrl;
    }


    /**
     * @return 获取花絮视频 articleId 这里会返回二百条
     */
    public static String getNBAVideoTidbitsArticleId(){
        return BaseUrl + "news/index?column=highlight"+ DefaulttUrl;
    }

    /**
     * @param articleIds 根据articleId 来获取花絮视频 每次获取二十条
     * @return
     */
    public static String getNBAVideoTidbits(String articleIds){
        return BaseUrl + "news/item?column=highlight&articleIds=" + articleIds + DefaulttUrl;
    }


    /**
     * 获取视频真正url
     * @param vid
     * @return
     */
    public static String getNBAVideoRealUrl(String vid){
        return BaseVideoUrl + "getinfo?platform=11001&charge=0&otype=json&vids=" + vid +"&appver=1.0.2.2&appvid=1.0.2.2&network=wifi";
    }


    /**
     * @return  NBA头条 id
     */
    public static String getNBANewsHeadlineArticleIds(){
        return BaseUrl + "news/index?column=banner" + DefaulttUrl;
    }

    /**
     * @return NBA头条信息
     */
    public static String getNBANewsHeadlines(String articleIds){
        return BaseUrl + "news/item?column=banner&articleIds=" + articleIds + DefaulttUrl;
    }



    /**
     * @return  NBA头条 id
     */
    public static String getNBANewsInformArticleIds(){
        return BaseUrl + "news/index?column=news" + DefaulttUrl;
    }

    /**
     * @return NBA头条信息
     */
    public static String getNBANewsInform(String articleIds){
        return BaseUrl + "news/item?column=news&articleIds=" + articleIds + DefaulttUrl;
    }


    /**
     * @return  NBA图集 id
     */
    public static String getNBANewsPictureArticleIds(){
        return BaseUrl + "news/index?column=nbapic" + DefaulttUrl;
    }

    /**
     * @return NBA图集
     */
    public static String getNBANewsPicture(String articleIds){
        return BaseUrl + "news/item?column=nbapic&articleIds=" + articleIds + DefaulttUrl;
    }
    /**
     * @return NBA头条新闻详情 http://sportsnba.qq.com/news/detail?column=banner&articleId=20170222026317
     */
    public static String getNBANewsHeadlineDetail(String articleIds){
        return BaseUrl + "news/detail?column=banner&articleId=" + articleIds + DefaulttUrl;
    }

    /** 这个和上面的 接口相同，只要是传新闻详情的 id 都是返回同样的数据
     * @return NBA资讯新闻详情 http://sportsnba.qq.com/news/detail?column=banner&articleId=20170222026317
     */
    public static String getNBANewsInformDetail(String articleIds){
        return BaseUrl + "news/detail?column=news&articleId=" + articleIds + DefaulttUrl;
    }


    /**获取图集详情
     * @param articleId
     * @return
     */
    public  static String getNBAPicturesDetail(String articleId){
        return BaseUrl + "news/detail?column=nbapic&articleId=" + articleId + DefaulttUrl;
    }


    /**
     * @return 重要日期
     */
    public static String getNBAImportanceDay(){
        return "http://m.china.nba.com/importantdatetoapp/wap.htm";
    }


    /**
     * @return 按联盟排名
     */
    public static String getNBATeamRankingByUnion(){
        return BaseUrl + "team/rank?"+DefaulttUrl;
    }

    /**
     * @return 按分区排名
     */
    public static String getNBATeamRankingByDivision(){
        return BaseUrl + "team/rankByDivision?"+DefaulttUrl;
    }




}
