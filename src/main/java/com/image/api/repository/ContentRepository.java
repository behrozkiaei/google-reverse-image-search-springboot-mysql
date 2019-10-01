package com.image.api.repository;

import com.image.api.entity.SearchContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository  extends JpaRepository<SearchContent,Long> {

}
