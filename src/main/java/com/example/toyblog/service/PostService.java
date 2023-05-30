package com.example.toyblog.service;

import com.example.toyblog.domain.Post;
import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.dto.response.PostResponse;
import com.example.toyblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public void write(CreatePost createPost){
        Post post = new Post(createPost.getTitle(), createPost.getContent());
        postRepository.save(post);
    }

    public PostResponse get(Long id){
          Post post = postRepository.findById(id)
                  .orElseThrow(()->new IllegalArgumentException("존재하지 않는 글입니다!"));

        PostResponse postResponse = new PostResponse(post.getId(), post.getTitle(), post.getContent());
        return postResponse;
    }
}
