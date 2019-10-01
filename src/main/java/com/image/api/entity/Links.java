package com.image.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "links")
//@EntityListeners(AuditingEntityListener.class)
public class Links {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Links(String link, Image image,String title) {
        this.link = link;
        this.image = image;
        this.title = title;
    }

    @Lob
    private String link;

    private  String meta;

    private String title;



    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="imageId")
    private Image image;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getTitle() {
        return title;
    }

//    public Image getImage() {
//        return image;
//    }

    public void setImage(Image image) {
        this.image = image;
    }



}
