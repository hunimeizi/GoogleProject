package com.xiangxue.googleproject.data.bean;

public class LibraryInfo {

    private String title; // XiangxeMusic
    private String summary; // “享学VIP之JetPack项目”
    private String url; // 本来是用来跳转到 WebView要加载的网页路径的

    public LibraryInfo() {
    }

    public LibraryInfo(String title, String summary, String url) {
        this.title = title;
        this.summary = summary;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
