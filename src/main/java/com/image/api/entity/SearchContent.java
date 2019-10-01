package com.image.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "content")
public class SearchContent {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Lob
    @Column(name="CONTENT", length=512)
    private String content;

    private String searchengin;

    public SearchContent(String url, String content, String searchengin) {
        this.url = url;
        this.content = content;
        this.searchengin = searchengin;
    }
}
