package com.example.date_scheduling.post.service;

import com.example.date_scheduling.post.dto.FindAllPostDto;
import com.example.date_scheduling.post.dto.PostDto;
import com.example.date_scheduling.post.entity.Post;
import com.example.date_scheduling.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// 역할 : 컨트롤러와 저장소(repository) 사이의 잡일 처리 역할
@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {private final PostRepository repository;

    /*
        - 게시물 목록 조회 중간처리
        1. 컨트롤러에게 userId를 뺀 게시물 리스트를 전달한다.
        2. 게시물 목록의 카운트를 세서 따로 추가해서 전달한다.
     */

    public FindAllPostDto findAllServ() {
        return new FindAllPostDto(repository.findAll());
    }

    public FindAllPostDto createServ(final Post newPost) {

        if (newPost == null) {
            log.warn("newPost cannot be null!");
            throw new RuntimeException("newPost cannot be null!");
        }

        boolean flag = repository.save(newPost);
        if (flag) log.info("새로운 게시물 [postId : {}]이 저장되었습니다.", newPost.getPostId());

        return flag ? findAllServ() : null;
    }

    public FindAllPostDto deleteServ(String postId) {

        boolean flag = repository.remove(postId);

        // 삭제 실패한 경우
        if (!flag) {
            log.warn("delete fail! not found postId [{}]", postId);
            throw new RuntimeException("delete fail!");
        }
        return findAllServ();
    }

    public PostDto findOneServ(String postId) {

        Post post = repository.findOne(postId);
        log.info("findOneServ return data - {}", post);

        return post != null ? new PostDto(post) : null;
    }

    public FindAllPostDto update(Post post) {

        boolean flag = repository.modify(post);

        return flag ? findAllServ() : new FindAllPostDto();
    }


}
