package com.example.toyblog.service;

import com.example.toyblog.domain.Post;
import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.dto.request.EditPost;
import com.example.toyblog.dto.request.SearchOption;
import com.example.toyblog.dto.response.PostResponse;
import com.example.toyblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private static PostResponse apply(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public void write(CreatePost createPost){
        Post post = new Post(createPost.getTitle(), createPost.getContent());
        postRepository.save(post);
    }

    public PostResponse get(Long id){
          Post post = postRepository.findById(id)
                  .orElseThrow(()->new IllegalArgumentException("존재하지 않는 글입니다!"));

        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public List<PostResponse> getList(SearchOption option) {
        return postRepository.getList(option).stream()
                .map(PostService::apply)
                .collect(Collectors.toList());
    }
    @Transactional
    public void edit(Long id, EditPost editPost){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다!"));
        if(editPost.getTitle() != null){
            post.setTitle(editPost.getTitle());
        }
        if(editPost.getContent() != null){
            post.setContent(editPost.getContent());
        }

    }

    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 글입니다."));
        postRepository.delete(post);
    }
}
