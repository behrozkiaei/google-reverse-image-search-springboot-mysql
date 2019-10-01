package com.image.api.repository;

import com.image.api.entity.Links;
import com.image.api.model.LinksLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface linksRepository extends JpaRepository<Links, Long> {

    @Query(" FROM Links WHERE image_id = ?1")
    List<Links> findByImageId(String id);
}
