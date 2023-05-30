package com.example.toyblog.repository;

import com.example.toyblog.domain.Post;
import com.example.toyblog.dto.request.SearchOption;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> getList(SearchOption option);
}
