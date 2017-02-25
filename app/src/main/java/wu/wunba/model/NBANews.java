package wu.wunba.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/23 00:02
 * 邮箱：wuwende@live.cn
 */

public class NBANews {


    /**
     * atype : 1
     * title : 高清-全明星周末技巧挑战赛精彩瞬间 图集
     * abstract : 高清-全明星周末技巧挑战赛精彩瞬间 图集
     * imgurl : http://inews.gtimg.com/newsapp_ls/0/1157840466_640470/0
     * imgurl2 : http://inews.gtimg.com/newsapp_ls/0/1157840466_150120/0
     * newsId : 20170219018282
     * url : http://nbachina.qq.com/a/20170219/010961.htm
     * commentId : 1772058865
     * pub_time : 2017-02-19 09:57
     * column : nbapic
     * vid :
     * duration :
     * img_count : 16
     * images_3 : ["http://inews.gtimg.com/newsapp_ls/0/1157840466_200160/0","http://inews.gtimg.com/newsapp_ls/0/1157840467_200160/0","http://inews.gtimg.com/newsapp_ls/0/1157840468_200160/0"]
     * footer : 16图
     * commentNum : 0
     * upNum : 2
     * pub_time_new : 02-19 09:57
     * isCollect : -1
     */

    private String atype;
    private String title;
    @SerializedName("abstract")
    private String abstractX;
    private String imgurl;
    private String imgurl2;
    private String newsId;
    private String url;
    private String commentId;
    private String pub_time;
    private String column;
    private String vid;
    private String duration;
    private String img_count;
    private String footer;
    private String commentNum;
    private String upNum;
    private String pub_time_new;
    private String isCollect;
    private List<String> images_3;

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getImgurl2() {
        return imgurl2;
    }

    public void setImgurl2(String imgurl2) {
        this.imgurl2 = imgurl2;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImg_count() {
        return img_count;
    }

    public void setImg_count(String img_count) {
        this.img_count = img_count;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getUpNum() {
        return upNum;
    }

    public void setUpNum(String upNum) {
        this.upNum = upNum;
    }

    public String getPub_time_new() {
        return pub_time_new;
    }

    public void setPub_time_new(String pub_time_new) {
        this.pub_time_new = pub_time_new;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public List<String> getImages_3() {
        return images_3;
    }

    public void setImages_3(List<String> images_3) {
        this.images_3 = images_3;
    }
}
