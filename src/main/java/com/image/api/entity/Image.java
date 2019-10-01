package com.image.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "image")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String ImageName;

    public String getImageSourceId() {
        return imageSourceId;
    }
    public  Boolean ImageIncludedGoogle;
    public  Boolean ImageIncludedYandex;
    public void setImageSourceId(String imageSourceId) {
        this.imageSourceId = imageSourceId;
    }

    private String imageSourceId;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;


    @OneToMany(mappedBy="image",cascade = CascadeType.ALL)
    private Set<Links> linksSet;


    public Image(String imageName, Date createdAt, Date updatedAt, Set<Links> linksSet) {
        ImageName = imageName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.linksSet = linksSet;
    }


    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Boolean getImageIncludedGoogle() {
        return ImageIncludedGoogle;
    }

    public void setImageIncludedGoogle(Boolean imageIncludedGoogle) {
        ImageIncludedGoogle = imageIncludedGoogle;
    }

    public Boolean getImageIncludedYandex() {
        return ImageIncludedYandex;
    }

    public void setImageIncludedYandex(Boolean imageIncludedYandex) {
        ImageIncludedYandex = imageIncludedYandex;
    }

    public void setLinks(Set<Links> links) {
        this.linksSet = links;
    }
}
