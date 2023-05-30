package com.example.toyblog.repository;

import com.example.toyblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom {
}
