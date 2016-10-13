package go.xzillion.com.xzilliongo.soup.entity;

import android.support.v7.graphics.Palette;

/**
 * Created by Xzillion on 2016/9/20.
 * E-mail : zouxiang13148@outlook.com
 * 某期鸡汤属性的实体类
 */

public class Soup {
    private int res;

    private Data data;//鸡汤数据封装类

    public void setRes(int res) {
        this.res = res;
    }

    public int getRes() {
        return this.res;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }


    public class Data {
        private String hpcontent_id;//鸡汤序列的id,似乎无规律可循

        private String hp_title;//鸡汤文的期数标题,例如VOL.1443,这个似乎是按照顺序

        private String author_id;//作者id,没卵用

        private String hp_img_url;//鸡汤图片的url,文艺装逼必备

        private String hp_img_original_url;//同上

        private String hp_author;//描述图片和作者的文字

        private String ipad_url;//也是图片地址

        private String hp_content;//鸡汤文字

        private String hp_makettime;//创建时间

        private String last_update_date;//最终更新时间

        private String web_url;//鸡汤的网页地址

        private String wb_img_url;//好像没用的web图片地址

        private int praisenum;//点赞数量

        private int sharenum;//分享数量

        private int commentnum;//交流数
        private Palette.Swatch color_swatch;//颜色样本


        //一堆get&set方法

        public void setHpcontent_id(String hpcontent_id) {
            this.hpcontent_id = hpcontent_id;
        }

        public String getHpcontent_id() {
            return this.hpcontent_id;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getHp_title() {
            return this.hp_title;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getAuthor_id() {
            return this.author_id;
        }

        public void setHp_img_url(String hp_img_url) {
            this.hp_img_url = hp_img_url;
        }

        public String getHp_img_url() {
            return this.hp_img_url;
        }

        public void setHp_img_original_url(String hp_img_original_url) {
            this.hp_img_original_url = hp_img_original_url;
        }

        public String getHp_img_original_url() {
            return this.hp_img_original_url;
        }

        public void setHp_author(String hp_author) {
            this.hp_author = hp_author;
        }

        public String getHp_author() {
            return this.hp_author;
        }

        public void setIpad_url(String ipad_url) {
            this.ipad_url = ipad_url;
        }

        public String getIpad_url() {
            return this.ipad_url;
        }

        public void setHp_content(String hp_content) {
            this.hp_content = hp_content;
        }

        public String getHp_content() {
            return this.hp_content;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getHp_makettime() {
            return this.hp_makettime;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getLast_update_date() {
            return this.last_update_date;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getWeb_url() {
            return this.web_url;
        }

        public void setWb_img_url(String wb_img_url) {
            this.wb_img_url = wb_img_url;
        }

        public String getWb_img_url() {
            return this.wb_img_url;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getPraisenum() {
            return this.praisenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getSharenum() {
            return this.sharenum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public int getCommentnum() {
            return this.commentnum;
        }

        public Palette.Swatch getColor_swatch() {
            return color_swatch;
        }

        public void setColor_swatch(Palette.Swatch color_swatch) {
            this.color_swatch = color_swatch;
        }
    }
}