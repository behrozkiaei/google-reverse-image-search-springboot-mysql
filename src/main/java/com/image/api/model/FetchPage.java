package com.image.api.model;


public class FetchPage {

    private String image;
    private String link;
    private String title;
    private String like ;

    public FetchPage( String title ,String link , String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setLike(String like) {
        this.like = like;
    }
}
