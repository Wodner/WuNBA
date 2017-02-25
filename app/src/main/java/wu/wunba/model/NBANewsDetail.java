package wu.wunba.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/23 16:08
 * 邮箱：wuwende@live.cn
 */

public class NBANewsDetail {

    /**
     * code : 0
     * data : {"title":"交易评分：湖人力保乐透签 火箭别针换别墅","abstract":"北京时间2月22日，魔术师履新湖人篮球事务总裁的第一刀，就是...","content":[{"type":"img","img":{"imgurl1000":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/1000","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl641":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/641","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl640":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/640","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl0":{"imgurl":"http://inews.gtimg.com/newsapp_match/0/1169263323/0","width":"640","height":"457","length":"55","face_size":{"x":"219","y":"60","width":"189","height":"189"}}},"itype":"2","islong":"0","has180":"1","isqrcode":"0","face":"219,60,189,189;","count":"1"},{"type":"text","info":"北京时间2月22日，魔术师履新湖人篮球事务总裁的第一刀，就是拿球队得分王路-威廉姆斯，从火箭得到了科里-布鲁尔和一个17年的首轮签。对于这笔操作，《Fox Sports》给双方打出了C+和A-的分数。"},{"type":"text","info":"湖人：C+"},{"type":"text","info":"没那么差，魔术师的第一笔真没那么差。虽然可能更好，但他至少没有被敲竹杠。"},{"type":"text","info":"现在看来这笔交易可能有些操之过急，但得到一个首轮签的回报湖人已经达到目的了。下赛季只挣700万美元的球队得分王，值一个首轮签。"},{"type":"text","info":"有很多质疑湖人为什么要科里-布鲁尔，他比路威挣的还多。连长对湖人的战术作用不大，所以他只是得到首轮签不得不付出的代价。"},{"type":"text","info":"当然湖人必须送走路威，他在只会帮湖人队赢球，这跟球队的摆烂政策不符。所以分开来说，得到火箭的首轮签湖人可以打B+，但接下布鲁尔的合同只能打D，所以中和一下吧。"},{"type":"text","info":"火箭：A-"},{"type":"text","info":"火箭的板凳还需要一个防守糟糕、得分爆炸的矮个后卫吗？这个问题见仁见智，但只付出已经淡出轮换的布鲁尔和一个末段首轮签，火箭稳赚不赔。"},{"type":"text","info":"他们能有更好的选择吗？当然，不过不是谁都能跟国王做交易的呀！（吐槽厉害）"}],"url":"http://nbachina.qq.com/a/20170222/025800.htm","imgurl":"http://inews.gtimg.com/newsapp_ls/0/1169290824_640330/0","imgurl1":"http://inews.gtimg.com/newsapp_ls/0/1169290824_640470/0","imgurl2":"http://inews.gtimg.com/newsapp_ls/0/1169290824_150120/0","pub_time":"2017-02-22 10:44:34","atype":"0","commentId":"1777254985","newsAppId":"","source":"NBA官网","commentsNum":"8","upNum":"194","pub_time_new":"02-22 10:44","isCollect":"-1"}
     * version : a1c4e0efd7bb9590cfaf15b6f8de4f8f
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
         * title : 交易评分：湖人力保乐透签 火箭别针换别墅
         * abstract : 北京时间2月22日，魔术师履新湖人篮球事务总裁的第一刀，就是...
         * content : [{"type":"img","img":{"imgurl1000":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/1000","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl641":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/641","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl640":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/640","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl0":{"imgurl":"http://inews.gtimg.com/newsapp_match/0/1169263323/0","width":"640","height":"457","length":"55","face_size":{"x":"219","y":"60","width":"189","height":"189"}}},"itype":"2","islong":"0","has180":"1","isqrcode":"0","face":"219,60,189,189;","count":"1"},{"type":"text","info":"北京时间2月22日，魔术师履新湖人篮球事务总裁的第一刀，就是拿球队得分王路-威廉姆斯，从火箭得到了科里-布鲁尔和一个17年的首轮签。对于这笔操作，《Fox Sports》给双方打出了C+和A-的分数。"},{"type":"text","info":"湖人：C+"},{"type":"text","info":"没那么差，魔术师的第一笔真没那么差。虽然可能更好，但他至少没有被敲竹杠。"},{"type":"text","info":"现在看来这笔交易可能有些操之过急，但得到一个首轮签的回报湖人已经达到目的了。下赛季只挣700万美元的球队得分王，值一个首轮签。"},{"type":"text","info":"有很多质疑湖人为什么要科里-布鲁尔，他比路威挣的还多。连长对湖人的战术作用不大，所以他只是得到首轮签不得不付出的代价。"},{"type":"text","info":"当然湖人必须送走路威，他在只会帮湖人队赢球，这跟球队的摆烂政策不符。所以分开来说，得到火箭的首轮签湖人可以打B+，但接下布鲁尔的合同只能打D，所以中和一下吧。"},{"type":"text","info":"火箭：A-"},{"type":"text","info":"火箭的板凳还需要一个防守糟糕、得分爆炸的矮个后卫吗？这个问题见仁见智，但只付出已经淡出轮换的布鲁尔和一个末段首轮签，火箭稳赚不赔。"},{"type":"text","info":"他们能有更好的选择吗？当然，不过不是谁都能跟国王做交易的呀！（吐槽厉害）"}]
         * url : http://nbachina.qq.com/a/20170222/025800.htm
         * imgurl : http://inews.gtimg.com/newsapp_ls/0/1169290824_640330/0
         * imgurl1 : http://inews.gtimg.com/newsapp_ls/0/1169290824_640470/0
         * imgurl2 : http://inews.gtimg.com/newsapp_ls/0/1169290824_150120/0
         * pub_time : 2017-02-22 10:44:34
         * atype : 0
         * commentId : 1777254985
         * newsAppId :
         * source : NBA官网
         * commentsNum : 8
         * upNum : 194
         * pub_time_new : 02-22 10:44
         * isCollect : -1
         */

        private String title;
        @SerializedName("abstract")
        private String abstractX;
        private String url;
        private String imgurl;
        private String imgurl1;
        private String imgurl2;
        private String pub_time;
        private String atype;
        private String commentId;
        private String newsAppId;
        private String source;
        private String commentsNum;
        private String upNum;
        private String pub_time_new;
        private String isCollect;
        private List<ContentBean> content;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl1() {
            return imgurl1;
        }

        public void setImgurl1(String imgurl1) {
            this.imgurl1 = imgurl1;
        }

        public String getImgurl2() {
            return imgurl2;
        }

        public void setImgurl2(String imgurl2) {
            this.imgurl2 = imgurl2;
        }

        public String getPub_time() {
            return pub_time;
        }

        public void setPub_time(String pub_time) {
            this.pub_time = pub_time;
        }

        public String getAtype() {
            return atype;
        }

        public void setAtype(String atype) {
            this.atype = atype;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getNewsAppId() {
            return newsAppId;
        }

        public void setNewsAppId(String newsAppId) {
            this.newsAppId = newsAppId;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCommentsNum() {
            return commentsNum;
        }

        public void setCommentsNum(String commentsNum) {
            this.commentsNum = commentsNum;
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

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * type : img
             * img : {"imgurl1000":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/1000","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl641":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/641","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl640":{"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/640","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}},"imgurl0":{"imgurl":"http://inews.gtimg.com/newsapp_match/0/1169263323/0","width":"640","height":"457","length":"55","face_size":{"x":"219","y":"60","width":"189","height":"189"}}}
             * itype : 2
             * islong : 0
             * has180 : 1
             * isqrcode : 0
             * face : 219,60,189,189;
             * count : 1
             * info : 北京时间2月22日，魔术师履新湖人篮球事务总裁的第一刀，就是拿球队得分王路-威廉姆斯，从火箭得到了科里-布鲁尔和一个17年的首轮签。对于这笔操作，《Fox Sports》给双方打出了C+和A-的分数。
             */

            private String type;
            private ImgBean img;
            private String itype;
            private String islong;
            private String has180;
            private String isqrcode;
            private String face;
            private String count;
            private String info;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public ImgBean getImg() {
                return img;
            }

            public void setImg(ImgBean img) {
                this.img = img;
            }

            public String getItype() {
                return itype;
            }

            public void setItype(String itype) {
                this.itype = itype;
            }

            public String getIslong() {
                return islong;
            }

            public void setIslong(String islong) {
                this.islong = islong;
            }

            public String getHas180() {
                return has180;
            }

            public void setHas180(String has180) {
                this.has180 = has180;
            }

            public String getIsqrcode() {
                return isqrcode;
            }

            public void setIsqrcode(String isqrcode) {
                this.isqrcode = isqrcode;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public static class ImgBean {
                /**
                 * imgurl1000 : {"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/1000","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}}
                 * imgurl641 : {"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/641","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}}
                 * imgurl640 : {"imgurl":"http://inews.gtimg.com/newsapp_bt/0/1169263323/640","width":"640","height":"457","face_size":{"x":"219","y":"60","width":"189","height":"189"}}
                 * imgurl0 : {"imgurl":"http://inews.gtimg.com/newsapp_match/0/1169263323/0","width":"640","height":"457","length":"55","face_size":{"x":"219","y":"60","width":"189","height":"189"}}
                 */

                private Imgurl1000Bean imgurl1000;
                private Imgurl641Bean imgurl641;
                private Imgurl640Bean imgurl640;
                private Imgurl0Bean imgurl0;

                public Imgurl1000Bean getImgurl1000() {
                    return imgurl1000;
                }

                public void setImgurl1000(Imgurl1000Bean imgurl1000) {
                    this.imgurl1000 = imgurl1000;
                }

                public Imgurl641Bean getImgurl641() {
                    return imgurl641;
                }

                public void setImgurl641(Imgurl641Bean imgurl641) {
                    this.imgurl641 = imgurl641;
                }

                public Imgurl640Bean getImgurl640() {
                    return imgurl640;
                }

                public void setImgurl640(Imgurl640Bean imgurl640) {
                    this.imgurl640 = imgurl640;
                }

                public Imgurl0Bean getImgurl0() {
                    return imgurl0;
                }

                public void setImgurl0(Imgurl0Bean imgurl0) {
                    this.imgurl0 = imgurl0;
                }

                public static class Imgurl1000Bean {
                    /**
                     * imgurl : http://inews.gtimg.com/newsapp_bt/0/1169263323/1000
                     * width : 640
                     * height : 457
                     * face_size : {"x":"219","y":"60","width":"189","height":"189"}
                     */

                    private String imgurl;
                    private String width;
                    private String height;
                    private FaceSizeBean face_size;

                    public String getImgurl() {
                        return imgurl;
                    }

                    public void setImgurl(String imgurl) {
                        this.imgurl = imgurl;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public FaceSizeBean getFace_size() {
                        return face_size;
                    }

                    public void setFace_size(FaceSizeBean face_size) {
                        this.face_size = face_size;
                    }

                    public static class FaceSizeBean {
                        /**
                         * x : 219
                         * y : 60
                         * width : 189
                         * height : 189
                         */

                        private String x;
                        private String y;
                        private String width;
                        private String height;

                        public String getX() {
                            return x;
                        }

                        public void setX(String x) {
                            this.x = x;
                        }

                        public String getY() {
                            return y;
                        }

                        public void setY(String y) {
                            this.y = y;
                        }

                        public String getWidth() {
                            return width;
                        }

                        public void setWidth(String width) {
                            this.width = width;
                        }

                        public String getHeight() {
                            return height;
                        }

                        public void setHeight(String height) {
                            this.height = height;
                        }
                    }
                }

                public static class Imgurl641Bean {
                    /**
                     * imgurl : http://inews.gtimg.com/newsapp_bt/0/1169263323/641
                     * width : 640
                     * height : 457
                     * face_size : {"x":"219","y":"60","width":"189","height":"189"}
                     */

                    private String imgurl;
                    private String width;
                    private String height;
                    private FaceSizeBeanX face_size;

                    public String getImgurl() {
                        return imgurl;
                    }

                    public void setImgurl(String imgurl) {
                        this.imgurl = imgurl;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public FaceSizeBeanX getFace_size() {
                        return face_size;
                    }

                    public void setFace_size(FaceSizeBeanX face_size) {
                        this.face_size = face_size;
                    }

                    public static class FaceSizeBeanX {
                        /**
                         * x : 219
                         * y : 60
                         * width : 189
                         * height : 189
                         */

                        private String x;
                        private String y;
                        private String width;
                        private String height;

                        public String getX() {
                            return x;
                        }

                        public void setX(String x) {
                            this.x = x;
                        }

                        public String getY() {
                            return y;
                        }

                        public void setY(String y) {
                            this.y = y;
                        }

                        public String getWidth() {
                            return width;
                        }

                        public void setWidth(String width) {
                            this.width = width;
                        }

                        public String getHeight() {
                            return height;
                        }

                        public void setHeight(String height) {
                            this.height = height;
                        }
                    }
                }

                public static class Imgurl640Bean {
                    /**
                     * imgurl : http://inews.gtimg.com/newsapp_bt/0/1169263323/640
                     * width : 640
                     * height : 457
                     * face_size : {"x":"219","y":"60","width":"189","height":"189"}
                     */

                    private String imgurl;
                    private String width;
                    private String height;
                    private FaceSizeBeanXX face_size;

                    public String getImgurl() {
                        return imgurl;
                    }

                    public void setImgurl(String imgurl) {
                        this.imgurl = imgurl;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public FaceSizeBeanXX getFace_size() {
                        return face_size;
                    }

                    public void setFace_size(FaceSizeBeanXX face_size) {
                        this.face_size = face_size;
                    }

                    public static class FaceSizeBeanXX {
                        /**
                         * x : 219
                         * y : 60
                         * width : 189
                         * height : 189
                         */

                        private String x;
                        private String y;
                        private String width;
                        private String height;

                        public String getX() {
                            return x;
                        }

                        public void setX(String x) {
                            this.x = x;
                        }

                        public String getY() {
                            return y;
                        }

                        public void setY(String y) {
                            this.y = y;
                        }

                        public String getWidth() {
                            return width;
                        }

                        public void setWidth(String width) {
                            this.width = width;
                        }

                        public String getHeight() {
                            return height;
                        }

                        public void setHeight(String height) {
                            this.height = height;
                        }
                    }
                }

                public static class Imgurl0Bean {
                    /**
                     * imgurl : http://inews.gtimg.com/newsapp_match/0/1169263323/0
                     * width : 640
                     * height : 457
                     * length : 55
                     * face_size : {"x":"219","y":"60","width":"189","height":"189"}
                     */

                    private String imgurl;
                    private String width;
                    private String height;
                    private String length;
                    private FaceSizeBeanXXX face_size;

                    public String getImgurl() {
                        return imgurl;
                    }

                    public void setImgurl(String imgurl) {
                        this.imgurl = imgurl;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getLength() {
                        return length;
                    }

                    public void setLength(String length) {
                        this.length = length;
                    }

                    public FaceSizeBeanXXX getFace_size() {
                        return face_size;
                    }

                    public void setFace_size(FaceSizeBeanXXX face_size) {
                        this.face_size = face_size;
                    }

                    public static class FaceSizeBeanXXX {
                        /**
                         * x : 219
                         * y : 60
                         * width : 189
                         * height : 189
                         */

                        private String x;
                        private String y;
                        private String width;
                        private String height;

                        public String getX() {
                            return x;
                        }

                        public void setX(String x) {
                            this.x = x;
                        }

                        public String getY() {
                            return y;
                        }

                        public void setY(String y) {
                            this.y = y;
                        }

                        public String getWidth() {
                            return width;
                        }

                        public void setWidth(String width) {
                            this.width = width;
                        }

                        public String getHeight() {
                            return height;
                        }

                        public void setHeight(String height) {
                            this.height = height;
                        }
                    }
                }
            }
        }
    }
}
