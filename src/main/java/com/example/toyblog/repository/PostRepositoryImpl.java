package com.example.toyblog.repository;

import com.example.toyblog.domain.Post;
import com.example.toyblog.domain.QPost;
import com.example.toyblog.dto.request.SearchOption;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
   private final JPAQueryFactory jpaQueryFactory;

   @Override
   public List<Post> getList(SearchOption option) {
     return jpaQueryFactory.selectFrom(QPost.post)
              .limit(option.getSize())
              .offset(option.getOffset())
              .orderBy(QPost.post.id.desc())
              .fetch();
   }
}
