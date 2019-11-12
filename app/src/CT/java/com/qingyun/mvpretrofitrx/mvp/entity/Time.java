package com.qingyun.mvpretrofitrx.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Time {

    /**
     * pages : 0
     * News : [{"id":2,"name":"阿斯达四大爱仕达爱仕达","img":"https://et2.etac.io/news/news2001.jpg","time":"2019-11-08 15:15:15"},{"id":1,"name":"阿斯达四大爱仕达","img":"https://et2.etac.io/news/news6120.jpg","time":"2019-11-08 15:14:58"}]
     */

    private int pages;
    private List<NewsBean> News;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<NewsBean> getNews() {
        return News;
    }

    public void setNews(List<NewsBean> News) {
        this.News = News;
    }

    public static class NewsBean implements Parcelable {
        /**
         *
         * id : 2
         * name : 阿斯达四大爱仕达爱仕达
         * img : https://et2.etac.io/news/news2001.jpg
         * time : 2019-11-08 15:15:15
         */

        private int id;
        private String name;
        private String img;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.img);
            dest.writeString(this.time);
        }

        public NewsBean() {
        }

        protected NewsBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.img = in.readString();
            this.time = in.readString();
        }

        public static final Parcelable.Creator<NewsBean> CREATOR = new Parcelable.Creator<NewsBean>() {
            @Override
            public NewsBean createFromParcel(Parcel source) {
                return new NewsBean(source);
            }

            @Override
            public NewsBean[] newArray(int size) {
                return new NewsBean[size];
            }
        };
    }
}
