package com.jiaui.recyclerviewhaderdemo.bean;

import java.util.List;

public class NewsBean {

    public NewsBean() {
    }

    public List<News> todayNewsList;

    public void setTodayNewsList(List<News> todayNewsList) {
        this.todayNewsList = todayNewsList;
    }

    public List<News> getTodayNewsList() {
        return todayNewsList;
    }

    public static class News {

        private String title;
        private String time;
        private int count;
        private int resId;

        public News(String title, int resId, String time, int count) {
            this.title = title;
            this.resId = resId;
            this.time = time;
            this.count = count;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }
    }
}